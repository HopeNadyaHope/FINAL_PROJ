package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseParticipantService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class DeleteCourseParticipant implements Command{
	
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String USER = "user";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GO_TO_STUDENT_COURSES_PAGE = "Controller?command=go_to_user_courses_page";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		User student = (User)request.getSession().getAttribute(USER);
		int runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		
		CourseParticipantService courseParticepantService = ServiceFactory.getInstance().getCourseParticipantService();
		courseParticepantService.deleteCourseParticipant(student.getId(), runningCourseID);
		response.sendRedirect(GO_TO_STUDENT_COURSES_PAGE);
	}
}
