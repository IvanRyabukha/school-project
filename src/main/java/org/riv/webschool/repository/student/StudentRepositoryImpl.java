package org.riv.webschool.repository.student;

import org.riv.webschool.entity.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentRepositoryImpl implements StudentRepository {
    //вынести в конфиг
    private static final String URL = "jdbc:postgresql://localhost:5432/sch_student";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String INSERT_QUERY = "INSERT INTO student (first_name, last_name, grade_id, birth_certificate) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM student";
    private static final String SELECT_ALL_BY_GRADE_QUERY = "SELECT * FROM student WHERE grade_id = ?";
    private static final String UPDATE_QUERY = "UPDATE student SET first_name = ?, last_name = ?, grade_id = ?, birth_certificate = ? WHERE student_id = ?";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM student WHERE student_id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM student WHERE student_id = ?";

    public StudentRepositoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public Long addStudent(Student student) {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGrade());
            statement.setString(4, student.getBirthCertificate());

            if (statement.executeUpdate() != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong("student_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1L;
    }

    @Override
    public Student getStudent(Long id) {
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("grade_id"),
                        resultSet.getLong("student_id"),
                        resultSet.getString("birth_certificate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateStudent(Student student) {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGrade());
            statement.setString(4, student.getBirthCertificate());
            statement.setLong(5, student.getStudentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeStudent(Long id) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(REMOVE_QUERY)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Студент не был удалён!");
            }
        }
    }

    @Override
    public ArrayList<Student> getAll() throws SQLException {
        ArrayList<Student> result = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while ((resultSet.next())) {
                result.add(new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("grade_id"),
                        resultSet.getLong("student_id"),
                        resultSet.getString("birth_certificate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Student> getAllByGrade(Integer gradeId) {
        ArrayList<Student> result = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_BY_GRADE_QUERY)) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
