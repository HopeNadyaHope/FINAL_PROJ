package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.CourseParticipantDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

/**
 * Implementation of CourseParticipantDAO 
 *
 * @author hope_nadya_hope
 */
public class CourseParticipantDAOImpl implements CourseParticipantDAO{
	/**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a CourseParticipantDAO.class
     */
	private static final Logger logger = LogManager.getLogger(CourseParticipantDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to find all course participants for a given running course
     */
	private static final String FIND_COURSE_PARTICIPANTS = "SELECT course_participants.id, users.id as userID, users.name, users.surname, "+
			"results.rating, results.review "+
			"FROM course_participants "+
			"JOIN users ON course_participants.users_id = users.id "+
			"LEFT OUTER JOIN results on course_participants.results_id = results.id "+
			"WHERE course_participants.running_courses_id = ? " +
			"ORDER BY users.name";
	
	/**
     * SQL statement to check if the student is a course participant of a given course
     */
	private static final String IS_COURSE_PARTICIPANT = "SELECT * FROM course_participants " + 
			"WHERE course_participants.users_id=? AND course_participants.running_courses_id=?";
	
	/**
     * SQL statement to find all courses participant results for the student
     */
	private static final String FIND_COURSES_PARTICIPANT_RESULTS ="SELECT running_courses.id, running_courses.start, faculty.running_courses.end, running_courses.passing, " + 
			"courses.id AS courseID, courses.courseName, courses.description, " + 
			"users.id AS teacherID, users.name, users.surname, " + 
			"results.rating, results.review " + 
			"FROM course_participants " + 
			"LEFT OUTER JOIN results ON course_participants.results_id = results.id " + 
			"JOIN running_courses ON course_participants.running_courses_id = running_courses.id " + 
			"JOIN courses ON running_courses.courses_id = courses.id " + 
			"JOIN users ON running_courses.users_id = users.id " + 
			"WHERE course_participants.id IN" + 
			"(SELECT course_participants.id FROM course_participants WHERE course_participants.users_id=?)";
	
	/**
     * SQL statement to add course participant
     */
	private static final String ADD_COURSE_PARTICIPANT = "INSERT INTO course_participants(users_id, running_courses_id) VALUES(?,?)";

	/**
     * SQL statement to delete course participant
     */
	private static final String DELETE_COURSE_PARTICIPANT = "DELETE course_participants, results " + 
			"FROM course_participants " + 
			"LEFT OUTER JOIN results ON course_participants.results_id = results.id " + 
			"WHERE course_participants.users_id = ? AND course_participants.running_courses_id = ?";
	
	/////////////////////////////////////////////////////////////////////////////////////
	private static final String ID = "id";
	private static final String USER_ID = "userID";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String COURSE_ID = "courseID";
	private static final String TEACHER_ID = "teacherID";
	
	private static final String RATING = "rating";
	private static final String REVIEW = "review";	
	
	private static final String COURSE_NAME = "courseName";
	private static final String DESCRIPTION = "description";
	private static final String PASSING = "passing";
	private static final String START = "start";
	private static final String END = "end";	

	/////////////////////////////////////////////////////////////////////////////////////
	
	/**
     * Finds all participants for a given running course SQL
     * 
     * @param ID of a running course
     * @return list of course participants 
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<CourseParticipant> findCourseParticipants(int runningCourseID) throws DAOException {
		List<CourseParticipant> courseParticipants = new ArrayList<CourseParticipant>();
		CourseParticipant courseParticipant;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE_PARTICIPANTS);
			ps.setInt(1, runningCourseID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {                
				User student = new User.Builder()
							   .withID(Integer.parseInt(resultSet.getString(USER_ID)))
							   .withName(resultSet.getString(NAME))
							   .withSurname(resultSet.getString(SURNAME))
							   .build();
				 
                Result result = new Result();
                if(resultSet.getString(RATING) != null) {
                	result.setRating(Integer.parseInt(resultSet.getString(RATING)));
                }
                result.setReview(resultSet.getString(REVIEW));
                
                courseParticipant = new CourseParticipant.Builder()
                					.withID(Integer.parseInt(resultSet.getString(ID)))
                					.withStudent(student)
                					.withResult(result)
                					.build();
                
                courseParticipants.add(courseParticipant);
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return courseParticipants;
	}

	/**
     * Finds courses participant result details for student SQL
     *
     * @param ID of a student
     * @return list of courses participant with this student id
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public List<CourseParticipant> findCoursesParticipantResults(int studentID) throws DAOException {
		List<CourseParticipant> coursesParticipantResults = new ArrayList<CourseParticipant>();
		CourseParticipant courseParticipantResult;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSES_PARTICIPANT_RESULTS);
			ps.setInt(1, studentID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                User student = new User.Builder()
                			  .withID(studentID)
                			  .build();
                
                Course course = new Course.Builder()
                			   .withID(Integer.parseInt(resultSet.getString(COURSE_ID)))
                		       .withCourseName(resultSet.getString(COURSE_NAME))
                		       .withDescription(resultSet.getString(DESCRIPTION))
                		       .build();   
                
                User teacher = new User.Builder()
                			  .withID(Integer.parseInt(resultSet.getString(TEACHER_ID)))
                			  .withName(resultSet.getString(NAME))
                              .withSurname(resultSet.getString(SURNAME))
                              .build();
                
                RunningCourse runningCourse = new RunningCourse.Builder()
						 					 .withID(Integer.parseInt(resultSet.getString(ID)))
						 					 .withCourse(course)
						 					 .withTeacher(teacher)
						 					 .withStart(resultSet.getDate(START).toLocalDate())
						 					 .withEnd(resultSet.getDate(END).toLocalDate())
						 					 .withPassing(resultSet.getString(PASSING))
						 					 .build();
                
                
                Result result = new Result();
                if(resultSet.getString(RATING) != null) {
                	result.setRating(Integer.parseInt(resultSet.getString(RATING)));
                }
                result.setReview(resultSet.getString(REVIEW));
                
                
                courseParticipantResult = new CourseParticipant.Builder()
                						 .withStudent(student)		
                						 .withRunningCourse(runningCourse)
                						 .withResult(result)
                						 .build();

                coursesParticipantResults.add(courseParticipantResult);
			}
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return coursesParticipantResults;
	}
	
	
	/**
     * Adds participant to a running course SQL
     *
     * @param ID of a student
     * @param ID of a running course
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public boolean addCourseParticipant(int studentID, int runningCourseID) throws DAOException {
		if(isCourseParticipant(studentID,runningCourseID)) {
			return false;
		}
		
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_COURSE_PARTICIPANT, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		return true;		
	}

	
	/**
     * Checks if the student is a course participant of a given running course
     *
     * @param ID of a student
     * @param ID of a running course
     * @throws DAOException if an DAO error occurs
     */
	private boolean isCourseParticipant(int studentID, int runningCourseID) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(IS_COURSE_PARTICIPANT);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			resultSet = ps.executeQuery();
			
			if(resultSet.next()) {
				return true;
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
        return false;
	}

	
	/**
     * Delete a course participant SQL
     * 
     * @param ID of a student
     * @param ID of a running course
     * @throws DAOException if an DAO error occurs
     */
	public void deleteCourseParticipant(int studentID, int runningCourseID) throws DAOException{
		Connection con = null;
		PreparedStatement ps = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(DELETE_COURSE_PARTICIPANT);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}	
}
