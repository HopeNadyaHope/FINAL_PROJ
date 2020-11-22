package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.UserDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.User;

public class UserDAOImplTest {
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private static final String FIND_TEACHERS = "SELECT users.id,name,surname,age,sex,email,photo_url FROM users WHERE users.roles_id=" +
			"(SELECT id FROM roles WHERE role='преподаватель') ORDER BY surname,name";

	private static final String FIND_STUDENTS = "SELECT users.id,name,surname,age,sex,email,photo_url FROM users WHERE users.roles_id=" +
			"(SELECT id FROM roles WHERE role='студент') ORDER BY surname,name";

	private static final String FIND_USER_BY_ID = "SELECT users.id,name,surname,age,sex,role,email,photo_url FROM users " +
			"JOIN roles ON users.roles_id = roles.id WHERE users.id=?";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String ROLE = "role";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String PHOTO_URL = "photo_url";
	
	private static final String STUDENT = "студент";
	private static final String TEACHER = "преподаватель";

	
	@Test
	public void findAllTeachersTest() {
		List<User> expectedTeachers = new ArrayList<User>();
		User teacher;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_TEACHERS);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                teacher = new User.Builder()
						 .withID(Integer.parseInt(resultSet.getString(ID)))
						 .withName(resultSet.getString(NAME))
						 .withSurname(resultSet.getString(SURNAME))
						 .withAge(resultSet.getInt(AGE))
						 .withSex(resultSet.getString(SEX))
						 .withRole(TEACHER)
						 .withEmail(resultSet.getString(EMAIL))
						 .withPhotoURL(resultSet.getString(PHOTO_URL))
						 .build();
				
                expectedTeachers.add(teacher);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		List<User> actualTeachers = null;
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try {
			actualTeachers = userDAO.findTeachers();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedTeachers, actualTeachers);
	}
	
	@Test
	public void findStudentsTest() {
		List<User> expectedStudents = new ArrayList<User>();
		User student;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_STUDENTS);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				
				student = new User.Builder()
						 .withID(Integer.parseInt(resultSet.getString(ID)))
						 .withName(resultSet.getString(NAME))
						 .withSurname(resultSet.getString(SURNAME))
						 .withAge(resultSet.getInt(AGE))
						 .withSex(resultSet.getString(SEX))
						 .withEmail(resultSet.getString(EMAIL))
						 .withRole(STUDENT)
						 .withPhotoURL(resultSet.getString(PHOTO_URL))
						 .build();
				
				expectedStudents.add(student);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		List<User> actualStudents = null;
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try {
			actualStudents = userDAO.findStudents();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedStudents, actualStudents);
	}
	
	@Test
	public void findUserByIDTest() {
		User expectedUser = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_USER_BY_ID);
			ps.setInt(1,4);
			
			resultSet = ps.executeQuery();			
			resultSet.next();		
			
			expectedUser = new User.Builder()
					.withID(Integer.parseInt(resultSet.getString(ID)))
					.withName(resultSet.getString(NAME))
					.withSurname(resultSet.getString(SURNAME))
					.withRole(resultSet.getString(ROLE))
					.withAge(resultSet.getInt(AGE))
					.withSex(resultSet.getString(SEX))
					.withEmail(resultSet.getString(EMAIL))
					.withPhotoURL(resultSet.getString(PHOTO_URL))
					.build();
			
		}catch (SQLException e) {
			e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		User actualUser = null;
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try { 
			actualUser = userDAO.findUserByID(4);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		org.junit.Assert.assertEquals(expectedUser, actualUser);
	}	
}
