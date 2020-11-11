package by.epam.lobanok.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import by.epam.lobanok.dao.ResultDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.pool.ConnectionPool;
import by.epam.lobanok.entity.Result;

/**
 * Implementation  of ResultDAO 
 *
 * @author hope_nadya_hope
 */
public class ResultDAOImpl implements ResultDAO{
	/**
     * Instance of a connection pool
     */
	private static final ConnectionPool pool = ConnectionPool.getInstance();
	
	/**
     * Logger for a ResultDAO.class
     */
	private static final Logger logger = LogManager.getLogger(ResultDAO.class);
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
     * SQL statement to find the user's result of a running course
     */
	private static final String FIND_RESULT = "SELECT results.rating, results.review "+
							"FROM course_participants "+
							"JOIN users ON users.id=course_participants.users_id "+ 
							"LEFT OUTER JOIN results on course_participants.results_id = results.id " +
							"WHERE course_participants.users_id=? AND course_participants.running_courses_id =?";
	
	/**
     * SQL statement to add result to all results
     */
	private static final String ADD_RESULT_TO_RESULTS = "INSERT INTO results(rating,review) VALUES(?,?)";
	
	/**
     * SQL statement to add result to course participant
     */
	private static final String ADD_RESULT_TO_COURSE_PARTICIPANTS = "UPDATE course_participants SET results_id=? WHERE id=?";
	
	/////////////////////////////////////////////////////////////////////////////////////
	private static final String RATING = "rating";
	private static final String REVIEW = "review";
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
     * Gets the user's result for the running course SQL
     *
     * @param ID of student
     * @param ID of running course
     * @return user's course result
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public Result getCourseResult(int studentID, int runningCourseID) throws DAOException {
		Result result = null;
		
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
                result = new Result();
                if(resultSet.getString(RATING) != null) {
                	result.setRating(Integer.parseInt(resultSet.getString(RATING)));
                }
                result.setReview(resultSet.getString(REVIEW));
			}
			
		}catch (SQLException e) {
			logger.info("DAOException");
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }		
        return result;
	}

	/**
     * Adds user's result SQL
     *
     * @param ID of CourseParticipant
     * @param user's course rating
     * @param user's course review
     * @throws DAOException if an DAO error occurs
     */
	@Override
	public void addResult(int coursePartisipantID, int rating, String review) throws DAOException {
		int resultID;
		resultID = addResultToResults(rating, review);
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = pool.takeConnection();			
			ps = con.prepareStatement(ADD_RESULT_TO_COURSE_PARTICIPANTS);
			ps.setInt(1, resultID);
			ps.setInt(2, coursePartisipantID);
			
			ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
	}

	/**
     * Adds user's result to Results Table
     *
     * @param user's course rating
     * @param user's course review
     * @throws DAOException if an DAO error occurs
     */
	private int addResultToResults(int rating, String review) throws DAOException {
		Connection con = null;
		PreparedStatement ps = null;
		int resultID = 0;
		try {
			con = pool.takeConnection();
			ps = con.prepareStatement(ADD_RESULT_TO_RESULTS, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, rating);
			ps.setString(2, review);			
			ps.executeUpdate();
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if(generatedKeys.next()) {
				resultID = generatedKeys.getInt(1);
			}
			
		}catch (SQLException e) {
			logger.error(e);
            throw new DAOException(e);
        } finally {
        	pool.closeConnection(con, ps);
        }
		return resultID;		
	}

}
