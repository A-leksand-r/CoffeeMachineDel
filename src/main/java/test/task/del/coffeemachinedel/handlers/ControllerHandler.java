package test.task.del.coffeemachinedel.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import test.task.del.coffeemachinedel.exceptions.BusinessException;
import test.task.del.coffeemachinedel.exceptions.ServiceException;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(BusinessException exception) {
        StringBuilder message = new StringBuilder();
        message.append(exception.getMessage()).append(System.lineSeparator());
        if (exception.getDetails() != null) message.append(exception.getDetails());
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> serviceExceptionHandler(ServiceException exception) {
        StringBuilder message = new StringBuilder();
        message.append(exception.getMessage()).append(System.lineSeparator());
        if (exception.getDetails() != null) message.append(exception.getDetails());
        return new ResponseEntity<>(message.toString(), HttpStatus.BAD_REQUEST);
    }
}
