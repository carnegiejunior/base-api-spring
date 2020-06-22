package api.exceptions;

public class SQLIntegrityConstraintViolationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SQLIntegrityConstraintViolationException(String msg) {
		super(msg);
	}
}
