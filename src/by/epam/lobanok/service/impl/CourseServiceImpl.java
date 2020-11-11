package by.epam.lobanok.service.impl;

import java.util.List;

import by.epam.lobanok.dao.CourseDAO;
import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.exception.ServiceException;

public class CourseServiceImpl implements CourseService{

	@Override
	public List<Course> findCourses() throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		
		List<Course> courses;
		try {
			courses = courseDAO.findCourses();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return courses;
	}
	
	
	@Override
	public Course findCourse(int courseID) throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 		
		Course course = null;	
		try {
			course = courseDAO.findCourse(courseID);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return course;
	}

	@Override
	public void editCourse(Course editedCourse) throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		try {
			courseDAO.editCourse(editedCourse);	
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addCourse(Course course) throws ServiceException {
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO(); 
		try {
			courseDAO.addCourse(course);	
		}catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
