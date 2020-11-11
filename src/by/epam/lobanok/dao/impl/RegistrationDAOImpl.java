package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.RegistrationDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.RegistrationData;

/**
 * Implementation  of RegistratinDAO 
 *
 * @author hope_nadya_hope
 */
public class RegistrationDAOImpl implements RegistrationDAO {
	/**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a RegistrationDAODAO.class
     */
	private static final Logger logger = LogManager.getLogger(RegistrationDAO.class);

	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to find all course participants for a given running course
     */
	private static final String FIND_ROLE_ID = "SELECT roles.id FROM roles WHERE roles.role=?";	
	
	/**
     * SQL statement to add user
     */
	private static final String ADD_USER = "INSERT INTO users(login, password, name, surname, age, sex, email, roles_id) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";	
	
	/**
     * SQL statement to find login in db
     */
	private static final String FIND_LOGIN ="SELECT users.login FROM users WHERE login=?";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Registers user using RegistrationData SQL
     *
     * @param RegistrationData of a user
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void registration(RegistrationData regData)  throws DAOException{				
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try {
			con = pool.takeConnection();			
			
			ps = con.prepareStatement(FIND_ROLE_ID); 
			ps.setString(1, regData.getRole());
			result = ps.executeQuery();	
			result.next();
			
			int role_id;
			role_id = result.getInt(ID);
			
			ps = con.prepareStatement(ADD_USER); 
			ps.setString(1, regData.getLogin());
			ps.setString(2, regData.getPassword());
			ps.setString(3, regData.getName());
			ps.setString(4, regData.getSurname());
			ps.setInt(5, regData.getAge()); 
			ps.setString(6, regData.getSex());
			ps.setString(7, regData.getEmail());
			ps.setInt(8, role_id);
			
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		return;
	}
	
	/**
     * Checks if there is a login SQL
     *
     * @param login
     * @return true if such login is in a system
     * @throws DAOException if an DAO error occurs
     */
	public boolean checkLogin(String login)  throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		
		try { 
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_LOGIN);			
			ps.setString(1, login);
			result = ps.executeQuery();
			
			if(result.next()) {
               return true;
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps, result);
        }
		return false;
	}
}
