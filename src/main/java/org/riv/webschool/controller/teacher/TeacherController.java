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
import org.riv.webschool.validator.Validator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/teacher")
public class TeacherController {
//        extends HttpServlet {
//    private final TeacherService teacherService = new TeacherServiceImpl();
//    private final ObjectMapper mapper = new ObjectMapper();
//    private final Validator validator = new Validator();
//    private final HashMap<String, String> rules = new HashMap<>();
//
//    {
//        rules.put("firstName", "^[A-z]{2,}$");
//        rules.put("lastName", "^[A-z]{2,}$");
//        rules.put("subject", "^(?i)(math|history|literature|biology|chemistry)$");
//        rules.put("teacherId", "^\\d+$");
//        rules.put("passport", "^[A-Z]{2}\\d{6}$");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(), List.of("teacherId"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            String id = req.getParameter("teacherId");
//            try {
//                json = id != null ? mapper.writeValueAsString(teacherService.getTeacher(Long.parseLong(id)))
//                        : mapper.writeValueAsString(teacherService.getAll());
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        // Устанавливаем тип содержимого как JSON
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(),
//                List.of("firstName", "lastName", "subject", "passport"));
//        if (!errors.isEmpty()) {
//         resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//         json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                Map.Entry<String, Long> result = Map.entry("teacherId", teacherService.addTeacher(new Teacher(
//                        req.getParameter("firstName"),
//                        req.getParameter("lastName"),
//                        Subject.valueOf(req.getParameter("subject").toUpperCase()),
//                        req.getParameter("passport"))));
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
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(), List.of("firstName", "lastName", "subject", "teacherId", "passport"));
//        if (!errors.isEmpty()) {
//         resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//         json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                teacherService.updateTeacher(new Teacher(
//                        req.getParameter("firstName"),
//                        req.getParameter("lastName"),
//                        Subject.valueOf(req.getParameter("subject").toUpperCase()),
//                        Long.parseLong(req.getParameter("teacherId")),
//                        req.getParameter("passport")));
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
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(), List.of("teacherId"));
//        if (!errors.isEmpty()) {
//         resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//         json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                teacherService.removeTeacher(Long.parseLong(req.getParameter("teacherId")));
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
