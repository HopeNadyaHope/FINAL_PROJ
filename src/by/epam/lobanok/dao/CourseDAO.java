package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;
/**
 * Interface of CourseDAO 
 *
 * @author hope_nadya_hope
 */
public interface CourseDAO {
	/**
     * Finds all courses
     *
     * @throws DAOException if an DAO error occurs
     */
	List<Course> findCourses() throws DAOException;
	
	/**
     * Finds a course by id
     * 
     * @param ID of course
     * @return course with this ID
     * @throws DAOException if an DAO error occurs
     */
	Course findCourse(int courseID) throws DAOException;
	
	/**
     * Edits a course
     * @param a course to edit
     * @throws DAOException if an DAO error occurs
     */
	void editCourse(Course editedCourse) throws DAOException;
	
	/**
     * Adds a course
     * @param a course to add
     * @throws DAOException if an DAO error occurs
     */
	void addCourse(Course course) throws DAOException;
	
}
