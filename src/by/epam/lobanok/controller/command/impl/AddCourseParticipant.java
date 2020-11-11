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

public class AddCourseParticipant implements Command{
	
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String USER = "user";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GO_TO_STUDENT_COURSES_RESULT_PAGE = "Controller?command=go_to_student_course_result_page&runningCourseID=";
	private static final String MESSAGE_ATTRIBUTE = "&message=";
	private static final String ALREADY = "already";
	private static final String ADDED = "added";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		User student = (User)request.getSession().getAttribute(USER);
		int runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		
		String page;
		page = GO_TO_STUDENT_COURSES_RESULT_PAGE + runningCourseID + MESSAGE_ATTRIBUTE;
		CourseParticipantService courseParticepantService = ServiceFactory.getInstance().getCourseParticipantService();
		if(courseParticepantService.addCourseParticipant(student.getId(), runningCourseID)) {
			page += ADDED;
		}else {
			page += ALREADY;
		}
		response.sendRedirect(page);
	}
}
