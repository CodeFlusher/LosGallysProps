package me.themiggergames.losgallysprops.util;

import me.themiggergames.losgallysprops.LosGallysProps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

public class InformativeLogger {

    private static Object lastDebugMessage;
    private static Object lastDebugNamespace;
    private static Long timesCalledPrint = 0L;
    private static final int accumulationBase = 50;
    private static int accumulationMultiplier = 1;
    private static final Logger LOGGER = LogManager.getLogger(LosGallysProps.MOD_ID);
    public static final LogRunnable INFO = (namespace, item) -> LOGGER.info("[ " + namespace + " " + "]: " + item);
    public static final LogRunnable WARN = (namespace, item) -> LOGGER.warn("[ " + namespace + " " + "]: " + item);
    public static final LogRunnable ERROR = (namespace, item) -> LOGGER.error("[ " + namespace + " " + "]: " + item);
    public static final LogRunnable DEBUG = LosGallysProps.isDebugEnabled() ? (namespace, item) -> {
        if ((Objects.equals(namespace, lastDebugNamespace) && Objects.equals(item, lastDebugMessage) )){
            if (timesCalledPrint < 2){
                LOGGER.info("[ " + lastDebugNamespace + " | DEBUG " + "]: " + lastDebugMessage);
                timesCalledPrint ++;
                return;
            }
            if (timesCalledPrint >= (long) Math.pow(2, accumulationMultiplier)){
                LOGGER.info("This debug message was called " + timesCalledPrint + " times.");
                LOGGER.info("[ " + lastDebugNamespace + " | DEBUG " + "]: " + lastDebugMessage);
                lastDebugMessage = item;
                lastDebugNamespace = namespace;
                timesCalledPrint = 0L;
                accumulationMultiplier += 1;
                return;
            }
            timesCalledPrint ++;
            return;
        } else if (timesCalledPrint > 1) {
            LOGGER.info("This debug message was called " + timesCalledPrint + " times.");
            LOGGER.info("[ " + lastDebugNamespace + " | DEBUG " + "]: " + lastDebugMessage);
        }
        accumulationMultiplier = 1;
        lastDebugMessage = item;
        lastDebugNamespace = namespace;
        timesCalledPrint = 0L;
        LOGGER.info("[ " + namespace + " | DEBUG " + "]: " + item);
    } : ((namespace, item) -> {

    });

    public static void info(String namespace, Object... message) {
        for (Object o : message) {
            INFO.run(namespace, o);
        }
    }

    public static void info(String namespace, Object message) {
        INFO.run(namespace, message);
    }

    public static void warn(String namespace, Object... message) {
        for (Object o : message) {
            WARN.run(namespace, o);
        }
    }

    public static void warn(String namespace, Object message) {
        WARN.run(namespace, message);
    }

    public static void error(String namespace, Object... message) {
        for (Object o : message) {
            ERROR.run(namespace, o);
        }
    }

    public static void error(String namespace, Object message) {
        ERROR.run(namespace, message);
    }

    public static void error(String namespace, Exception e) {
        error(namespace, "Message: " + e.getMessage());
        error(namespace, "Cause: " + e.getCause());
        error(namespace, "Stack trace: ");
        error(namespace, e.getStackTrace());
    }

    @Deprecated
    public static void info(Object message) {
        INFO.run(LosGallysProps.MOD_ID, message);
    }

    public static void debugMessage(String namespace, Object message) {
        DEBUG.run(namespace, message);
    }

    public static void debugMessage(String namespace, Object... messages) {
        for (var message : messages)
            DEBUG.run(namespace, message);
    }

    @Deprecated
    public static void debugMessage(Object message) {
        DEBUG.run(LosGallysProps.MOD_ID, message);
    }

    public static void informativeLog(String titleMessage, @Nullable ArrayList<String> description, @NotNull ArrayList<LogItem> logItems) {
        LOGGER.info("| " + titleMessage);

        if (description != null) {
            for (int i = 0; i < logItems.size() - 1; i++) {
                LOGGER.info("| " + description.get(i));
            }
        }
        for (int i = 0; i < logItems.size() - 1; i++) {
            LOGGER.info("# " + logItems.get(i).title());
            LOGGER.info("#      " + logItems.get(i).contains());
            LOGGER.info("#-------------------#");
        }
    }

    public static void informativeLog(LogRunnable runnable, String namespace, String titleMessage, String description, @NotNull LogItem... logItems) {
        runnable.run(namespace, titleMessage);

        if (description != null) {
            info(namespace, description);
        }
        for (var logItem : logItems) {
            runnable.run(namespace, logItem.title());
            runnable.run(namespace, logItem.contains());
        }
    }

    public record LogItem(String title, Object contains) {
    }

}
