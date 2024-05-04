package org.riv.webschool.service.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.repository.teacher.TeacherRepository;
import org.riv.webschool.repository.teacher.TeacherRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository = new TeacherRepositoryImpl();

    @Override
    public Long addTeacher(Teacher teacher) throws SQLException {
        return teacherRepository.addTeacher(teacher);
    }

    @Override
    public Teacher getTeacher(Long id) throws SQLException {
        return teacherRepository.getTeacher(id);
    }

    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        teacherRepository.updateTeacher(teacher);
    }

    @Override
    public void removeTeacher(Long id) throws SQLException {
        teacherRepository.removeTeacher(id);
    }

    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        return teacherRepository.getAll();
    }

    @Override
    public ArrayList<Teacher> getAllBySubject(Subject subject) {
        return teacherRepository.getAllBySubject(subject);
    }
}
