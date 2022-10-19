package hexlet.code;


import java.util.HashMap;
import java.util.Map;

public class Differ {
    public static String generate(String pathfile1, String pathfile2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parser(pathfile1);
        Map<String, Object> map2 = Parser.parser(pathfile2);

        String result = Formatter.formatStyle(map1, map2, format);
        System.out.println(result);

        return result;
    }
    public static String generate(String pathfile1, String pathfile2) throws Exception {
        Map<String, Object> map1 = Parser.parser(pathfile1);
        Map<String, Object> map2 = Parser.parser(pathfile2);

        String result = Formatter.formatStyle(map1, map2, "stylish");
        System.out.println(result);

        return result;
    }

    public static Map<String, String> makingString(Map<String, Object> map) {
        Map<String, String> stringToMap = new HashMap<>();
        for (String key: map.keySet()) {
            if (map.get(key) != null) {
                stringToMap.put(key, map.get(key).toString());
            } else {
                stringToMap.put(key, "null");
            }
        }
        return stringToMap;
    }

    public static Map<String, String> differ(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new HashMap<>();
        Map<String, String> newMap1 = makingString(map1);
        Map<String, String> newMap2 = makingString(map2);

        result.putAll(newMap1);
        result.putAll(newMap2);

        for (String key : result.keySet()) {
            if (newMap1.containsKey(key) && newMap2.containsKey(key)) {
                if (newMap1.get(key).equals(newMap2.get(key))) {
                    result.put(key,  "same");
                } else {
                    result.put(key, "updated");
                }
            } else if (newMap1.containsKey(key) && !newMap2.containsKey(key)) {
                result.put(key, "removed");
            } else {
                result.put(key, "added");
            }
        }
        return result;
    }
}
