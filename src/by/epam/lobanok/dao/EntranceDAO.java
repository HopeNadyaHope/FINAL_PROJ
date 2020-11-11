package by.epam.lobanok.dao;

import by.epam.lobanok.dao.exception.DAOException;
import by.epam.lobanok.entity.EntranceData;
import by.epam.lobanok.entity.User;
/**
 * Interface of EntranceDAO 
 *
 * @author hope_nadya_hope
 */
public interface EntranceDAO {
	/**
     * Entrance user 
     *
     * @param EntranceData which contains login and password
     * @return an authorized user
     * @throws DAOException if an DAO error occurs
     */
	User entrance(EntranceData entrData) throws DAOException;
}
