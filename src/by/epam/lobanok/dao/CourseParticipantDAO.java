package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.CourseParticipant;

/**
 * Interface of CourseParticipantDAO 
 *
 * @author hope_nadya_hope
 */
public interface CourseParticipantDAO {
	/**
     * Finds all participants for a given running course
     * 
     * @param ID of a running course
     * @return list of course participants 
     * @throws DAOException if an DAO error occurs
     */
	List<CourseParticipant> findCourseParticipants(int runningCourseID) throws DAOException;
	
	/**
     * Finds courses participant result details for student 
     *
     * @param ID of a student
     * @return list of courses participant with this student id
     * @throws DAOException if an DAO error occurs
     */
	List<CourseParticipant> findCoursesParticipantResults(int studentID) throws DAOException;
	
	/**
     * Adds participant to a running course
     *
     * @param ID of a student
     * @param ID of a running course
     * @throws DAOException if an DAO error occurs
     */
	boolean addCourseParticipant(int studentID, int runningCourseID) throws DAOException;
	
	/**
     * Delete a course participant
     * 
     * @param ID of a student
     * @param ID of a running course
     * @throws DAOException if an DAO error occurs
     */
	void deleteCourseParticipant(int studentID, int runningCourseID) throws DAOException;
}
