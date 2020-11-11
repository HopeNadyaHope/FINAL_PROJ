package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToAboutPage implements Command{

	private static final String ABOUT_PAGE = "jsp/about.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(ABOUT_PAGE);
		requestDispatcher.forward(request, response);		
	}
}