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
import org.riv.webschool.validator.Validator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/student")
public class StudentController {
//        extends HttpServlet {
//    private final StudentServiceImpl studentService = new StudentServiceImpl();
//    private final ObjectMapper mapper = new ObjectMapper();
//    private final Validator validator = new Validator();
//    private final HashMap<String, String> rules = new HashMap<>();
//
//    {
//        rules.put("firstName", "^[A-z]{2,}$");
//        rules.put("lastName", "^[A-z]{2,}$");
//        rules.put("gradeId", "^\\d+$");
//        rules.put("studentId", "^\\d+$");
//        rules.put("birthCertificate", "^[A-Z]{2}\\d{6}$");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(), List.of("studentId"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            String id = req.getParameter("studentId");
//            try {
//                json = id != null ? mapper.writeValueAsString(studentService.getStudent(Long.parseLong(id)))
//                        : mapper.writeValueAsString(studentService.getAll());
//            } catch (SQLException | NumberFormatException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        // Устанавливаем тип содержимого как JSON
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(),
//                List.of("firstName", "lastName", "grade", "birthCertificate"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                Map.Entry<String, Long> result = Map.entry("studentId", studentService.addStudent(new Student(
//                        req.getParameter("firstName"),
//                        req.getParameter("lastName"),
//                        Integer.parseInt(req.getParameter("grade")),
//                        req.getParameter("birthCertificate"))));
//                json = mapper.writeValueAsString(result);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(),
//                List.of("firstName", "lastName", "grade", "studentId", "birthCertificate"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                studentService.update(new Student(
//                        req.getParameter("firstName"),
//                        req.getParameter("lastName"),
//                        Integer.parseInt(req.getParameter("grade")),
//                        Long.parseLong(req.getParameter("studentId")),
//                        req.getParameter("birthCertificate")));
//                json = mapper.writeValueAsString("Success");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(), List.of("teacherId"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                studentService.remove(Long.parseLong(req.getParameter("studentId")));
//                json = mapper.writeValueAsString("Success");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//    }
}
