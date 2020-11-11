package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.RunningCourseDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

/**
 * Implementation  of RunningCourseDAO 
 *
 * @author hope_nadya_hope
 */
public class RunningCourseDAOImpl implements RunningCourseDAO {
	/**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a RunningCourseDAO.class
     */
	private static final Logger logger = LogManager.getLogger(RunningCourseDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to find all running courses for a given course
     */
	private static final String FIND_RUNNING_COURSES = "SELECT faculty.running_courses.id, running_courses.start, running_courses.end, running_courses.passing, " + 
			"courses.courseName, courses.description, users.id as userID, users.name, users.surname " + 
			"FROM running_courses " + 
			"JOIN courses ON running_courses.courses_id = courses.id " + 
			"JOIN users ON running_courses.users_id = users.id " + 
			"WHERE courses_id = ?";	
	
	/**
     * SQL statement to find running course by ID
     */
	private static final String FIND_RUNNING_COURSE = "SELECT running_courses.id as runningCourseID, running_courses.start, running_courses.end, running_courses.passing, "+
			" courses.id as courseID, courses.courseName, courses.description, users.id as userID, users.name, users.surname "+
			"FROM running_courses "+
			"JOIN courses ON running_courses.courses_id = courses.id "+
			"JOIN users ON running_courses.users_id = users.id  "+
			"WHERE running_courses.id = ?";
	
	/**
     * SQL statement to find all courses for a given student
     */
	private static final String FIND_STUDENT_COURSES = "SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing,"+
			"courses.courseName, courses.description, users.name, users.surname "+ 
			"FROM running_courses "+
			"JOIN courses ON running_courses.courses_id = courses.id "+
			"JOIN users ON running_courses.users_id = users.id "+
			"WHERE running_courses.id IN (SELECT running_courses.id "+ 
										"FROM running_courses "+ 
										"JOIN course_participants ON running_courses.id=course_participants.running_courses_id "+
										"JOIN users ON users.id=course_participants.users_id "+
										"WHERE course_participants.users_id=?)";
	
	/**
     * SQL statement to find all courses for a given teacher
     */
	private static final String FIND_TEACHER_COURSES = "SELECT  running_courses.id, running_courses.start, running_courses.end, running_courses.passing, "+
			"courses.courseName, courses.description "+ 
			"FROM courses "+
			"JOIN running_courses ON courses.id = running_courses.courses_id "+
			"JOIN users ON users.id = running_courses.users_id "+
			"WHERE running_courses.users_id = ?";
	
	/**
     * SQL statement to add running course
     */
	private static final String ADD_RUNNING_COURSE = "INSERT INTO running_courses(courses_id,users_id,start,end,passing) VALUES(?,?,?,?,?)";
	
	/**
     * SQL statement to edit running course
     */
	private static final String EDIT_RUNNING_COURSE = "UPDATE running_courses SET users_id=?,start=?,end=?,passing=? WHERE running_courses.id=?";
	
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
	/**
     * Find all running courses for a given course SQL
     *
     * @param ID of a given course
     * @return List of running courses for a given course
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<RunningCourse> findRunningCourses(int courseID) throws DAOException {
		List<RunningCourse> runningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSES);
			ps.setInt(1, courseID);
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
                
                runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourses;
	}
	
	
	/**
     * Find running course details by the running course ID SQL
     *
     * @param ID of the running course
     * @return RunningCourse on this ID
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public RunningCourse findRunningCourse(int runningCourseID) throws DAOException {
		RunningCourse runningCourse = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RUNNING_COURSE);
			ps.setInt(1, runningCourseID);
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
               
                
                runningCourse = new RunningCourse.Builder()
                			   .withID(Integer.parseInt(resultSet.getString(RUNNING_COURSE_ID)))
                			   .withTeacher(teacher)
                			   .withCourse(course)
                			   .withStart(resultSet.getDate(START).toLocalDate())
                			   .withEnd(resultSet.getDate(END).toLocalDate())
                			   .withPassing(resultSet.getString(PASSING))
                			   .build();
			}			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourse;
	}
	
	
	/**
     * Find all running courses for a given student SQL
     *
     * @param ID of a student
     * @return List of running courses for a given student
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<RunningCourse> findStudentCourses(int userID) throws DAOException {
		List<RunningCourse> runningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_STUDENT_COURSES);
			ps.setInt(1,userID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				Course course = new Course.Builder()
							   .withCourseName(resultSet.getString(COURSE_NAME))
						       .withDescription(resultSet.getString(DESCRIPTION))	
						       .build();
				
				
				User teacher = new User.Builder()
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
				
				runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return runningCourses;
	}
	
	
	/**
     * Find all running courses for a given teacher
     *
     * @param ID of a teacher
     * @return List of running courses for a given teacher
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<RunningCourse> findTeacherCourses(int userID) throws DAOException {
		List<RunningCourse> runningCourses = new ArrayList<RunningCourse>();
		RunningCourse runningCourse;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_TEACHER_COURSES);
			ps.setInt(1, userID);
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

				runningCourses.add(runningCourse);
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }	
        return runningCourses;
	}

	
	/**
     * Adds running course SQL
     *
     * @param RunningCourse to add
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void addRunningCourse(RunningCourse runningCourse) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_RUNNING_COURSE); 
			
			ps.setInt(1, runningCourse.getCourse().getId());
			ps.setInt(2, runningCourse.getTeacher().getId());
			ps.setDate(3, Date.valueOf(runningCourse.getStart()));
			ps.setDate(4, Date.valueOf(runningCourse.getEnd()));
			ps.setString(5, runningCourse.getPassing());	
			
			ps.executeUpdate();
			
		}catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}

	
	/**
     * Edits running course SQL
     *
     * @param RunningCourse to edit
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void editRunningCourse(RunningCourse editedRunningCourse) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(EDIT_RUNNING_COURSE); 

			ps.setInt(1, editedRunningCourse.getTeacher().getId());
			ps.setDate(2, Date.valueOf(editedRunningCourse.getStart()));
			ps.setDate(3, Date.valueOf(editedRunningCourse.getEnd()));
			ps.setString(4, editedRunningCourse.getPassing());		
			ps.setInt(5, editedRunningCourse.getId());
			
			ps.executeUpdate();
			
		}catch (SQLException e) {
			logger.error(e);
			throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}
}
