package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.CourseParticipant;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.CourseParticipantService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToStudentCoursesResultsPage implements Command {
	private static final String USER = "user";
	private static final String RESULTS = "results";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String STUDENT_RESULTS_PAGE = "WEB-INF/jsp/studentResultsPage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		User student = (User)request.getSession().getAttribute(USER);
		int studentID;
		studentID = student.getId();
		
		CourseParticipantService courseParticipantService = ServiceFactory.getInstance().getCourseParticipantService();
		List<CourseParticipant> results;
		results = courseParticipantService.getCoursesParticipantResults(studentID);
		if(results.size() > 0) {
			request.setAttribute(RESULTS, results);
		}	
		
		request.getRequestDispatcher(STUDENT_RESULTS_PAGE).forward(request, response);	
	}
}
