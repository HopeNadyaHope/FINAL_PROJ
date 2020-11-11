package by.epam.lobanok.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.validator.UserValidator;
import by.epam.lobanok.entity.RegistrationData;
import by.epam.lobanok.service.RegistrationService;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.exception.DublicateUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;

public class Registration implements Command {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String AGE = "age";
	private static final String SEX = "sex";
	private static final String EMAIL = "email";
	private static final String ROLE = "role";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String EXCEPTION_MESSAGE = "exceptionMessage";
	private static final String SERVER_EXCEPTION = "Ошибка сервера";
	private static final String DUBLICATE_LOGIN = "Пользователь с таким логином существует";
	private static final String UNCORRECT_DATA = "Некорректные данные";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String MAIN_PAGE = "jsp/main.jsp";
	private static final String REGISTRATION_PAGE = "jsp/registration.jsp";	

	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {	
		
		RegistrationData regData;
		regData = new RegistrationData.Builder()
				.withLogin(request.getParameter(LOGIN))
				.withPassword(request.getParameter(PASSWORD))
				.withName(request.getParameter(NAME))
				.withSurname(request.getParameter(SURNAME))
				.withAge(Integer.parseInt(request.getParameter(AGE)))
				.withSex(request.getParameter(SEX))
				.withEmail(request.getParameter(EMAIL))
				.withRole(request.getParameter(ROLE))
				.build();
		
		if(!UserValidator.getInstance().validateRegistrationData(regData)) {			
			request.setAttribute(SERVER_EXCEPTION, UNCORRECT_DATA);			
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);		
		}

		RegistrationService registrationService = ServiceFactory.getInstance().getRegistrationService();
		try {				
			registrationService.registration(regData);
			request.setAttribute(EXCEPTION_MESSAGE, null);
			request.getRequestDispatcher(MAIN_PAGE).forward(request, response); 
			
		}catch(DublicateUserServiceException e) {
			request.setAttribute(EXCEPTION_MESSAGE, DUBLICATE_LOGIN);
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
		
		}catch(ServiceException e) {	
			request.setAttribute(SERVER_EXCEPTION, SERVER_EXCEPTION);
			request.getRequestDispatcher(REGISTRATION_PAGE).forward(request, response);
		}
	}	
}