package org.riv.webschool.repository.teacher;

import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;

public class TeacherRepositoryImpl implements TeacherRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/sch_student";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String INSERT_QUERY = "INSERT INTO teacher (first_name, last_name, subject, passport) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM teacher";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM teacher WHERE teacher_id = ?";
    private static final String SELECT_ALL_BY_SUBJECT_QUERY = "SELECT * FROM teacher WHERE subject = ?";
    private static final String UPDATE_QUERY = "UPDATE teacher SET first_name = ?, last_name = ?, subject = ?, passport = ? WHERE teacher_id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM teacher WHERE teacher_id = ?";

    public TeacherRepositoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    //TODO REF
    @Override
    public Long addTeacher(Teacher teacher) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getSubject().toString());
            statement.setString(4, teacher.getPassport());

            if (statement.executeUpdate() != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong("teacher_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1L;
    }

    //TODO REF
    @Override
    public Teacher getTeacher(Long id) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Teacher(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Subject.valueOf(resultSet.getString("subject")),
                        resultSet.getLong("teacher_id"),
                        resultSet.getString("passport"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO REF
    @Override
    public void updateTeacher(Teacher teacher) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getSubject().toString());
            statement.setString(4, teacher.getPassport());
            statement.setLong(5, teacher.getTeacherId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTeacher(Long id) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(REMOVE_QUERY)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Учитель не был удалён!");
            }
        }
    }

    //TODO REF
    @Override
    public ArrayList<Teacher> getAll() throws SQLException {
        ArrayList<Teacher> result = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while ((resultSet.next())) {
                result.add(new Teacher(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Subject.valueOf(resultSet.getString("subject")),
                        resultSet.getLong("teacher_id"),
                        resultSet.getString("passport")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Teacher> getAllBySubject(Subject subject) {
        ArrayList<Teacher> result = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_BY_SUBJECT_QUERY)) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
