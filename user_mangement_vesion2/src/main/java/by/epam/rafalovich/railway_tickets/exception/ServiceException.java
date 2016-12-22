package by.epam.rafalovich.railway_tickets.exception;

/**
 *  The {@code ServiceException} class is an exception that occurs
 *  in the Service layer.
 *  @author Dzmitry_Rafalovich
 */
public class ServiceException extends Exception {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
