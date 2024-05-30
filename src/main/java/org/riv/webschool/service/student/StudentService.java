package org.riv.webschool.service.student;

import org.riv.webschool.entity.Student;
import org.riv.webschool.service.exception.ServiceException;

import java.util.ArrayList;

public interface StudentService {
    ArrayList<Student> getAll() throws ServiceException;

    ArrayList<Student> getAllByGrade(Integer grade) throws ServiceException;
}
