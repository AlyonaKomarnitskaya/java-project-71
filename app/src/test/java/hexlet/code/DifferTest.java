package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

public class DifferTest {

    private static String pathEmpty = "src/test/resources/testempty.json";
    private static String pathOrig = "src/test/resources/testorig.json";
    private static String pathSame = "src/test/resources/testsame.json";
    private static String pathTest1 = "src/test/resources/test1.json";
    private static String pathTest2 = "src/test/resources/test2.json";
    private static String pathTest3 = "src/test/resources/test3.json";

    // ориг и пустой
    @Test
    public void testDiffer1() throws Exception {
        String expected = """
                {
                  - game: TLOU
                  - release date: 2013
                  - sequel: true
                  - studio: Naughty Dog
                }""";
        Map<String, Object> map1 = Parser.parser(pathOrig);
        Map<String, Object> map2 = Parser.parser(pathEmpty);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // ориг и такой же
    @Test
    public void testDiffer2() throws Exception {
        String expected = """
                {
                    game: TLOU
                    release date: 2013
                    sequel: true
                    studio: Naughty Dog
                }""";

        Map<String, Object> map1 = Parser.parser(pathOrig);
        Map<String, Object> map2 = Parser.parser(pathSame);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // удалён
    @Test
    public void testDiffer3() throws Exception {
        String expected = """
                {
                    game: TLOU
                    release date: 2013
                  - sequel: true
                    studio: Naughty Dog
                }""";

        Map<String, Object> map1 = Parser.parser(pathOrig);
        Map<String, Object> map2 = Parser.parser(pathTest1);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // добавлен
    @Test
    public void testDiffer4() throws Exception {
        String expected = """
                {
                    game: TLOU
                    release date: 2013
                    sequel: true
                    studio: Naughty Dog
                  + tv adaptation: true
                }""";

        Map<String, Object> map1 = Parser.parser(pathOrig);
        Map<String, Object> map2 = Parser.parser(pathTest2);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }

    // изменён
    @Test
    public void testDiffer5() throws Exception {
        String expected = """
                {
                  - game: TLOU
                  + game: TLOU2
                  - release date: 2013
                  + release date: 2020
                  - sequel: true
                  + sequel: false
                    studio: Naughty Dog
                }""";

        Map<String, Object> map1 = Parser.parser(pathOrig);
        Map<String, Object> map2 = Parser.parser(pathTest3);
        String actual = Differ.generate(map1, map2);
        assertEquals(expected, actual);

    }
}
