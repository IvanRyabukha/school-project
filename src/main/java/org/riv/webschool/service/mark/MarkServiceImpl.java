package org.riv.webschool.service.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.repository.mark.MarkRepository;
import org.riv.webschool.repository.mark.MarkRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class MarkServiceImpl implements MarkService {
    MarkRepository markRepository = new MarkRepositoryImpl();

    @Override
    public ArrayList<Mark> getMark(Long studentId) throws SQLException {
        return markRepository.getMark(studentId);
    }

    @Override
    public ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subjectId) throws SQLException {
        return markRepository.getAllMarksByGradeAndBySubjectId(gradeId, subjectId);
    }

    @Override
    public Long addMark(int mark, Long studentId, Subject subject) throws SQLException {
        return markRepository.addMark(mark, studentId, subject);
    }

    @Override
    public void updateMark(int newMark, Long studentId, Subject subject) throws SQLException {
        markRepository.updateMark(newMark, studentId, subject);
    }

    @Override
    public void removeMark(Long id) throws SQLException {
        markRepository.removeMark(id);
    }
}
