package by.epam.lobanok.dao.pool;

public class ConnectionPoolException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String message, Exception e){
		super(message, e);
	}
}