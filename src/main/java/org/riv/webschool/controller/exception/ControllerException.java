package org.riv.webschool.controller.exception;

import org.riv.webschool.service.exception.ServiceException;

public class ControllerException extends ServiceException {
    public ControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
