package org.riv.webschool.controller.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.entity.Teacher;
import org.riv.webschool.service.teacher.TeacherService;
import org.riv.webschool.service.teacher.TeacherServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/teacher")
public class TeacherController extends HttpServlet {
    private final TeacherService teacherService = new TeacherServiceImpl();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = "";
        String id = req.getParameter("teacherId");
        try {
            json = id != null ? mapper.writeValueAsString(teacherService.getTeacher(Long.parseLong(id)))
                    : mapper.writeValueAsString(teacherService.getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Устанавливаем тип содержимого как JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Отправляем JSON в ответ
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = "";
        Teacher teacher = new Teacher(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                Subject.valueOf(req.getParameter("subject").toUpperCase()),
                req.getParameter("passport"));
        try {
            Map.Entry<String, Long> result = Map.entry("teacherId", teacherService.addTeacher(teacher));
            json = mapper.writeValueAsString(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Отправляем JSON в ответ
        resp.getWriter().write(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = "";
        Teacher teacher = new Teacher(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                Subject.valueOf(req.getParameter("subject").toUpperCase()),
                Long.parseLong(req.getParameter("teacherId")),
                req.getParameter("passport"));
        try {
            teacherService.updateTeacher(teacher);
            json = mapper.writeValueAsString("Success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Отправляем JSON в ответ
        resp.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = "";
        try {
            teacherService.removeTeacher(Long.parseLong(req.getParameter("teacherId")));
            json = mapper.writeValueAsString("Success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Отправляем JSON в ответ
        resp.getWriter().write(json);
    }
}
