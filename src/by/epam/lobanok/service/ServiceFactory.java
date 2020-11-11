package by.epam.lobanok.service;

import by.epam.lobanok.service.impl.CourseParticipantServiceImpl;
import by.epam.lobanok.service.impl.CourseServiceImpl;
import by.epam.lobanok.service.impl.EntranceServiceImpl;
import by.epam.lobanok.service.impl.RegistrationServiceImpl;
import by.epam.lobanok.service.impl.ResultServiceImpl;
import by.epam.lobanok.service.impl.RunningCourseServiceImpl;
import by.epam.lobanok.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private final EntranceService enterenceService = new EntranceServiceImpl();
	private final RegistrationService registrationService = new RegistrationServiceImpl();
	private final UserService userService = new UserServiceImpl();
	private final CourseService courseService = new CourseServiceImpl();
	private final RunningCourseService runningCourseService = new RunningCourseServiceImpl();
	private final ResultService resultService = new ResultServiceImpl();
	private final CourseParticipantService courseParticipantService = new CourseParticipantServiceImpl();

	/////////////////////////////////////////////////////////////////////////////////////////////
	private ServiceFactory() {}
	
	public static ServiceFactory getInstance() {
		return instance;
	}

	public EntranceService getEntranceService() {
		return enterenceService;
	}

	public RegistrationService getRegistrationService() {
		return registrationService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public CourseService getCourseService() {
		return courseService;
	}
	
	public RunningCourseService getRunningCourseService() {
		return runningCourseService;
	}
	
	public ResultService getResultService() {
		return resultService;
	}
	
	public CourseParticipantService getCourseParticipantService() {
		return courseParticipantService;
	}
}
