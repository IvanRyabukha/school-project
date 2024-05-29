package org.riv.webschool.repository.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherRepository {
    ArrayList<Teacher> getAll() throws PersistenceException;

    ArrayList<Teacher> getAllBySubject(Subject subject) throws PersistenceException;
}
