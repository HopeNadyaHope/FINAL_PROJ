package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.UserDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class UserServiceImpl implements UserService{	

	@Override
	public void editProfile(User editedUser) throws ServiceException {
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try { 
			userDAO.editProfile(editedUser); 
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
	}
	

	@Override
	public List<User> findAllTeachers() throws ServiceException {
		List<User> teachers = null;
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try {
			teachers = userDAO.findTeachers();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return teachers;
	}

	
	@Override
	public List<User> findAllStudents() throws ServiceException {
		List<User> students = null;
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try {
			students = userDAO.findStudents();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return students;
	}

	@Override
	public void updatePhotoURL(int userID, String photoURL) throws ServiceException {
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try { 
			userDAO.updatePhotoURL(userID, photoURL); 
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
	}


	@Override
	public User findUserByID(int userID) throws ServiceException {
		User user;
		UserDAO userDAO = DAOFactory.getInstance().getUserDAO(); 
		try { 
			user = userDAO.findUserByID(userID);
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
		return user;
	}
}
