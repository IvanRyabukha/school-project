package org.riv.webschool.repository.exception;

import java.sql.SQLException;

public class FatalPersistenceException extends PersistenceException {
    public FatalPersistenceException(String error, SQLException e) {
    }
}
