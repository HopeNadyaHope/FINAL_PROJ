package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.CourseParticipantDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.service.CourseParticipantService;
import by.epam.lobanok.service.exception.ServiceException;

public class CourseParticipantServiceImpl implements CourseParticipantService {

	@Override
	public List<CourseParticipant> findCoursePartucepants(int runningCourseID) throws ServiceException {
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO(); 
		
		List<CourseParticipant> courseParticipants;
		try {
			courseParticipants = courseParticipantDAO.findCourseParticipants(runningCourseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return courseParticipants;
	}

	@Override
	public boolean addCourseParticipant(int studentID, int runningCourseID) throws ServiceException {
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO();
		boolean follow;
		try {
			follow = courseParticipantDAO.addCourseParticipant(studentID,runningCourseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return follow;
	}

	@Override
	public List<CourseParticipant> getCoursesParticipantResults(int studentID) throws ServiceException {
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO(); 
		
		List<CourseParticipant> coursesParticipantResults;
		try {
			coursesParticipantResults = courseParticipantDAO.findCoursesParticipantResults(studentID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return coursesParticipantResults;
	}

	@Override
	public void deleteCourseParticipant(int studentID, int runningCourseID) throws ServiceException {
		CourseParticipantDAO courseParticipantDAO = DAOFactory.getInstance().getCourseParticipantDAO();
		try {
			courseParticipantDAO.deleteCourseParticipant(studentID,runningCourseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
