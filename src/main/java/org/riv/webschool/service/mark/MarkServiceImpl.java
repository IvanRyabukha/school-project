package org.riv.webschool.service.mark;

import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.repository.exception.PersistenceException;
import org.riv.webschool.repository.mark.MarkRepository;
import org.riv.webschool.repository.mark.MarkRepositoryImpl;
import org.riv.webschool.service.BaseService;
import org.riv.webschool.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.ArrayList;

public class MarkServiceImpl extends BaseService<Long, Mark, MarkRepositoryImpl> implements MarkService {

   public MarkServiceImpl() {
        super(new MarkRepositoryImpl());
    }
    @Override
    public ArrayList<Mark> getMarkByStudentId(Long studentId) throws ServiceException {
        try {
            return repository.getMarkByStudentId(studentId);
        } catch (PersistenceException e) {
            throw new ServiceException("Error", e);
        }
    }

    @Override
    public ArrayList<Mark> getAllMarksByGradeAndBySubjectId(Integer gradeId, Integer subject_id) throws ServiceException {
        try {
            return repository.getAllMarksByGradeAndBySubjectId(gradeId, subject_id);
        } catch (PersistenceException e) {
            throw new ServiceException("Error", e);
        }
    }
}
