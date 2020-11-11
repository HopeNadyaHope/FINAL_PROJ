package by.epam.lobanok.dao.exception;
/** 
 * Class for exceptions in the DAO layer.
 * @author hope_nadya_hope
 * 
 * */
public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

    /**
     * Construct a DAOException
     */
	public DAOException() {
		super();
	}
	
    /**
     * Construct a DAOException with the specified detail message.
     * @param message the detail message
     */
	public DAOException(String message) {
		super(message);
	}

    /**
     * Construct a DAOException
     * @param e Exception that we may have caught to reissue as an DAOException. The message will be used, and we may want to consider overriding the printStackTrace() methods to get data pointing back to original throw stack.
     */
	public DAOException(Exception e) {
		super(e);
	}

    /**
     * Construct a DAOException with the specified detail message and cause.
     * @param message the detail message
     * @param e Exception that we may have caught to reissue as an DAOException. The message will be used, and we may want to consider overriding the printStackTrace() methods to get data pointing back to original throw stack.
     * 
     */
	public DAOException(String message, Exception e) {
		super(message, e);
	}
}
