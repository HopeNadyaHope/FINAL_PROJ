package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface RunningCourseService {
	List<RunningCourse> findUserCourses(User user) throws ServiceException;
	List<RunningCourse> findRunningCourses(int courseID) throws ServiceException;	
	RunningCourse findRunningCourse(int runningCourseID) throws ServiceException;
	void addRunningCourse(RunningCourse runningCourse) throws ServiceException;
	void editRunningCourse(RunningCourse editedRunningCourse) throws ServiceException;
}
