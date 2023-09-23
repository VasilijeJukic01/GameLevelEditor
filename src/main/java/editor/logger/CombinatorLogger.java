package editor.logger;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public class CombinatorLogger implements Logger {

    private final Function<String, String> logFunction;

    private CombinatorLogger(Function<String, String> logFunction) {
        this.logFunction = logFunction;
    }

    public static CombinatorLogger createLogger() {
        Combinator<String> combinator = Combinator.combine(
                CombinatorLogger::logToConsole,
                CombinatorLogger::logToFile
        );
        return new CombinatorLogger(combinator.apply(x -> x));
    }

    public void log(String message, LogType type) {
        String timestamp = getCurrentTimestamp();
        String formattedMessage = "[" + type + "] [" + timestamp + "] " + message;
        logFunction.apply(formattedMessage);
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        return dateFormat.format(now);
    }

    private static String logToConsole(String message) {
        System.out.println(message);
        return message;
    }

    private static String logToFile(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/log.txt", true))) {
            writer.println(message);
        }
        catch (Exception ignored) {}
        return message;
    }

}
