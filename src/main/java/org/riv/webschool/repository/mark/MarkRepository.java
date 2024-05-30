package org.riv.webschool.repository.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MarkRepository {
    ArrayList<Mark> getMarkByStudentId(Long studentId) throws PersistenceException;

    ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subjectId) throws PersistenceException;

}
