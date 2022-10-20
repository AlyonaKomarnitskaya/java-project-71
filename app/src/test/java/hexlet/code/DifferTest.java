package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private final String path1 = "src/test/resources/step81.json";
    private final String path2 = "src/test/resources/step82.json";
    private final String path3 = "src/test/resources/step81.yml";
    private final String path4 = "src/test/resources/step82.yml";

    @Test
    public void test1() throws Exception {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        assertEquals(expected, Differ.generate(path1, path2));
    }

    @Test
    public void testStylish1() throws Exception {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        assertEquals(expected, Differ.generate(path1, path2, "stylish"));
    }

    @Test
    public void testStylish2() throws Exception {
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";
        assertEquals(expected, Differ.generate(path3, path4, "stylish"));
    }

    @Test
    public void testPlain1() throws Exception {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        assertEquals(expected, Differ.generate(path1, path2, "plain"));
    }

    @Test
    public void testPlain2() throws Exception {
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'""";
        assertEquals(expected, Differ.generate(path3, path4, "plain"));
    }

    @Test
    public void testJson1() throws Exception {
        String expected = "{\"+ id\":null,\"  numbers1\":[1,2,3,4],\"+ numbers4\":[4,5,6],\"- setting2\":200,\""
                + "- setting1\":\"Some value\",\"- default\":null,\"+ numbers2\":[22,33,44,55],\"- setting3\":true,\"  "
                + "chars1\":[\"a\",\"b\",\"c\"],\"+ setting1\":\"Another value\",\"+ checked\":true,\"+ setting2\""
                + ":300,\"+ default\":[\"value1\",\"value2\"],\"+ obj1\":{\"nestedKey\":\"value\",\"isNested\":true},\""
                + "- numbers3\":[3,4,5],\"- id\":45,\"- key1\":\"value1\",\"- chars2\":[\"d\",\"e\",\"f\"],\"+ chars2\""
                + ":false,\"+ key2\":\"value2\",\"- numbers2\":[2,3,4,5],\"+ setting3\":\"none\",\"- checked\":false}";
        assertEquals(expected, Differ.generate(path1, path2, "json"));
    }

    @Test
    public void testJson2() throws Exception {
        String expected = "{\"+ id\":null,\"  numbers1\":[1,2,3,4],\"+ numbers4\":[4,5,6],\"- setting2\":200,\""
                + "- setting1\":\"Some value\",\"- default\":null,\"+ numbers2\":[22,33,44,55],\"- setting3\":true,\"  "
                + "chars1\":[\"a\",\"b\",\"c\"],\"+ setting1\":\"Another value\",\"+ checked\":true,\"+ setting2\""
                + ":300,\"+ default\":[\"value1\",\"value2\"],\"+ obj1\":{\"nestedKey\":\"value\",\"isNested\":true},\""
                + "- numbers3\":[3,4,5],\"- id\":45,\"- key1\":\"value1\",\"- chars2\":[\"d\",\"e\",\"f\"],\"+ chars2\""
                + ":false,\"+ key2\":\"value2\",\"- numbers2\":[2,3,4,5],\"+ setting3\":\"none\",\"- checked\":false}";
        assertEquals(expected, Differ.generate(path3, path4, "json"));
    }
}



