package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parserYAML(String yaml) throws Exception {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(yaml, Map.class);
    }

    public static Map<String, Object> parserJSON(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Map.class);
    }

    public static Map<String, Object> parser(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath();
        String filepathContent = Files.readString(path);
        Map<String, Object> map = null;
        if (filepath.endsWith("json")) {
            map = parserJSON(filepathContent);
        } else if (filepath.endsWith("yml")) {
            map = parserYAML(filepathContent);
        }
        return map;
    }
}
