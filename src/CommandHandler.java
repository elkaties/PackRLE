import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class CommandHandler {
    @Option(name = "-z", forbids = {"-u"}, metaVar = "Запаковать?")
    private boolean encode;

    @Option(name = "-u", metaVar = "Распаковать?")
    private boolean decode;

    @Option(name = "-out", metaVar = "Имя выходного файла")
    private String ofn = "";

    @Argument(required = true, metaVar = "Имя входного файла")
    private String ifn;


    public static void main(String[] args) {
        CommandHandler cmdH = new CommandHandler();

        CmdLineParser cmdLineParser = new CmdLineParser(cmdH);
        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
            return;
        }
        if (cmdH.decode == cmdH.encode) {
            System.err.print("Обязательно должна быть указана одна из опций: -z, -u");
            System.exit(0);
        }

        FileHandle fileHandle;
        if (cmdH.encode) {
            fileHandle = new FileEncoder();
        } else {
            fileHandle = new FileDecoder();
        }
        fileHandle.pack(cmdH.ifn, cmdH.ofn);
    }
}
