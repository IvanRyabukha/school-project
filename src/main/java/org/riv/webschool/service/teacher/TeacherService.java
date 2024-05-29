package org.riv.webschool.service.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherService {

    ArrayList<Teacher> getAll() throws ServiceException;

    ArrayList<Teacher> getAllBySubject(Subject subject) throws ServiceException;
}
