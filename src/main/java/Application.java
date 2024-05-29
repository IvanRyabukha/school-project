import org.riv.webschool.entity.Student;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
//        String email = "email@.com";
//        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
//            System.out.println(false);
//        }
//        HashMap<String, String> teacherRules = new HashMap<>();
//        teacherRules.put("firstName", "^[A-z]{2,}$");
//        teacherRules.put("lastName", "^[A-z]{2,}$");
//        teacherRules.put("subject", "^(?i)(math|history|literature|biology|chemistry)$");
//        teacherRules.put("teacherId", "^\\d+$");
//        teacherRules.put("passport", "^[A-Z]{2}\\d{6}$");
//
//        HashMap<String, String[]> teacherValues = new HashMap<>();
//        teacherValues.put("firstName", new String[]{"Aboba1"});
//        teacherValues.put("lastName", new String[]{"Aboboas"});
//        teacherValues.put("subject", new String[]{"MATH"});
//        teacherValues.put("teacherId", new String[]{"322"});
//        teacherValues.put("passport", new String[]{"AA1488888"});
//
//        List<String> params = List.of("firstName", "lastName", "subject", "teacherId", "passport");
//        System.out.println(validateParams(teacherRules, teacherValues, params));

        Student student = new Student();

        Class<?> studentClass = Class.forName("org.riv.webschool.entity.Student");

        Method method = studentClass.getMethod("setFirstName", String.class);

        method.invoke(student, "First Name");

        System.out.println(student.getFirstName());


    }

    private static HashMap<String, String> validateParams(HashMap<String,String> rules, HashMap<String, String[]> values, List<String> params) {
        HashMap<String, String> result = new HashMap<>();
        for (String param : params) {
           if (!values.get(param)[0].matches(rules.get(param))) {
               result.put(param, "Неверное значение поля");
           }
        }
        return result;

    }
}
