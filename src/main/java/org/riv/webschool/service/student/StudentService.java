package org.riv.webschool.service.student;

import org.riv.webschool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentService {
    Long addStudent(Student student) throws SQLException; //Create

    Student getStudent(Long id) throws SQLException; //Read

    void updateStudent(Student student) throws SQLException; //Update

    void removeStudent(Long id) throws SQLException; //Delete

    ArrayList<Student> getAll() throws SQLException;

    ArrayList<Student> getAllByGrade(Integer grade);
}
