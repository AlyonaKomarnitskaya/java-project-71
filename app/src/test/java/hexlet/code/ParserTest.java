package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

public class ParserTest {
    private final String path1 = "src/test/resources/filepath1.yml";
    private final String path2 = "src/test/resources/filepath2.yml";

    private final String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";



    @Test
    public void testParser() throws Exception {
        Map<String, Object> map1 = Parser.parser(path1);
        Map<String, Object> map2 = Parser.parser(path2);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);
    }
}
