package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.ResultDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Result;

public class ResultDAOImplTest {
private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	private static final String FIND_RESULT = "SELECT results.rating, results.review "+
		"FROM course_participants "+
		"JOIN users ON users.id=course_participants.users_id "+ 
		"LEFT OUTER JOIN results on course_participants.results_id = results.id " +
		"WHERE course_participants.users_id=? AND course_participants.running_courses_id =?";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String RATING = "rating";
	private static final String REVIEW = "review";
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void findCourseResultTest() {
		int studentID;
		int runningCourseID;
		studentID = 1;
		runningCourseID = 1;
		
		Result expectedResult = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;		
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(FIND_RESULT);
			ps.setInt(1, studentID);
			ps.setInt(2, runningCourseID);
			resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
                expectedResult = new Result();
                if(resultSet.getString(RATING) != null) {
                	expectedResult.setRating(Integer.parseInt(resultSet.getString(RATING)));
                }
                expectedResult.setReview(resultSet.getString(REVIEW));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {			
			if(con != null) {
				try{
					con.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		ResultDAO resultDAO = DAOFactory.getInstance().getResultDAO(); 		
		Result actualResult = null;
		try {
			actualResult = resultDAO.getCourseResult(studentID, runningCourseID);
			}catch (DAOException e) { 
				e.printStackTrace();
			} 
		org.junit.Assert.assertEquals(expectedResult, actualResult);
	}
}
