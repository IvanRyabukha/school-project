package org.riv.webschool.service.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.repository.BaseRepository;
import org.riv.webschool.repository.exception.PersistenceException;
import org.riv.webschool.repository.teacher.TeacherRepository;
import org.riv.webschool.repository.teacher.TeacherRepositoryImpl;
import org.riv.webschool.service.BaseService;
import org.riv.webschool.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherServiceImpl extends BaseService<Long, Teacher, TeacherRepositoryImpl> implements TeacherService {

    public TeacherServiceImpl() {
        super(new TeacherRepositoryImpl());
    }

    @Override
    public ArrayList<Teacher> getAll() throws ServiceException {
        try {
            return repository.getAll();
        } catch (PersistenceException e) {
            throw new ServiceException("Error", e);
        }
    }

    @Override
    public ArrayList<Teacher> getAllBySubject(Subject subject) throws ServiceException {
        try {
            return repository.getAllBySubject(subject);
        } catch (PersistenceException e) {
            throw new ServiceException("Error", e);
        }
    }
}
