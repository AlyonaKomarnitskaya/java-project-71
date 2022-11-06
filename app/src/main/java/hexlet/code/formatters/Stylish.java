package hexlet.code.formatters;

import java.util.List;
import java.util.TreeMap;

public class Stylish {
    public static String formatStylish(List<TreeMap<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (TreeMap<String, Object> diffs : differences) {
            switch (diffs.get("status").toString()) {
                case "removed" -> result.append("  - ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                case "added" -> result.append("  + ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("newValue")).append("\n");
                case "unchanged" -> result.append("    ").append(diffs.get("key")).append(": ")
                        .append(diffs.get("oldValue")).append("\n");
                default -> {
                    result.append("  - ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("oldValue")).append("\n");
                    result.append("  + ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("newValue")).append("\n");
                }
            }

        }
        result.append("}");
        return result.toString();
    }
}
