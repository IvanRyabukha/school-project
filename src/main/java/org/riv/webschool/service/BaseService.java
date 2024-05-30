package org.riv.webschool.service;

import org.riv.webschool.repository.BaseRepository;
import org.riv.webschool.repository.exception.PersistenceException;
import org.riv.webschool.service.exception.ServiceException;

public abstract class BaseService<ID, T, R extends BaseRepository<ID, T>> {
    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    public ID create(T entity) throws ServiceException {
        try {
            return repository.create(entity);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public T read(ID id) throws ServiceException {
        try {
            return repository.read(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void update(T entity) throws ServiceException {
        try {
            repository.update(entity);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void remove(ID id) throws ServiceException {
        try {
            repository.remove(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
