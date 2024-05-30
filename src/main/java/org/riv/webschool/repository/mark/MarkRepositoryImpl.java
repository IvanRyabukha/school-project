package org.riv.webschool.repository.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.repository.BaseRepository;
import org.riv.webschool.repository.exception.FatalPersistenceException;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarkRepositoryImpl extends BaseRepository<Long, Mark> implements MarkRepository {

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO mark(subject_id, mark, student_id, grade_id)" +
                "VALUES(?, ?, ?, (SELECT grade_id FROM student WHERE student_id = ?))";
    }

    @Override
    protected String getReadQuery() {
        return "SELECT * FROM mark WHERE id = ?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE mark SET mark = ? WHERE student_id = ? AND subject_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM mark WHERE id = ?";
    }

    @Override
    protected void prepareCreateStatement(PreparedStatement preparedStatement, Mark mark) throws PersistenceException {
        try {
            preparedStatement.setInt(1, mark.getSubjectId());
            preparedStatement.setInt(2, mark.getValue());
            preparedStatement.setLong(3, mark.getStudentId());
            preparedStatement.setInt(4, mark.getGradeId());
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
    protected void prepareUpdateStatement(PreparedStatement preparedStatement, Mark mark) throws PersistenceException {
        try {
            preparedStatement.setLong(1, mark.getStudentId());
            preparedStatement.setLong(2, mark.getSubjectId());
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
            return resultSet.getLong("id");
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    protected Mark getEntityFromResultSet(ResultSet resultSet) throws PersistenceException {
        try {
            return new Mark(
                    resultSet.getLong("id"),
                    resultSet.getLong("student_id"),
                    resultSet.getInt("subject_id"),
                    resultSet.getInt("mark"),
                    resultSet.getInt("grade_id"));
        } catch (SQLException e) {
            throw new PersistenceException("Error", e);
        }
    }

    @Override
    public ArrayList<Mark> getMarkByStudentId(Long studentId) throws PersistenceException {
        ArrayList<Mark> result = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM mark WHERE student_id = ?")) {
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
    public ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subjectId) throws PersistenceException {
        ArrayList<Mark> result = new ArrayList<>();
        try (PreparedStatement statement = dataSource.getConnection().prepareStatement("SELECT * FROM mark WHERE grade_id = ? AND subject_id = ?")) {
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
