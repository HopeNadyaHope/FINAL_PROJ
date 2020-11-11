package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.ResultService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class AddCourseResult implements Command{
	private static final String RUNNING_COURSE_ID = "runningCourseID";
	private static final String COURSE_PARTICIPANT_ID = "courseParticipantID";
	private static final String RATING = "rating";
	private static final String REVIEW = "review";
	
	/////////////////////////////////////////////////////////////////////////////////////////////	
	private static final String COURSE_PARTICIPANTS_PAGE = "Controller?command=go_to_teacher_course_participants_page&runningCourseID=";
	
	/////////////////////////////////////////////////////////////////////////////////////////////	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		int runningCourseID;
		int courseParticipantID;
		int rating;
		String review;

		runningCourseID = Integer.parseInt(request.getParameter(RUNNING_COURSE_ID));
		courseParticipantID = Integer.parseInt(request.getParameter(COURSE_PARTICIPANT_ID));
		rating = Integer.parseInt(request.getParameter(RATING));
		review = request.getParameter(REVIEW);
		
		ResultService resultService = ServiceFactory.getInstance().getResultService();	
		resultService.addResult(courseParticipantID, rating, review);
		
		response.sendRedirect(COURSE_PARTICIPANTS_PAGE + runningCourseID);			
	}
}