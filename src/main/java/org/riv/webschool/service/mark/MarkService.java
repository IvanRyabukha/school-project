package org.riv.webschool.service.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.repository.exception.PersistenceException;
import org.riv.webschool.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MarkService {
    ArrayList<Mark> getMarkByStudentId(Long studentId) throws ServiceException;

    ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subject_id) throws ServiceException;
}
