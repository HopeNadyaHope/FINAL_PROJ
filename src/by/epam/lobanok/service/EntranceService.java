package by.epam.lobanok.service;

import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
import by.epam.lobanok.service.exception.ServiceException;

public interface EntranceService {
	User entrance(EntranceData entrData) throws ServiceException;
}
