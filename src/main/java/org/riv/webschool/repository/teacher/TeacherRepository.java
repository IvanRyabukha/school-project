package org.riv.webschool.repository.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherRepository {
    Long addTeacher(Teacher teacher) throws SQLException; //Create

    Teacher getTeacher(Long id) throws SQLException; //Read

    void updateTeacher(Teacher teacher) throws SQLException; //Update

    void removeTeacher(Long id) throws SQLException; //Delete

    ArrayList<Teacher> getAll() throws SQLException;

    ArrayList<Teacher> getAllBySubject(Subject subject);
}
