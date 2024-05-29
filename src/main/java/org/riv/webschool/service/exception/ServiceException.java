package org.riv.webschool.service.exception;

import org.riv.webschool.repository.exception.PersistenceException;

public class ServiceException extends PersistenceException {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
