package by.epam.lobanok.dao;

import by.epam.lobanok.dao.impl.CourseDAOImpl;
import by.epam.lobanok.dao.impl.CourseParticipantDAOImpl;
import by.epam.lobanok.dao.impl.EntranceDAOImpl;
import by.epam.lobanok.dao.impl.RegistrationDAOImpl;
import by.epam.lobanok.dao.impl.ResultDAOImpl;
import by.epam.lobanok.dao.impl.RunningCourseDAOImpl;
import by.epam.lobanok.dao.impl.UserDAOImpl;
/** 
 * Class factory for DAO objects
 * @author hope_nadya_hope
 * 
 * */
public final class DAOFactory {
	 /**
     * DAOFactory instance
     */
	private static final DAOFactory instance = new DAOFactory();

	/////////////////////////////////////////////////////////////////////////////////////////////
	 /**
     * EntranceDAO instance
     */
	private final EntranceDAO entranceDAO  = new EntranceDAOImpl();
	
	/**
     * RegistrationDAO instance
     */
	private final RegistrationDAO registrationDAO = new RegistrationDAOImpl();
	
	/**
     * UserDAO instance
     */
	private final UserDAO userDAO = new UserDAOImpl();
	
	/**
     * CourseDAO instance
     */
	private final CourseDAO courseDAO = new CourseDAOImpl();
	/**
     * RunningCourseDAO instance
     */
	private final RunningCourseDAO runningCourseDAO = new RunningCourseDAOImpl();
	/**
     * ResultDAO instance
     */
	private final ResultDAO resultDAO = new ResultDAOImpl();
	/**
     * CourseParticipantDAO instance
     */
	private final CourseParticipantDAO courseParticipantDAO = new CourseParticipantDAOImpl();

	/////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Construct a DAOFactory
     */
	private DAOFactory() {}	
	
    /**
     * @return instance of DAOFactory
     */
	public static DAOFactory getInstance() {
		return instance;
	}

    /**
     * @return instance of EntranceDAO
     */
	public EntranceDAO  getEntranceDAO() {
		return entranceDAO ;
	}
	
    /**
     * @return instance of RegistrationDAO
     */
	public RegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}
	
	/**
     * @return instance of UserDAO
     */
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
    /**
     * @return instance of CourseDAO
     */
	public CourseDAO getCourseDAO() {
		return courseDAO;
	}
	
    /**
     * @return instance of RunningCourseDAO
     */	
	public RunningCourseDAO getRunningCourseDAO() {
		return runningCourseDAO;
	}
	
    /**
     * @return instance of ResultDAO
     */
	public ResultDAO getResultDAO() {
		return resultDAO;
	}
	
    /**
     * @return instance of CourseParticipantDAO
     */
	public CourseParticipantDAO getCourseParticipantDAO() {
		return courseParticipantDAO;
		
	}
}
