package org.riv.webschool.repository.teacher;

import org.junit.jupiter.api.Test;
import org.riv.webschool.configuration.DataSource;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.repository.BaseRepositoryTest;
import org.riv.webschool.repository.exception.PersistenceException;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherRepositoryImplTest extends BaseRepositoryTest {

    private final TeacherRepositoryImpl repository = new TeacherRepositoryImpl();
    private final DataSource dataSource = new DataSource();
    private Connection connection;

    @Test
    void test_readTeacher() throws PersistenceException {
        String expectedLastName = "test last name";
        Teacher teacher = new Teacher("test", expectedLastName, Subject.BIOLOGY, "123321");
        Long id = repository.create(teacher);
        Teacher teacher1 = repository.read(id);
        teacher.setTeacherId(id);
        clearDB(teacher);
        assertEquals(expectedLastName, teacher1.getLastName());
    }

    void clearDB(Teacher... teachers) throws PersistenceException {
        for (Teacher t : teachers ) {
            repository.remove(t.getTeacherId());
        }
    }

}
