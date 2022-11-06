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
        String expected = "[ {\n"
                + "  \"key\" : \"chars1\",\n"
                + "  \"oldValue\" : [ \"a\", \"b\", \"c\" ],\n"
                + "  \"status\" : \"unchanged\"\n"
                + "}, {\n"
                + "  \"key\" : \"chars2\",\n"
                + "  \"newValue\" : false,\n"
                + "  \"oldValue\" : [ \"d\", \"e\", \"f\" ],\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"checked\",\n"
                + "  \"newValue\" : true,\n"
                + "  \"oldValue\" : false,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"default\",\n"
                + "  \"newValue\" : [ \"value1\", \"value2\" ],\n"
                + "  \"oldValue\" : null,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"id\",\n"
                + "  \"newValue\" : null,\n"
                + "  \"oldValue\" : 45,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"key1\",\n"
                + "  \"oldValue\" : \"value1\",\n"
                + "  \"status\" : \"removed\"\n"
                + "}, {\n"
                + "  \"key\" : \"key2\",\n"
                + "  \"newValue\" : \"value2\",\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers1\",\n"
                + "  \"oldValue\" : [ 1, 2, 3, 4 ],\n"
                + "  \"status\" : \"unchanged\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers2\",\n"
                + "  \"newValue\" : [ 22, 33, 44, 55 ],\n"
                + "  \"oldValue\" : [ 2, 3, 4, 5 ],\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers3\",\n"
                + "  \"oldValue\" : [ 3, 4, 5 ],\n"
                + "  \"status\" : \"removed\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers4\",\n"
                + "  \"newValue\" : [ 4, 5, 6 ],\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"obj1\",\n"
                + "  \"newValue\" : {\n"
                + "    \"nestedKey\" : \"value\",\n"
                + "    \"isNested\" : true\n"
                + "  },\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting1\",\n"
                + "  \"newValue\" : \"Another value\",\n"
                + "  \"oldValue\" : \"Some value\",\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting2\",\n"
                + "  \"newValue\" : 300,\n"
                + "  \"oldValue\" : 200,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting3\",\n"
                + "  \"newValue\" : \"none\",\n"
                + "  \"oldValue\" : true,\n"
                + "  \"status\" : \"updated\"\n"
                + "} ]";
        assertEquals(expected, Differ.generate(path1, path2, "json"));
    }

    @Test
    public void testJson2() throws Exception {
        String expected = "[ {\n"
                + "  \"key\" : \"chars1\",\n"
                + "  \"oldValue\" : [ \"a\", \"b\", \"c\" ],\n"
                + "  \"status\" : \"unchanged\"\n"
                + "}, {\n"
                + "  \"key\" : \"chars2\",\n"
                + "  \"newValue\" : false,\n"
                + "  \"oldValue\" : [ \"d\", \"e\", \"f\" ],\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"checked\",\n"
                + "  \"newValue\" : true,\n"
                + "  \"oldValue\" : false,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"default\",\n"
                + "  \"newValue\" : [ \"value1\", \"value2\" ],\n"
                + "  \"oldValue\" : null,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"id\",\n"
                + "  \"newValue\" : null,\n"
                + "  \"oldValue\" : 45,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"key1\",\n"
                + "  \"oldValue\" : \"value1\",\n"
                + "  \"status\" : \"removed\"\n"
                + "}, {\n"
                + "  \"key\" : \"key2\",\n"
                + "  \"newValue\" : \"value2\",\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers1\",\n"
                + "  \"oldValue\" : [ 1, 2, 3, 4 ],\n"
                + "  \"status\" : \"unchanged\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers2\",\n"
                + "  \"newValue\" : [ 22, 33, 44, 55 ],\n"
                + "  \"oldValue\" : [ 2, 3, 4, 5 ],\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers3\",\n"
                + "  \"oldValue\" : [ 3, 4, 5 ],\n"
                + "  \"status\" : \"removed\"\n"
                + "}, {\n"
                + "  \"key\" : \"numbers4\",\n"
                + "  \"newValue\" : [ 4, 5, 6 ],\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"obj1\",\n"
                + "  \"newValue\" : {\n"
                + "    \"nestedKey\" : \"value\",\n"
                + "    \"isNested\" : true\n"
                + "  },\n"
                + "  \"status\" : \"added\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting1\",\n"
                + "  \"newValue\" : \"Another value\",\n"
                + "  \"oldValue\" : \"Some value\",\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting2\",\n"
                + "  \"newValue\" : 300,\n"
                + "  \"oldValue\" : 200,\n"
                + "  \"status\" : \"updated\"\n"
                + "}, {\n"
                + "  \"key\" : \"setting3\",\n"
                + "  \"newValue\" : \"none\",\n"
                + "  \"oldValue\" : true,\n"
                + "  \"status\" : \"updated\"\n"
                + "} ]";
        assertEquals(expected, Differ.generate(path3, path4, "json"));
    }
}



