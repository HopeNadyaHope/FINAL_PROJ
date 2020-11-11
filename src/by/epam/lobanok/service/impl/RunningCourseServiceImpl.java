package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.RunningCourseDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.RunningCourseService;
import by.epam.lobanok.service.exception.ServiceException;

public class RunningCourseServiceImpl implements RunningCourseService {
	private static final String STUDENT = "студент";
	private static final String TEACHER = "преподаватель";

	/////////////////////////////////////////////////////////////////////////////////////////////
	
	public List<RunningCourse> findUserCourses(User user) throws ServiceException {
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 		
		List<RunningCourse> runningCourses = null;
		
		int userID;
		userID = user.getId();
		
		try {
			switch(user.getRole()) {
			case STUDENT:
				runningCourses = runningCourseDAO.findStudentCourses(userID);
				break;
			case TEACHER:
				runningCourses  = runningCourseDAO.findTeacherCourses(userID);	
				for(RunningCourse runningCourse: runningCourses) {
					runningCourse.setTeacher(user);
				}
				break;
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return runningCourses;
	}
	
	
	@Override
	public List<RunningCourse> findRunningCourses(int courseID) throws ServiceException {
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 
		
		List<RunningCourse> runningCourses;
		try {
			runningCourses = runningCourseDAO.findRunningCourses(courseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return runningCourses;
	}

	
	@Override
	public RunningCourse findRunningCourse(int runningCourseID) throws ServiceException {
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 
		RunningCourse runningCourse;
		try {
			runningCourse = runningCourseDAO.findRunningCourse(runningCourseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return runningCourse;
	}


	@Override
	public void addRunningCourse(RunningCourse runningCourse) throws ServiceException {
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 
		try {
			runningCourseDAO.addRunningCourse(runningCourse);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public void editRunningCourse(RunningCourse editedRunningCourse) throws ServiceException {
		RunningCourseDAO runningCourseDAO = DAOFactory.getInstance().getRunningCourseDAO(); 
		try {
			runningCourseDAO.editRunningCourse(editedRunningCourse);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
