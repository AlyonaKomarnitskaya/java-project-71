package hexlet.code.formatters;


import hexlet.code.Differ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatStylish(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> resultMap = Differ.differ(map1, map2);
        List<String> keysList = new ArrayList<>(resultMap.keySet());

        Collections.sort(keysList);

        List<String> resultList = new ArrayList<>();
        for (String key : keysList) {
            switch (resultMap.get(key)) {
                case "same":
                    resultList.add("  " + key + ": " + map2.get(key));
                    break;
                case "removed":
                    resultList.add("- " + key + ": " + map1.get(key));
                    break;
                case "added":
                    resultList.add("+ " + key + ": " + map2.get(key));
                    break;
                case "updated":
                    resultList.add("- " + key + ": " + map1.get(key));
                    resultList.add("+ " + key + ": " + map2.get(key));
                    break;
                default:
                    System.out.print("Error");
            }
        }
        return "{\n" + "  " + String.join("\n  ", resultList) + "\n}";

    }
}
