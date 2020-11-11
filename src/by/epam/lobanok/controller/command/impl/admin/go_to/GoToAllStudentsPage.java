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

public class GoToAllStudentsPage implements Command{
	private static final String STUDENTS = "students";

	/////////////////////////////////////////////////////////////////////////////////////////////
	private static final String ALL_STUDENTS_PAGE = "WEB-INF/jsp/admin/allStudentsPage.jsp";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ServiceException {
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> students;
		students = userService.findAllStudents();		
		request.setAttribute(STUDENTS, students);
		
		request.getRequestDispatcher(ALL_STUDENTS_PAGE).forward(request, response);		
	}
}
