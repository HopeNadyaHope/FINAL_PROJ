package by.epam.lobanok.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.controller.command.CommandProvider;
import by.epam.lobanok.service.exception.ServiceException;

@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final CommandProvider commands = new CommandProvider();
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String CONTROLLER = "Controller";
	
	private static final String COMMAND = "command";
	private static final String LOCALIZATION = "localization";
	private static final String LAST_COMMAND = "lastCommand";
	
	private static final String ERROR_PAGE = "WEB-INF/errorPage.jsp";

	/////////////////////////////////////////////////////////////////////////////////////////////
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		
		String currentCommand; 
		Command command;		  
		currentCommand = request.getParameter(COMMAND);	
		
		if(!currentCommand.equals(LOCALIZATION)) {
		  request.getSession(true).setAttribute(LAST_COMMAND, CONTROLLER + "?" + request.getQueryString()); 
		}
		
		command = commands.getCommand(currentCommand); 
		try {
			command.execute(request, response);
		}catch(ServiceException e) {
			request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
		}
	}
}