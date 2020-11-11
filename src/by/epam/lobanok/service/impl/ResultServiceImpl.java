package by.epam.lobanok.service.impl;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.ResultDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ResultService;
import by.epam.lobanok.service.exception.ServiceException;

public class ResultServiceImpl implements ResultService{

	@Override
	public Result getCourseResult(User student, int runningCourseID) throws ServiceException {
		ResultDAO resultDAO = DAOFactory.getInstance().getResultDAO(); 
		
		int studentID;	
		studentID = student.getId();
		
		Result result;
		try {
			result = resultDAO.getCourseResult(studentID, runningCourseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public void addResult(int сoursePartisipantID, int rating, String review) throws ServiceException {
		ResultDAO resultDAO = DAOFactory.getInstance().getResultDAO(); 
		try {
			resultDAO.addResult(сoursePartisipantID, rating, review);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
