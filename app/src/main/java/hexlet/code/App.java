package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Map;
import java.util.concurrent.Callable;


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
        Map<String, Object> map1 = Parser.parser(filepath1);
        Map<String, Object> map2 = Parser.parser(filepath2);
        System.out.println(Differ.generate(map1, map2));
        return Differ.generate(map1, map2);

    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
