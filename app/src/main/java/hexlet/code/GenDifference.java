package hexlet.code;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GenDifference {

    public static List<TreeMap<String, Object>> differ(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        List<TreeMap<String, Object>> result = new ArrayList<>();
        for (String key :  getAllKeys(map1, map2)) {
            TreeMap<String, Object> map = new TreeMap<>();
            if (!map2.containsKey(key)) {
                map.put("status", "removed");
                map.put("key", key);
                map.put("oldValue", map1.get(key));
            } else if (!map1.containsKey(key)) {
                map.put("status", "added");
                map.put("key", key);
                map.put("newValue", map2.get(key));
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                map.put("status", "updated");
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
            } else {
                map.put("status", "unchanged");
                map.put("key", key);
                map.put("oldValue", map1.get(key));
            }
            result.add(map);
        }
        return result;
    }

    private static Set<String> getAllKeys(TreeMap<String, Object> map1, TreeMap<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }
}
