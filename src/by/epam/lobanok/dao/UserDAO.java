package by.epam.lobanok.dao;

import java.util.List;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.User;
/**
 * Interface of UserDAO 
 *
 * @author hope_nadya_hope
 */
public interface UserDAO {
	/**
     * Edits user
     *
     * @param a user to edit
     * @throws DAOException if an DAO error occurs
     */
	void editProfile(User editedUser) throws DAOException;
	
	/**
     * Finds all teachers in a system
     *
     * @return List of teachers
     * @throws DAOException if an DAO error occurs
     */
	List<User> findTeachers() throws DAOException;
	
	/**
     * Finds all students in a system
     *
     * @return List of students
     * @throws DAOException if an DAO error occurs
     */
	List<User> findStudents() throws DAOException;
	
	/**
     * Updates photoURL for a given user
     *
     * @param ID of a given user
     * @param new photoURL
     * @throws DAOException if an DAO error occurs
     */
	void updatePhotoURL(int userID, String photoURL) throws DAOException;

	/**
     * Find user by id 
     *
     * @param user's id
     * @throws DAOException if an DAO error occurs
     */
	User findUserByID(int userID) throws DAOException;

}
