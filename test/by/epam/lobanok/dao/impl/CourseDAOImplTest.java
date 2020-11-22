package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;

public class CourseDAOImplTest {
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private static final String FIND_COURSES = "SELECT * FROM courses";	
	private static final String FIND_COURSE = "SELECT * FROM courses WHERE id=?";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";

	
	/////////////////////////////////////////////////////////////////////////////////////////////	
	
	@Test
	public void findCoursesTest() {
		List<Course> actualCourses = new ArrayList<Course>();
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
                actualCourses.add(course);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		List<Course> expectedCourses = null;
		try {
			expectedCourses = courseDAO.findCourses();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedCourses, actualCourses);
	}
	
	@Test
	public void findCourseTest() {
		Course actualCourse = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE);
			ps.setInt(1, 2);
			
			resultSet = ps.executeQuery();			
			resultSet.next();
			
			actualCourse = new Course.Builder()
					.withID(Integer.parseInt(resultSet.getString(ID)))
					.withCourseName(resultSet.getString(COURSE_NAME))
			 		.withDescription(resultSet.getString(DESCRIPTION))
			 		.build();
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }		
		
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 		
		Course expectedCourse = null;	
		try {
			expectedCourse = courseDAO.findCourse(2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedCourse, actualCourse);
	}
	
}
