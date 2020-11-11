package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.Result;
import by.epam.lobanok.entity.RunningCourse;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ResultService;
import by.epam.lobanok.service.RunningCourseService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToStudentCourseResultPage implements Command{
	private static final String RUNNING_COURSE = "runningCourse";
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String USER = "user";
	private static final String COURSE_RESULT = "courseResult";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String USER_COURSES_RESULT_PAGE = "WEB-INF/jsp/studentCourseResultPage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		User student = (User)request.getSession().getAttribute(USER);
		int runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		
		ResultService resultService = ServiceFactory.getInstance().getResultService();		
		Result result;
		result = resultService.getCourseResult(student, runningCourseID);	
		request.setAttribute(COURSE_RESULT, result);

		RunningCourseService runningcourseService = ServiceFactory.getInstance().getRunningCourseService();
		RunningCourse runningCourse;
		runningCourse = runningcourseService.findRunningCourse(runningCourseID);
		request.setAttribute(RUNNING_COURSE, runningCourse);
		
		request.getRequestDispatcher(USER_COURSES_RESULT_PAGE).forward(request, response);			
	}
}
