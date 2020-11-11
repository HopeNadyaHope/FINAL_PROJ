package by.epam.lobanok.dao;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Result;
/**
 * Interface of ResultDAO 
 *
 * @author hope_nadya_hope
 */
public interface ResultDAO {
	/**
     * Gets the user's result for the running course 
     *
     * @param ID of student
     * @param ID of running course
     * @return user's course result
     * @throws DAOException if an DAO error occurs
     */
	Result getCourseResult(int studentID, int runningCourseID) throws DAOException;
	
	/**
     * Adds user's result
     *
     * @param ID of CourseParticipant
     * @param user's course rating
     * @param user's course review
     * @throws DAOException if an DAO error occurs
     */
	void addResult(int coursePartisipantID, int rating, String review) throws DAOException;
}
