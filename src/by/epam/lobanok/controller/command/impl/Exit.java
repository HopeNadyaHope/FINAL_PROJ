package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.service.exception.ServiceException;

public class Exit implements Command {

	private static final String GO_TO_MAIN_PAGE = "Controller?command=go_to_main_page";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {		
		request.getSession().invalidate();		
		response.sendRedirect(GO_TO_MAIN_PAGE);
	}
}
