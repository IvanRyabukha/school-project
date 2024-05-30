package org.riv.webschool.controller.mark;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.service.mark.MarkService;
import org.riv.webschool.service.mark.MarkServiceImpl;
import org.riv.webschool.validator.Validator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/mark")
public class MarkController {
//        extends HttpServlet {
//    private final MarkService markService = new MarkServiceImpl();
//    private final ObjectMapper mapper = new ObjectMapper();
//    private final Validator validator = new Validator();
//    private final HashMap<String, String> rules = new HashMap<>();
//
//    {
//        rules.put("id", "^\\d+$");
//        rules.put("studentId", "^\\d+$");
//        rules.put("subjectId", "^\\d+$");
//        rules.put("value", "^(1[0-2]|[1-9])$");
//        rules.put("gradeId", "^\\d+$");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors;
//        Map<String, String[]> result = req.getParameterMap();
//        try {
//            if (result.containsKey("studentId")) {
//                errors = validator.validateParams(rules, result, List.of("studentId"));
//                if (!errors.isEmpty()) {
//                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                    json = mapper.writeValueAsString(errors);
//                } else {
//                    json = mapper.writeValueAsString(markService.getMarkByStudentId(Long.parseLong(result.get("studentId")[0])));
//                }
//            } else {
//                errors = validator.validateParams(rules, result, List.of("gradeId", "subjectId"));
//                if (!errors.isEmpty()) {
//                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                    json = mapper.writeValueAsString(errors);
//                } else {
//                    Integer gradeId = Integer.parseInt(result.get("gradeId")[0]);
//                    Integer subjectId = Integer.parseInt(result.get("subjectId")[0]);
//                    json = mapper.writeValueAsString(markService.getAllMarksByGradeAndBySubjectId(gradeId, subjectId));
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        // Устанавливаем тип содержимого как JSON
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(),
//                List.of("studentId", "subjectId", "value"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            Mark mark = new Mark(
//                    Long.parseLong(req.getParameter("studentId")),
//                    Integer.parseInt(req.getParameter("subjectId")),
//                    Integer.parseInt(req.getParameter("value")));
//            try {
//                Map.Entry<String, Long> result = Map.entry("id", markService.addMark(mark.getValue(), mark.getStudentId(), Subject.getById(mark.getSubjectId())));
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
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(),
//                List.of("studentId", "subjectId", "value"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            Mark mark = new Mark(
//                    Long.parseLong(req.getParameter("studentId")),
//                    Integer.parseInt(req.getParameter("subjectId")),
//                    Integer.parseInt(req.getParameter("value")));
//            try {
//                markService.updateMark(mark.getValue(), mark.getStudentId(), Subject.getById(mark.getSubjectId()));
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
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String json = "";
//        //Валидатор проаускает ошибку, возвращается пустая мапа
//        Map<String, String> errors = validator.validateParams(rules, req.getParameterMap(), List.of("id"));
//        if (!errors.isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            json = mapper.writeValueAsString(errors);
//        } else {
//            try {
//                markService.removeMark(Long.parseLong(req.getParameter("id")));
//                json = mapper.writeValueAsString("Success");
//            } catch (SQLException e) {
//                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                json = mapper.writeValueAsString("Operation was unsuccessful");
//            }
//        }
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        // Отправляем JSON в ответ
//        resp.getWriter().write(json);
//    }
}
