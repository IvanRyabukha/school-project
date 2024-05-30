package org.riv.webschool.repository.student;

import org.riv.webschool.entity.Student;
import org.riv.webschool.repository.BaseRepository;
import org.riv.webschool.repository.exception.FatalPersistenceException;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepositoryImpl extends BaseRepository<Long, Student> implements StudentRepository {
    @Override
    protected String getCreateQuery() {
        return "INSERT INTO student (first_name, last_name, grade_id, birth_certificate) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM student WHERE student_id = ?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE student SET first_name = ?, last_name = ?, grade_id = ?, birth_certificate = ? WHERE student_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM student WHERE student_id = ?";
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, Student student) throws PersistenceException {
        try {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getGrade());
            preparedStatement.setString(4, student.getBirthCertificate());
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected void prepareReadStatement(PreparedStatement preparedStatement, Long id) throws PersistenceException {
        try {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Student student) throws PersistenceException {
        try {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getGrade());
            preparedStatement.setString(4, student.getBirthCertificate());
            preparedStatement.setLong(5, student.getStudentId());
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement preparedStatement, Long id) throws PersistenceException {
        try {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected Long getIdFromResultSet(ResultSet resultSet) throws PersistenceException {
        try {
            return resultSet.getLong("student_id");
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected Student getEntityFromResultSet(ResultSet resultSet) throws PersistenceException {
        try {
            return new Student(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("grade_id"),
                    resultSet.getLong("student_id"),
                    resultSet.getString("birth_certificate"));
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    public ArrayList<Student> getAll() throws PersistenceException {
        ArrayList<Student> result = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM student");
             ResultSet resultSet = statement.executeQuery()) {
            while ((resultSet.next())) {
                result.add(new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("grade_id"),
                        resultSet.getLong("student_id"),
                        resultSet.getString("birth_certificate")));
            }
            dataSource.commit();
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new FatalPersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }
        return result;
    }

    @Override
    public ArrayList<Student> getAllByGrade(Integer gradeId) throws PersistenceException {
        ArrayList<Student> result = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM student WHERE grade_id = ?")) {
            statement.setInt(1, gradeId);
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                result.add(new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        gradeId,
                        resultSet.getLong("student_id"),
                        resultSet.getString("birth_certificate")));
            }
            dataSource.commit();
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new FatalPersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }
        return result;
    }
}
