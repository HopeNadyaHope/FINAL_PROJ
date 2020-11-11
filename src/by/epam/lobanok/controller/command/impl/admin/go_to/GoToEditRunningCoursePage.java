package by.epam.lobanok.controller.command.impl.admin.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.RunningCourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToEditRunningCoursePage implements Command{
	private static final String TEACHERS = "teachers";
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String RUNNING_COURSE = "runningCourse";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String EDIT_RUNNING_COURSES_PAGE = "WEB-INF/jsp/admin/editRunningCoursePage.jsp";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		int runningCourseID;
		runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		
		RunningCourseService runningCourseService = ServiceFactory.getInstance().getRunningCourseService();
		RunningCourse runningCourse;
		runningCourse = runningCourseService.findRunningCourse(runningCourseID);
		request.setAttribute(RUNNING_COURSE, runningCourse);	
		
		UserService userService = ServiceFactory.getInstance().getUserService();		List<User> teachers;
		teachers = userService.findAllTeachers();		
		request.setAttribute(TEACHERS, teachers);
		
		request.getRequestDispatcher(EDIT_RUNNING_COURSES_PAGE).forward(request, response);		
	}
}
