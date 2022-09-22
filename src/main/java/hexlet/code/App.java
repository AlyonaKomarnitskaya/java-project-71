package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "picocli 4.6.3",
        description = "Compares two configuration files and shows a difference.")
class App {

    @Parameters(description = "path to first file")
    File filepath1;

    @Parameters(description = "path to second file")
    File filepath2;

    @Option(names = {"-f", "--format=format"}, description = "output format [default: stylish]", defaultValue = "stylish")
    String format;


    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
