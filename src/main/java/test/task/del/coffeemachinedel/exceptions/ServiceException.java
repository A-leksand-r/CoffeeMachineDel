package test.task.del.coffeemachinedel.exceptions;

import lombok.Getter;
import test.task.del.coffeemachinedel.exceptions.enums.Error;

@Getter
public class ServiceException extends RuntimeException {

    private final Error errorCode;
    private final String details;

    public ServiceException(String message, Error errorCode, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    public ServiceException(String message, Error errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.details = null;
    }
}
