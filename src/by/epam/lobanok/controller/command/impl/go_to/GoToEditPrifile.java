package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToEditPrifile implements Command {
	private static final String USER = "user";
	private static final String ERROR_PAGE = "WEB-INF/errorPage.jsp";
	private static final String USER_EDIT_PROFILE_PAGE = "WEB-INF/jsp/userEditProfilePage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		if((User)request.getSession().getAttribute(USER) == null) {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}else {
			request.getRequestDispatcher(USER_EDIT_PROFILE_PAGE).forward(request, response);	
		}
	}
}
