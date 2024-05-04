package org.riv.webschool.controller.mark;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riv.webschool.entity.Mark;
import org.riv.webschool.entity.Subject;
import org.riv.webschool.service.mark.MarkService;
import org.riv.webschool.service.mark.MarkServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/mark")
public class MarkController extends HttpServlet {
    private final MarkService markService = new MarkServiceImpl();
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = "";
        String id = req.getParameter("studentId");
        Map<String, String[]> result = req.getParameterMap();
        try {
            if (id != null) {
                json = mapper.writeValueAsString(markService.getMark(Long.parseLong(id)));
            } else if (!result.isEmpty()) {
                Integer gradeId = Integer.parseInt(result.get("gradeId")[0]);
                Integer subjectId = Integer.parseInt(result.get("subjectId")[0]);
                json = mapper.writeValueAsString(markService.getAllMarksByGradeAndBySubjectId(gradeId, subjectId));
            }
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
        Mark mark = new Mark(
                Long.parseLong(req.getParameter("studentId")),
                Integer.parseInt(req.getParameter("subjectId")),
                Integer.parseInt(req.getParameter("mark")));
        try {
            Map.Entry<String, Long> result = Map.entry("id", markService.addMark(mark.getValue(), mark.getStudentId(), Subject.getById(mark.getSubjectId())));
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
        Mark mark = new Mark(
                Long.parseLong(req.getParameter("studentId")),
                Integer.parseInt(req.getParameter("subjectId")),
                Integer.parseInt(req.getParameter("newMark")));
        try {
            markService.updateMark(mark.getValue(), mark.getStudentId(), Subject.getById(mark.getSubjectId()));
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
            markService.removeMark(Long.parseLong(req.getParameter("markId")));
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
