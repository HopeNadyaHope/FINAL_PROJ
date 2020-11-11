package by.epam.lobanok.dao;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.RegistrationData;
/**
 * Interface of RegistartionDAO
 *
 * @author hope_nadya_hope
 */
public interface RegistrationDAO {
	/**
     * Registers user using RegistrationData
     *
     * @param RegistrationData of a user
     * @throws DAOException if an DAO error occurs
     */
	void registration(RegistrationData regData) throws DAOException;
	
	/**
     * Checks if there is a login
     *
     * @param login
     * @return true if such login is in a system
     * @throws DAOException if an DAO error occurs
     */
	boolean checkLogin(String login) throws DAOException;
}
