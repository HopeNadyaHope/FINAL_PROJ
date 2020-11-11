package by.epam.lobanok.controller.command.impl.go_to;

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
import by.epam.lobanok.service.exception.ServiceException;

public class GoToUserCoursesPage implements Command{

	private static final String USER = "user";
	private static final String ERROR_PAGE = "WEB-INF/errorPage.jsp";
	private static final String RUNNING_COURSES = "runningCourses";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String USER_COURSES_PAGE = "WEB-INF/jsp/userCoursesPage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		User user = (User)request.getSession().getAttribute(USER);
		
		if((user == null)) {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);		
		
		}else {
			RunningCourseService runningcourseService = ServiceFactory.getInstance().getRunningCourseService();
			List<RunningCourse> runningCourses;
			runningCourses = runningcourseService.findUserCourses(user);		
			
			if(runningCourses.size() > 0) {
				request.setAttribute(RUNNING_COURSES, runningCourses);
			}			
			request.getRequestDispatcher(USER_COURSES_PAGE).forward(request, response);	
		}
	}
}