package by.epam.lobanok.dao.exception;
/** 
 * Thrown when user not found in data base
 * @author hope_nadya_hope
 * 
 * */
public class NoSuchUserDAOException extends DAOException{
	private static final long serialVersionUID = 1L;
	/**
     * Construct a NoSuchUserDAOException
     */
	public NoSuchUserDAOException() {
		super();
	}
}
