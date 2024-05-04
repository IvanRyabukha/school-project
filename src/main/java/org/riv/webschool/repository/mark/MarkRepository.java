package org.riv.webschool.repository.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MarkRepository {
    ArrayList<Mark> getMark(Long studentId) throws SQLException;

    ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subject_id) throws SQLException;

    Long addMark(int mark, Long studentId, Subject subject) throws SQLException;

    void updateMark(int newMark, Long studentId, Subject subject) throws SQLException;

    void removeMark(Long id) throws SQLException;
}
