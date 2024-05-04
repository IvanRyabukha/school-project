package org.riv.webschool.service.student;

import org.riv.webschool.entity.Student;
import org.riv.webschool.repository.student.StudentRepository;
import org.riv.webschool.repository.student.StudentRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository = new StudentRepositoryImpl();

    @Override
    public Long addStudent(Student student) throws SQLException {
        return studentRepository.addStudent(student);
    }

    @Override
    public Student getStudent(Long id) throws SQLException {
        return studentRepository.getStudent(id);
    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        studentRepository.updateStudent(student);
    }

    @Override
    public void removeStudent(Long id) throws SQLException {
        studentRepository.removeStudent(id);
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        return studentRepository.getAll();
    }

    @Override
    public ArrayList<Student> getAllByGrade(Integer grade) {
        return studentRepository.getAllByGrade(grade);
    }
}
