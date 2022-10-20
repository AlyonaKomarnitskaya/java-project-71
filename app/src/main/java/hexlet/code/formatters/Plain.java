package hexlet.code.formatters;

import hexlet.code.Differ;
import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatPlain(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> resultMap = Differ.differ(map1, map2);
        List<String> keysList = new ArrayList<>(resultMap.keySet());

        Collections.sort(keysList);

        List<String> resultList = new ArrayList<>();
        for (String key : keysList) {
            switch (resultMap.get(key)) {
                case "same":
                    break;
                case "removed":
                    resultList.add("Property '" + key + "' was removed");
                    break;
                case "added":
                    resultList.add("Property '" + key + "' was added with value: " + complexValue(map2, key));
                    break;
                case "updated":
                    resultList.add("Property '" + key + "' was updated. From " + complexValue(map1, key)
                            + " to " + complexValue(map2, key));
                    break;
                default:
                    System.out.print("Error");
            }
        }
        return String.join("\n", resultList);

    }

    public static Object complexValue(Map<String, Object> map, String key) {
        Object value;
        if (map.get(key) == null || ClassUtils.isPrimitiveOrWrapper(map.get(key).getClass())) {
            value = map.get(key);
        } else if (map.get(key) instanceof String) {
            value = "'" + map.get(key) + "'";
        } else {
            value = "[complex value]";
        }
        return value;
    }
}
