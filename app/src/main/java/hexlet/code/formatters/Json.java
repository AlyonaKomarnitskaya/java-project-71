package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Json {
    public static String formatJson(Map<String, Object> map1, Map<String, Object> map2)
            throws JsonProcessingException {
        Map<String, String> resultMap = Differ.differ(map1, map2);
        List<String> keysList = new ArrayList<>(resultMap.keySet());
        Collections.sort(keysList);
        Map<String, Object> result = new HashMap<>();
        for (String key : keysList) {
            switch (resultMap.get(key)) {
                case "same":
                    result.put("  " + key, map2.get(key));
                    break;
                case "removed":
                    result.put("- " + key, map1.get(key));
                    break;
                case "added":
                    result.put("+ " + key, map2.get(key));
                    break;
                case "updated":
                    result.put("- " + key, map1.get(key));
                    result.put("+ " + key, map2.get(key));
                    break;
                default:
                    System.out.println("Error");
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(result);
    }
}
