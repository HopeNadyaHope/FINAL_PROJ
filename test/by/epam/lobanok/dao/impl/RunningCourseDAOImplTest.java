package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.RunningCourseDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

public class RunningCourseDAOImplTest {
	
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private static final String FIND_RUNNING_COURSES = "SELECT faculty.running_courses.id, running_courses.start, running_courses.end, running_courses.passing, " + 
			"courses.courseName, courses.description, users.id as userID, users.name, users.surname " + 
			"FROM running_courses " + 
			"JOIN courses ON running_courses.courses_id = courses.id " + 
			"JOIN users ON running_courses.users_id = users.id " + 
			"WHERE courses_id = ?";	
	
	private static final String FIND_RUNNING_COURSE = "SELECT running_courses.id as runningCourseID, running_courses.start, running_courses.end, running_courses.passing, "+
			" courses.id as courseID, courses.courseName, courses.description, users.id as userID, users.name, users.surname "+
			"FROM running_courses "+
			"JOIN courses ON running_courses.courses_id = courses.id "+
			"JOIN users ON running_courses.users_id = users.id  "+
			"WHERE running_courses.id = ?";

	private static final String FIND_STUDENT_COURSES = "SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing,"+
			"courses.courseName, courses.description, users.id as userID, users.name, users.surname "+ 
			"FROM running_courses "+
			"JOIN courses ON running_courses.courses_id = courses.id "+
			"JOIN users ON running_courses.users_id = users.id "+
			"WHERE running_courses.id IN (SELECT running_courses.id "+ 
										"FROM running_courses "+ 
										"JOIN course_participants ON running_courses.id=course_participants.running_courses_id "+
										"JOIN users ON users.id=course_participants.users_id "+
										"WHERE course_participants.users_id=?)";
	

	private static final String FIND_TEACHER_COURSES = "SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing, "+
			"courses.courseName, courses.description "+ 
			"FROM courses "+
			"JOIN running_courses ON courses.id = running_courses.courses_id "+
			"JOIN users ON users.id = running_courses.users_id "+
			"WHERE running_courses.users_id = ?";
	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String COURSE_ID = "courseID";
	private static final String USER_ID = "userID";
	private static final String ID = "id";
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";
	private static final String PASSING = "passing";
	private static final String START = "start";
	private static final String END = "end";	
	
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void findRunningCoursesTest() {
		List<RunningCourse> expectedRunningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSES);
			ps.setInt(1, 1);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {

                User teacher = new User.Builder()
                			  .withID(resultSet.getInt(USER_ID))
                			  .withName(resultSet.getString(NAME))
                			  .withSurname(resultSet.getString(SURNAME))
                			  .build();

                Course course = new Course.Builder()
                			   .withCourseName(resultSet.getString(COURSE_NAME))
                			   .withDescription(resultSet.getString(DESCRIPTION))
                			   .build();
                
                runningCourse = new RunningCourse.Builder()
                			   .withID(Integer.parseInt(resultSet.getString(ID)))
                			   .withTeacher(teacher)
                			   .withCourse(course)
                			   .withStart(resultSet.getDate(START).toLocalDate())
                			   .withEnd(resultSet.getDate(END).toLocalDate())
                			   .withPassing(resultSet.getString(PASSING))
                			   .build();               
                
                expectedRunningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }	
		
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO();		
		List<RunningCourse> actualRunningCourses = null;
		try {
			actualRunningCourses = runningCourseDAO.findRunningCourses(1);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedRunningCourses, actualRunningCourses);
	}
	
	@Test
	public void findRunningCourseTest() {
		RunningCourse expectedRunningCourse = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSE);
			ps.setInt(1, 2);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				User teacher = new User.Builder()
                			  .withID(resultSet.getInt(USER_ID))
                			  .withName(resultSet.getString(NAME))
                			  .withSurname(resultSet.getString(SURNAME))
                			  .build();
                
                
                Course course = new Course.Builder()
                			   .withID(resultSet.getInt(COURSE_ID))
                			   .withCourseName(resultSet.getString(COURSE_NAME))
                			   .withDescription(resultSet.getString(DESCRIPTION))
                			   .build();
               
                
                expectedRunningCourse = new RunningCourse.Builder()
                			   .withID(Integer.parseInt(resultSet.getString(RUNNING_COURSE_ID)))
                			   .withTeacher(teacher)
                			   .withCourse(course)
                			   .withStart(resultSet.getDate(START).toLocalDate())
                			   .withEnd(resultSet.getDate(END).toLocalDate())
                			   .withPassing(resultSet.getString(PASSING))
                			   .build();
			}			
		}catch (SQLException e) {
			e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }	
		
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 
		RunningCourse actualRunningCourse = null;
		try {
			actualRunningCourse = runningCourseDAO.findRunningCourse(2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedRunningCourse, actualRunningCourse);
	}
	
	@Test
	public void findStudentCoursesTest() {
		List<RunningCourse> expectedRunningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_STUDENT_COURSES);
			ps.setInt(1,2);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				Course course = new Course.Builder()
							   .withCourseName(resultSet.getString(COURSE_NAME))
						       .withDescription(resultSet.getString(DESCRIPTION))	
						       .build();
				
				
				User teacher = new User.Builder()
							  .withID(Integer.parseInt(resultSet.getString(USER_ID)))
							  .withName(resultSet.getString(NAME))
						      .withSurname(resultSet.getString(SURNAME))
						      .build();
				
				runningCourse = new RunningCourse.Builder()
         			   .withID(Integer.parseInt(resultSet.getString(ID)))
         			   .withTeacher(teacher)
         			   .withCourse(course)
         			   .withStart(resultSet.getDate(START).toLocalDate())
         			   .withEnd(resultSet.getDate(END).toLocalDate())
         			   .withPassing(resultSet.getString(PASSING))
         			   .build();
				
				expectedRunningCourses.add(runningCourse);
			}	
		}catch (SQLException e) {
			e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }	
		
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 		
		List<RunningCourse> actualRunningCourses = null;
		try {
			actualRunningCourses = runningCourseDAO.findStudentCourses(2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedRunningCourses, actualRunningCourses);
	}
	
	@Test
	public void findTeacherCoursesTest() {
		List<RunningCourse> expectedRunningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_TEACHER_COURSES);
			ps.setInt(1, 3);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				Course course = new Course.Builder()
						   .withCourseName(resultSet.getString(COURSE_NAME))
					       .withDescription(resultSet.getString(DESCRIPTION))	
					       .build();				
				
				runningCourse = new RunningCourse.Builder()
	         			   .withID(Integer.parseInt(resultSet.getString(ID)))
	         			   .withCourse(course)
	         			   .withStart(resultSet.getDate(START).toLocalDate())
	         			   .withEnd(resultSet.getDate(END).toLocalDate())
	         			   .withPassing(resultSet.getString(PASSING))
	         			   .build();

				expectedRunningCourses.add(runningCourse);
			}
		}catch (SQLException e) {
			e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }	
		
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 		
		List<RunningCourse> actualRunningCourses = null;
		try {
			actualRunningCourses = runningCourseDAO.findTeacherCourses(3);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedRunningCourses, actualRunningCourses);
	}
	
}
