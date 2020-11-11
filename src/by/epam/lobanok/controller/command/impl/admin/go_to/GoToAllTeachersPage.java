package by.epam.lobanok.controller.command.impl.admin.go_to;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.lobanok.controller.command.Command;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.ServiceFactory;
import by.epam.lobanok.service.UserService;
import by.epam.lobanok.service.exception.ServiceException;

public class GoToAllTeachersPage implements Command{
	private static final String TEACHERS = "teachers";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ALL_TEACHERS_PAGE = "WEB-INF/jsp/admin/allTeachersPage.jsp";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> teachers;
		teachers = userService.findAllTeachers();		
		request.setAttribute(TEACHERS, teachers);
		
		request.getRequestDispatcher(ALL_TEACHERS_PAGE).forward(request, response);		
	}
}
