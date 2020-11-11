package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class EditProfile implements Command {
	private static final String USER = "user";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String GO_TO_USER_PAGE = "Controller?command=go_to_user_page";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		
		User user = (User)request.getSession().getAttribute(USER);
		int userID;
		userID = user.getId();
			
		User editedUser;
		editedUser = new User.Builder()
					.withID(userID)
					.withName(request.getParameter(NAME))
					.withSurname(request.getParameter(SURNAME))
					.withAge(Integer.parseInt(request.getParameter(AGE)))
					.withSex(request.getParameter(SEX))
					.withEmail(request.getParameter(EMAIL))
					.withRole(request.getParameter(ROLE))
					.withPhotoURL(user.getPhotoURL())
					.build();
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		userService.editProfile(editedUser);
		
		request.getSession(true).setAttribute(USER, editedUser);
		response.sendRedirect(GO_TO_USER_PAGE);
	}
}
