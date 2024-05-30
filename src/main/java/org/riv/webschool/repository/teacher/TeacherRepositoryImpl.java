package org.riv.webschool.repository.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.repository.BaseRepository;
import org.riv.webschool.repository.exception.FatalPersistenceException;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherRepositoryImpl extends BaseRepository<Long, Teacher> implements TeacherRepository {

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO teacher (first_name, last_name, subject, passport) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM teacher WHERE teacher_id = ?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE teacher SET first_name = ?, last_name = ?, subject = ?, passport = ? WHERE teacher_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM teacher WHERE teacher_id = ?";
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, Teacher teacher) throws PersistenceException {
        try {
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getSubject().toString());
            preparedStatement.setString(4, teacher.getPassport());
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
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Teacher teacher) throws PersistenceException {
        try {
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getSubject().toString());
            preparedStatement.setString(4, teacher.getPassport());
            preparedStatement.setLong(5, teacher.getTeacherId());
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
            return resultSet.getLong("teacher_id");
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected Teacher getEntityFromResultSet(ResultSet resultSet) throws PersistenceException {
        try {
            return new Teacher(
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    Subject.valueOf(resultSet.getString("subject")),
                    resultSet.getLong("teacher_id"),
                    resultSet.getString("passport"));
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    public ArrayList<Teacher> getAll() throws PersistenceException {
        ArrayList<Teacher> result = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM teacher");
             ResultSet resultSet = statement.executeQuery()) {
            while ((resultSet.next())) {
                result.add(new Teacher(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Subject.valueOf(resultSet.getString("subject")),
                        resultSet.getLong("teacher_id"),
                        resultSet.getString("passport")));
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
    public ArrayList<Teacher> getAllBySubject(Subject subject) throws PersistenceException{
        ArrayList<Teacher> result = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM teacher WHERE subject = ?")) {
            statement.setString(1, String.valueOf(subject.getId()));
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                result.add(new Teacher(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Subject.valueOf(resultSet.getString("subject")),
                        resultSet.getLong("student_id"),
                        resultSet.getString("birth_certificate")));
            }
            dataSource.commit();
        } catch (SQLException e) {
            try {
                dataSource.rollbackConnection();
            } catch (SQLException ex) {
                throw new PersistenceException("Error", ex);
            }
            throw new PersistenceException("Error", e);
        }
        return result;
    }

}
