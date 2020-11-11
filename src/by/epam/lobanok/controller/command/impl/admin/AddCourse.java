package by.epam.lobanok.controller.command.impl.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.validator.CourseValidator;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class AddCourse implements Command {
	private static final String COURSE_NAME = "courseName";
	private static final String COURSE_DESCRIPTION = "courseDescription";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GO_TO_COURSES_PAGE = "Controller?command=go_to_courses_page";
	private static final String GO_TO_ADD_COURSE_PAGE = "Controller?command=go_to_add_course_page";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {

		Course course = new Course();
		course.setCourseName(request.getParameter(COURSE_NAME));
		course.setDescription(request.getParameter(COURSE_DESCRIPTION));
		String page;
		page = GO_TO_ADD_COURSE_PAGE;
		if(CourseValidator.getInstance().validateCourse(course)) {			
			CourseService courseService = ServiceFactory.getInstance().getCourseService();
			courseService.addCourse(course);
			page = GO_TO_COURSES_PAGE;
		}				
		response.sendRedirect(page);
	}
}
