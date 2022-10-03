package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;
import com.fasterxml.jackson.databind.ObjectMapper;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

class App implements Callable<String> {
    @Parameters(description = "path to first file.")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "format";

    @Override
    public String call() throws Exception {
        Path path1 = Paths.get(filepath1).toAbsolutePath();
        Path path2 = Paths.get(filepath2).toAbsolutePath();
        String json1 = Files.readString(path1);
        String json2 = Files.readString(path2);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(json1, Map.class);
        Map<String, Object> map2 = mapper.readValue(json2, Map.class);


        System.out.println(Differ.generate(map1, map2));
        return Differ.generate(map1, map2);

    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
