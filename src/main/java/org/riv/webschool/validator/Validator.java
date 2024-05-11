package org.riv.webschool.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    public Map<String, String> validateParams(Map<String,String> rules, Map<String, String[]> values, List<String> params) {
        Map<String, String> result = new HashMap<>();
        for (String param : params) {
            if (values.get(param) == null) {
                result.put(param, "Отсутствует необходимый параметр запроса.");
                continue;
            }
            if (!values.get(param)[0].matches(rules.get(param))) {
                result.put(param, "Неверное значение поля");
            }
        }
        return result;

    }
}
