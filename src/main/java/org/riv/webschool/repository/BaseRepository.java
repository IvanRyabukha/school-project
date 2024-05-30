package org.riv.webschool.repository;

import org.riv.webschool.configuration.DataSource;
import org.riv.webschool.repository.exception.FatalPersistenceException;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.*;

public abstract class BaseRepository<ID, T> {

    protected final DataSource dataSource = new DataSource();

    protected abstract String getCreateQuery();

    protected abstract String getReadQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract void prepareCreateStatement(PreparedStatement preparedStatement, T entity) throws PersistenceException;

    protected abstract void prepareReadStatement(PreparedStatement preparedStatement, ID id) throws PersistenceException;

    protected abstract void prepareUpdateStatement(PreparedStatement preparedStatement, T entity) throws PersistenceException;

    protected abstract void prepareDeleteStatement(PreparedStatement preparedStatement, ID id) throws PersistenceException;

    protected abstract ID getIdFromResultSet(ResultSet resultSet) throws PersistenceException;

    protected abstract T getEntityFromResultSet(ResultSet resultSet) throws PersistenceException;

    public ID create(T entity) throws PersistenceException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareCreateStatement(preparedStatement, entity);
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    ID id = getIdFromResultSet(resultSet);
                    connection.commit();
                    return id;
                }
            }
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new FatalPersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }
        return null;
    }

    public T read(ID id) throws PersistenceException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getReadQuery())) {
            prepareReadStatement(preparedStatement, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T entity = getEntityFromResultSet(resultSet);
                connection.commit();
                return entity;
            }
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new FatalPersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }
        return null;
    }

    public void update(T entity) throws PersistenceException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getUpdateQuery())) {
            prepareUpdateStatement(preparedStatement, entity);
            int i = preparedStatement.executeUpdate();
            connection.commit();
            if (i == 0) {
                throw new PersistenceException("Update error");
            }
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new FatalPersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }
    }

    public void remove(ID id) throws PersistenceException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getDeleteQuery())) {
            prepareDeleteStatement(preparedStatement, id);
            int i = preparedStatement.executeUpdate();
            connection.commit();
            if (i == 0) {
                throw new PersistenceException("Remove error");
            }
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new FatalPersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }

    }

}
