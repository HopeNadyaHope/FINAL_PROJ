package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
/**
 * Implementation  of CourseDAO 
 *
 * @author hope_nadya_hope
 */
public class CourseDAOImpl implements CourseDAO{
	 /**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a CourseDAO.class
     */
	private static final Logger logger = LogManager.getLogger(CourseDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to find all courses
     */
	private static final String FIND_COURSES = "SELECT * FROM courses";	
	/**
     * SQL statement to find a course by id
     */
	private static final String FIND_COURSE = "SELECT * FROM courses WHERE id=?";
	/**
     * SQL statement to edit course
     */
	private static final String EDIT_COURSE = "UPDATE courses SET courseName=?,description=? WHERE id=?";
	/**
     * SQL statement to add course
     */
	private static final String ADD_COURSE = "INSERT INTO courses(courseName, description) VALUES(?,?)";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * Finds all courses SQL
     *
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<Course> findCourses() throws DAOException {
		List<Course> courses = new ArrayList<Course>();
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSES);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				course = new Course.Builder()
						.withID(Integer.parseInt(resultSet.getString(ID)))
						.withCourseName(resultSet.getString(COURSE_NAME))
				 		.withDescription(resultSet.getString(DESCRIPTION))
				 		.build();
                courses.add(course);
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return courses;
	}
	
	
	/**
     * Finds a course by id SQL
     * 
     * @param ID of course
     * @return course with this ID
     * @throws DAOException if an DAO error occurs
     */
	public Course findCourse(int courseID) throws DAOException{
		Course course;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE);
			ps.setInt(1, courseID);
			
			resultSet = ps.executeQuery();			
			resultSet.next();
			
			course = new Course.Builder()
					.withID(Integer.parseInt(resultSet.getString(ID)))
					.withCourseName(resultSet.getString(COURSE_NAME))
			 		.withDescription(resultSet.getString(DESCRIPTION))
			 		.build();
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return course;		
	}
	

	/**
     * Edits a course SQL
     * @param a course to edit 
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void editCourse(Course editedCourse) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(EDIT_COURSE); 
			
			ps.setString(1, editedCourse.getCourseName());
			ps.setString(2, editedCourse.getDescription());
			ps.setInt(3, editedCourse.getId());
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}
	
	
	/**
     * Adds a course SQL
     * @param a course to add
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void addCourse(Course course) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_COURSE); 
			
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getDescription());
			
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
		} finally {
			pool.closeConnection(con, ps);
		}
	}
}
