package by.epam.lobanok.controller.command.impl.go_to;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToGuestUserPagePage implements Command {

	private static final String USER_ID = "userID";
	private static final String PERSON = "person";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GUEST_USER_PAGE = "jsp/guestUserPage.jsp";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		int userID;
		userID = Integer.parseInt(request.getParameter(USER_ID));
		User user;
		UserService userService = ServiceFactory.getInstance().getUserService();
		user = userService.findUserByID(userID);
		request.setAttribute(PERSON, user);
		
		request.getRequestDispatcher(GUEST_USER_PAGE).forward(request, response);	
	}

}
