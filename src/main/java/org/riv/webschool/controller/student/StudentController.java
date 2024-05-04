package org.riv.webschool.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riv.webschool.entity.Student;
import org.riv.webschool.service.student.StudentService;
import org.riv.webschool.service.student.StudentServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/student")
public class StudentController extends HttpServlet {
    private final StudentService studentService = new StudentServiceImpl();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        String id = req.getParameter("studentId");
        try {
            json = id != null ? mapper.writeValueAsString(studentService.getStudent(Long.parseLong(id)))
                    : mapper.writeValueAsString(studentService.getAll());
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        Student student = new Student(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                Integer.parseInt(req.getParameter("grade")),
                req.getParameter("birthСertificate"));
        try {
            Map.Entry<String, Long> result = Map.entry("studentId", studentService.addStudent(student));
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        Student student = new Student(
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                Integer.parseInt(req.getParameter("grade")),
                Long.parseLong(req.getParameter("studentId")),
                req.getParameter("birthСertificate"));
        try {
            studentService.updateStudent(student);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        try {
            studentService.removeStudent(Long.parseLong(req.getParameter("studentId")));
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
