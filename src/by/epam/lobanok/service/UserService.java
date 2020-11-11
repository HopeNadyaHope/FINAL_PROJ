package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface UserService {

	void editProfile(User editedUser) throws ServiceException; 
	void updatePhotoURL(int userID, String photoURL) throws ServiceException;
	List<User> findAllTeachers() throws ServiceException; 
	List<User> findAllStudents() throws ServiceException;
	User findUserByID(int userID) throws ServiceException;
}
