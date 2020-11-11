package by.epam.lobanok.controller.command.impl.admin.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.Course;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToAddRunningCoursePage implements Command {
	private static final String TEACHERS = "teachers";
	private static final String COURSE_ID = "courseID";
	private static final String COURSE = "course";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ADD_RUNNING_COURSES_PAGE = "WEB-INF/jsp/admin/addRunningCoursePage.jsp";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		int courseID;
		courseID = Integer.parseInt(request.getParameter(COURSE_ID));
		
		CourseService courseService = ServiceFactory.getInstance().getCourseService();
		Course course;
		course = courseService.findCourse(courseID);
		request.setAttribute(COURSE, course);		
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> teachers;
		teachers = userService.findAllTeachers();		
		request.setAttribute(TEACHERS, teachers);
		
		request.getRequestDispatcher(ADD_RUNNING_COURSES_PAGE).forward(request, response);		
	}
}