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

public class EditCourse implements Command {
	private static final String COURSE_ID = "courseID";
	private static final String COURSE_NAME = "courseName";
	private static final String COURSE_DESCRIPTION = "courseDescription";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GO_TO_COURSES_PAGE = "Controller?command=go_to_courses_page";
	private static final String GO_TO_EDIT_COURSE_PAGE = "Controller?command=go_to_edit_course_page&courseID=";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		int courseID;
		courseID = Integer.parseInt(request.getParameter(COURSE_ID));
		
		Course editedCourse = new Course();
		editedCourse.setId(courseID);
		editedCourse.setCourseName(request.getParameter(COURSE_NAME));
		editedCourse.setDescription(request.getParameter(COURSE_DESCRIPTION));		
		
		String page;
		if(CourseValidator.getInstance().validateCourse(editedCourse)) {
			CourseService courseService = ServiceFactory.getInstance().getCourseService();
			courseService.editCourse(editedCourse);
			page = GO_TO_COURSES_PAGE;
		}else {
			page = GO_TO_EDIT_COURSE_PAGE + courseID;
		}		
		response.sendRedirect(page);
	}
}
