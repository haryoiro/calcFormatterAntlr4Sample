/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package calcparser;

import calcparser.antlr.CalcLexer;
import calcparser.antlr.CalcParser;
import calcparser.formatter.CalcFormatter;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import static java.lang.System.exit;

public class App {

    private static String usage = """
            hogr
            """;

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println(usage);
            exit(0);
        }

        String targetString = args[0];

        var stream = CharStreams.fromString(targetString);
        var lexer = new CalcLexer(stream);
        var tokens = new CommonTokenStream(lexer);
        var parser = new CalcParser(tokens);

        // パースを行い、ParseTree（構文木）を取得
        CalcParser.StartContext tree = parser.start();

        // Visitorを作成し、構文木を走査
        CalcFormatter formatter = new CalcFormatter();
        String formattedCode = formatter.visit(tree);

        System.out.println(formattedCode);

    }
}
