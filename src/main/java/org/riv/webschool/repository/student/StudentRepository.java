package org.riv.webschool.repository.student;

import org.riv.webschool.entity.Student;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRepository {

    ArrayList<Student> getAll() throws PersistenceException;

    ArrayList<Student> getAllByGrade(Integer grade) throws PersistenceException;

}









