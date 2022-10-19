package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String formatStyle(
            Map<String, Object> map1, Map<String, Object> map2, String format) throws JsonProcessingException {
        switch (format) {
            case "stylish":
                return Stylish.formatStylish(map1, map2);
            case "plain":
                return Plain.formatPlain(map1, map2);
            case "json":
                return Json.formatJson(map1, map2);
            default:
                System.out.println("Format" + format + "is not correct!");
        }
        return Stylish.formatStylish(map1, map2);
    }
}
