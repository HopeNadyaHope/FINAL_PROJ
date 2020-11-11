package by.epam.lobanok.controller.command.impl.admin.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToAddCoursePage implements Command {
	private static final String ADD_COURSE_PAGE = "WEB-INF/jsp/admin/addCoursePage.jsp";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		request.getRequestDispatcher(ADD_COURSE_PAGE).forward(request, response);				
	}
}
