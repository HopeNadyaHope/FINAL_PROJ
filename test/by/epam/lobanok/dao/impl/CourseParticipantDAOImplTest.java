package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import by.epam.lobanok.dao.CourseParticipantDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;

public class CourseParticipantDAOImplTest {
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private static final String FIND_COURSE_PARTICIPANTS = "SELECT course_participants.id, users.id as userID, users.name, users.surname, "+
			"results.rating, results.review "+
			"FROM course_participants "+
			"JOIN users ON course_participants.users_id = users.id "+
			"LEFT OUTER JOIN results on course_participants.results_id = results.id "+
			"WHERE course_participants.running_courses_id = ? " +
			"ORDER BY users.name";
	
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
	@Test
	public void findCourseParticipantsTest() {
		List<CourseParticipant> expectedCourseParticipants = new ArrayList<CourseParticipant>();
		CourseParticipant courseParticipant;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_COURSE_PARTICIPANTS);
			ps.setInt(1, 1);
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
                
                expectedCourseParticipants.add(courseParticipant);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO(); 		
		List<CourseParticipant> actualCourseParticipants = null;
		try {
			actualCourseParticipants = courseParticipantDAO.findCourseParticipants(1);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedCourseParticipants, actualCourseParticipants);
	}
	
	
	@Test
	public void findCourseParticipantResultTest() {
		int studentID;
		studentID = 2;
		
		List<CourseParticipant> expectedCoursesParticipantResults = new ArrayList<CourseParticipant>();
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

                expectedCoursesParticipantResults.add(courseParticipantResult);
			}
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	pool.closeConnection(con, ps);
        }
		
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO(); 		
		List<CourseParticipant> actualCoursesParticipantResults = null;
		try {
			actualCoursesParticipantResults = courseParticipantDAO.findCoursesParticipantResults(studentID);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		org.junit.Assert.assertEquals(expectedCoursesParticipantResults, actualCoursesParticipantResults);
	}
	
}
