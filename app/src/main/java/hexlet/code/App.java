package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;


@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")

class App implements Callable<String> {
    @Parameters(description = "path to first file.")
    private String filepath1;
    @Parameters(description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"},
            description = "output format: stylish, plain, json, no-format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish")
    private String format;

    @Override
    public String call() throws Exception {
        return Differ.generate(filepath1, filepath2, format);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
