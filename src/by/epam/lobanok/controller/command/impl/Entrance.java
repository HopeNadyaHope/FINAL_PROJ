package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.validator.UserValidator;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.EntranceService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.NoSuchUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;


public class Entrance implements Command {
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final  String LOGIN = "login";
	private static final String PASSWORD = "password";	
	private static final String USER = "user";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String EXCEPTION_MESSAGE_ATTRIBUTE = "&exceptionMessage=";
	private static final String SERVER_EXCEPTION = "serverException";
	private static final String NO_SUCH_USER = "noSuchUser";
	private static final String UNCORRECT_DATA = "uncorrectData";
	
	/////////////////////////////////////////////////////////////////////////////////////////////	
	private static final String GO_TO_USER_PAGE = "Controller?command=go_to_user_page";
	private static final String GO_TO_MAIN_PAGE = "Controller?command=go_to_main_page";
		
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException{			
		String page;
		
		EntranceData entrData;
		entrData = new EntranceData.Builder()
				  .withLogin(request.getParameter(LOGIN))
				  .withPassword(request.getParameter(PASSWORD))
				  .build();
		
		if(!UserValidator.getInstance().validateEntranceData(entrData)) {
			page = GO_TO_MAIN_PAGE + EXCEPTION_MESSAGE_ATTRIBUTE + UNCORRECT_DATA;
		}
		
		EntranceService entranceService = ServiceFactory.getInstance().getEntranceService();
		User user;
		try {
			user = entranceService.entrance(entrData);
			request.getSession(true).setAttribute(USER, user);			
			page = GO_TO_USER_PAGE;		
		}catch(NoSuchUserServiceException e) {	
			page = GO_TO_MAIN_PAGE + EXCEPTION_MESSAGE_ATTRIBUTE + NO_SUCH_USER;
			
		}catch(ServiceException e) {
			page = GO_TO_MAIN_PAGE + EXCEPTION_MESSAGE_ATTRIBUTE + SERVER_EXCEPTION;
		}
		response.sendRedirect(page);
	}	
}
