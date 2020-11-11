package by.epam.lobanok.service.impl;

import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.RegistrationDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.RegistrationData;
import by.epam.lobanok.service.RegistrationService;
import by.epam.lobanok.service.exception.DublicateUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;

public class RegistrationServiceImpl implements RegistrationService {

	@Override
	public void registration(RegistrationData regData) throws ServiceException {
		RegistrationDAO registrationDAO = DAOFactory.getInstance().getRegistrationDAO(); 
		
		boolean dublicateLogin;
		try {
			dublicateLogin = registrationDAO.checkLogin(regData.getLogin());
		}catch(DAOException e) {
			throw new ServiceException(e);
		}		
		if(dublicateLogin) {
			throw new DublicateUserServiceException();
		}
		
		try { 
			registrationDAO.registration(regData); 
		} catch (DAOException e) {
			throw new ServiceException(e); 
		}
	}
}
