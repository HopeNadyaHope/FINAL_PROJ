package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.exception.NoSuchUserDAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;

/**
 * Implementation  of EntranceDAO 
 *
 * @author hope_nadya_hope
 */
public class EntranceDAOImpl implements EntranceDAO{
	 /**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a EntranceDAO.class
     */
	private static final Logger logger = LogManager.getLogger(EntranceDAO.class);

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";	
	private static final String PHOTO_URL = "photo_url";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to find user by login and password
     */
	private static final String FIND_USER ="SELECT users.id, users.name, users.surname, users.age, users.sex, users.email, roles.role, users.photo_url "+
											"FROM users JOIN roles on roles.id=users.roles_id " + "WHERE login=? AND password=?";

	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Entrances user SQL
     *
     * @param EntranceData which contains login and password
     * @return an authorized user
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public User entrance(EntranceData entrData) throws DAOException {
		String login = entrData.getLogin();
		String password = entrData.getPassword();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		User user = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_USER); 
			
			ps.setString(1, login);
            ps.setString(2, password);
			resultSet = ps.executeQuery();
			
			if(!resultSet.next()) {
				logger.info("NoSuchUserDAOException");
				throw new NoSuchUserDAOException();
			}			
			
			user = new User.Builder()
				  .withID(resultSet.getInt(ID))
				  .withName(resultSet.getString(NAME))
				  .withSurname(resultSet.getString(SURNAME))
				  .withAge(resultSet.getInt(AGE))
				  .withSex(resultSet.getString(SEX))
				  .withEmail(resultSet.getString(EMAIL))
				  .withRole(resultSet.getString(ROLE))
				  .withPhotoURL(resultSet.getString(PHOTO_URL))
				  .build();
            
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return user;
	}
}
