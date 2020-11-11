package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.RunningCourse;
/**
 * Interface of RunningCourseDAO 
 *
 * @author hope_nadya_hope
 */
public interface RunningCourseDAO {
	/**
     * Find all running courses for a given course 
     *
     * @param ID of a given course
     * @return List of running courses for a given course
     * @throws DAOException if an DAO error occurs
     */
	List<RunningCourse> findRunningCourses(int courseID) throws DAOException;
	
	/**
     * Find running course details by the running course ID 
     *
     * @param ID of the running course
     * @return RunningCourse on this ID
     * @throws DAOException if an DAO error occurs
     */
	RunningCourse findRunningCourse(int runningCourseID) throws DAOException;
	
	/**
     * Find all running courses for a given student
     *
     * @param ID of a student
     * @return List of running courses for a given student
     * @throws DAOException if an DAO error occurs
     */
	List<RunningCourse> findStudentCourses(int userID) throws DAOException;
	
	/**
     * Find all running courses for a given teacher
     *
     * @param ID of a teacher
     * @return List of running courses for a given teacher
     * @throws DAOException if an DAO error occurs
     */
	List<RunningCourse> findTeacherCourses(int userID) throws DAOException;	
	
	/**
     * Adds running course
     *
     * @param RunningCourse to add
     * @throws DAOException if an DAO error occurs
     */
	void addRunningCourse(RunningCourse runningCourse) throws DAOException;
	
	/**
     * Edits running course
     *
     * @param RunningCourse to edit
     * @throws DAOException if an DAO error occurs
     */
	void editRunningCourse(RunningCourse editedRunningCourse) throws DAOException;
}
