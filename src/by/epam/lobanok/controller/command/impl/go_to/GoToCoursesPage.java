package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToCoursesPage implements Command {

	private static final String COURSES = "courses";
	private static final String COURSES_PAGE = "jsp/courses.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		CourseService courseService = ServiceFactory.getInstance().getCourseService();
		List<Course> courses;
		courses = courseService.findCourses();		
		request.setAttribute(COURSES, courses);
		
		request.getRequestDispatcher(COURSES_PAGE).forward(request, response);		
	}
}
