package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class DifferTest {

    private static String pathEmpty = "./app/src/test/resources/testempty.json";
    private static String pathOrig = "./app/src/test/resources/testorig.json";
    private static String pathSame = "./app/src/test/resources/testsame.json";
    private static String pathTest1 = "./app/src/test/resources/test1.json";
    private static String pathTest2 = "./app/src/test/resources/test2.json";
    private static String pathTest3 = "./app/src/test/resources/test3.json";

    // ориг и пустой
    @Test
    public static void testDiffer1() throws Exception {
        String expected = """
                {
                    - game: TLOU
                    - release date: 2013
                    - sequel: true
                    - studio: Naughty Dog
                }
                """;
        Path path1 = Paths.get(pathEmpty).toAbsolutePath();
        Path path2 = Paths.get(pathOrig).toAbsolutePath();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(json1, Map.class);
        Map<String, Object> map2 = mapper.readValue(json2, Map.class);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // ориг и такой же
    @Test
    public static void testDiffer2() throws Exception {
        String expected = """
                {
                    game: TLOU
                    release date: 2013
                    sequel: true
                    studio: Naughty Dog
                }
                """;
        Path path1 = Paths.get(pathSame).toAbsolutePath();
        Path path2 = Paths.get(pathOrig).toAbsolutePath();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(json1, Map.class);
        Map<String, Object> map2 = mapper.readValue(json2, Map.class);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // удалён
    @Test
    public static void testDiffer3() throws Exception {
        String expected = """
                {
                    game: TLOU
                    release date: 2013
                  - sequel: true
                    studio: Naughty Dog
                }
                """;
        Path path1 = Paths.get(pathTest1).toAbsolutePath();
        Path path2 = Paths.get(pathOrig).toAbsolutePath();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(json1, Map.class);
        Map<String, Object> map2 = mapper.readValue(json2, Map.class);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // добавлен
    @Test
    public static void testDiffer4() throws Exception {
        String expected = """
                {
                    game: TLOU
                    release date: 2013
                    sequel: true
                    studio: Naughty Dog
                  + tv adaptation: true
                }
                """;
        Path path1 = Paths.get(pathTest2).toAbsolutePath();
        Path path2 = Paths.get(pathOrig).toAbsolutePath();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(json1, Map.class);
        Map<String, Object> map2 = mapper.readValue(json2, Map.class);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // изменён
    @Test
    public static void testDiffer5() throws Exception {
        String expected = """
                {
                  - game: TLOU
                  + game: TLOU2
                  - release date: 2013
                  + release date: 2020
                  - sequel: true
                  + sequel: false
                    studio: Naughty Dog
                }
                """;
        Path path1 = Paths.get(pathTest3).toAbsolutePath();
        Path path2 = Paths.get(pathOrig).toAbsolutePath();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(json1, Map.class);
        Map<String, Object> map2 = mapper.readValue(json2, Map.class);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }
}
