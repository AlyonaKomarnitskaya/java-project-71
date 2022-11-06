package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;

public class GenDifference {

    public static List<TreeMap<String, Object>> differ(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        List<TreeMap<String, Object>> result = new ArrayList<>();
        TreeMap<String, String> newMap1 = makingString(map1);
        TreeMap<String, String> newMap2 = makingString(map2);
        Set<String> keySet = new TreeSet<>(newMap1.keySet());
        keySet.addAll(newMap2.keySet());

        for (String key : keySet) {
            TreeMap<String, Object> map = new TreeMap<>();
            if (newMap1.containsKey(key) && newMap2.containsKey(key)) {
                if (newMap1.get(key).equals(newMap2.get(key))) {
                    map.put("key", key);
                    map.put("oldValue", newMap1.get(key));
                    map.put("status", "unchanged");
                } else {
                    map.put("key", key);
                    map.put("oldValue", newMap1.get(key));
                    map.put("newValue", newMap2.get(key));
                    map.put("status", "updated");
                }
            } else if (newMap1.containsKey(key) && !newMap2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", newMap1.get(key));
                map.put("status", "removed");
            } else {
                map.put("key", key);
                map.put("newValue", newMap2.get(key));
                map.put("status", "added");
            }
            result.add(map);
        }
        return result;
    }
    public static TreeMap<String, String> makingString(Map<String, Object> map) {
        TreeMap<String, String> stringToMap = new TreeMap<>();
        for (String key: map.keySet()) {
            if (map.get(key) != null) {
                stringToMap.put(key, map.get(key).toString());
            } else {
                stringToMap.put(key, "null");
            }
        }
        return stringToMap;
    }
}
