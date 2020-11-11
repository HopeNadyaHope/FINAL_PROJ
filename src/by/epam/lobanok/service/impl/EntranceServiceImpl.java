package by.epam.lobanok.service.impl;


import by.epam.lobanok.dao.DAOFactory;
import by.epam.lobanok.dao.EntranceDAO;
import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.dao.exception.NoSuchUserDAOException;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.EntranceService;
import by.epam.lobanok.service.exception.NoSuchUserServiceException;
import by.epam.lobanok.service.exception.ServiceException;

public class EntranceServiceImpl implements EntranceService{

	@Override
	public User entrance(EntranceData entrData) throws ServiceException {
		EntranceDAO entranceDAO = DAOFactory.getInstance().getEntranceDAO(); 
		User user = null; 
		try {
			user = entranceDAO.entrance(entrData); 
			} catch(NoSuchUserDAOException e) {
				throw new NoSuchUserServiceException();
			}catch (DAOException e) { 
				throw new ServiceException(e);
			} 
		return user;
	}
}
