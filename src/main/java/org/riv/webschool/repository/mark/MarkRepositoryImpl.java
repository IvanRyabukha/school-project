package org.riv.webschool.repository.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;

import java.sql.*;
import java.util.ArrayList;

public class MarkRepositoryImpl implements MarkRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/sch_student";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final String INSERT_QUERY = "INSERT INTO mark(subject_id, mark, student_id, grade_id) " +
            "VALUES(?, ?, ?, (SELECT grade_id FROM student WHERE student_id = ?))";
    private static final String SELECT_BY_STUDENT_ID_QUERY = "SELECT * FROM mark WHERE student_id = ?";
    private static final String SELECT_BY_GRADE_ID_AND_BY_SUBJECT_ID_QUERY = "SELECT * FROM mark WHERE grade_id = ? AND subject_id = ?";
    private static final String UPDATE_QUERY = "UPDATE mark SET mark = ? WHERE student_id = ? AND subject_id = ?";
    private static final String REMOVE_QUERY = "DELETE FROM mark WHERE id = ?";

    public MarkRepositoryImpl() {
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
    public ArrayList<Mark> getMark(Long studentId) throws SQLException {
        ArrayList<Mark> result = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_STUDENT_ID_QUERY)) {
            statement.setLong(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new Mark(
                        resultSet.getLong("id"),
                        resultSet.getLong("student_id"),
                        resultSet.getInt("subject_id"),
                        resultSet.getInt("mark"),
                        resultSet.getInt("grade_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subjectId) throws SQLException {
        ArrayList<Mark> result = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BY_GRADE_ID_AND_BY_SUBJECT_ID_QUERY)) {

            statement.setInt(1, gradeId);
            statement.setInt(2, subjectId);
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                result.add(new Mark(
                        resultSet.getLong("id"),
                        resultSet.getLong("student_id"),
                        subjectId,
                        resultSet.getInt("mark"),
                        gradeId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Long addMark(int mark, Long studentId, Subject subject) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, subject.getId());
            statement.setInt(2, mark);
            statement.setLong(3, studentId);
            statement.setLong(4, studentId);
            if (statement.executeUpdate() != 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getLong("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    @Override
    public void updateMark(int newMark, Long studentId, Subject subject) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_QUERY)) {
            statement.setInt(1, newMark);
            statement.setLong(2, studentId);
            statement.setLong(3, subject.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMark(Long id) throws SQLException {
        try (PreparedStatement statement = getConnection().prepareStatement(REMOVE_QUERY)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Оценка не удалена!");
            }
        }
    }
}
