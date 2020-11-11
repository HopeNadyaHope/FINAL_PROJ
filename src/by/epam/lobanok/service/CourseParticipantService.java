package by.epam.lobanok.service;

import java.util.List;

import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.service.exception.ServiceException;

public interface CourseParticipantService {
	List<CourseParticipant> findCoursePartucepants(int runningCourseID) throws ServiceException;	
	boolean addCourseParticipant(int studentID, int runningCourseID) throws ServiceException;
	List<CourseParticipant> getCoursesParticipantResults(int studentID) throws ServiceException;
	void deleteCourseParticipant(int studentID, int runningCourseID) throws ServiceException;
}
