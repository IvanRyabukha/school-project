package org.riv.webschool.service.student;

import org.riv.webschool.entity.Student;
import org.riv.webschool.repository.exception.PersistenceException;
import org.riv.webschool.repository.student.StudentRepository;
import org.riv.webschool.repository.student.StudentRepositoryImpl;
import org.riv.webschool.service.BaseService;
import org.riv.webschool.service.exception.ServiceException;

import java.util.ArrayList;

public class StudentServiceImpl extends BaseService<Long, Student, StudentRepositoryImpl> implements StudentService {
    public StudentServiceImpl() {
        super(new StudentRepositoryImpl());
    }

    @Override
    public ArrayList<Student> getAll() throws ServiceException {
        try {
            return repository.getAll();
        } catch (PersistenceException e) {
            throw new ServiceException("Error", e);
        }
    }

    @Override
    public ArrayList<Student> getAllByGrade(Integer grade) throws ServiceException {
        try {
            return repository.getAllByGrade(grade);
        } catch (PersistenceException e) {
            throw new ServiceException("Error", e);
        }
    }
}
