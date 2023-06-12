package me.themiggergames.losgallysprops.util;

import me.themiggergames.losgallysprops.LosGallysProps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class InformativeLogger {

    private static final Logger LOGGER = LogManager.getLogger(LosGallysProps.MOD_ID);

    public static void info(String namespace,Object message) {
        LOGGER.info("["+namespace+"]: "+message);
    }
    public static void info(Object message) {
        info(LosGallysProps.MOD_ID, message);
    }

    public static void debugInformativeLog(String titleMessage, ArrayList<String> description, ArrayList<LogItem> logItems) {
        if (LosGallysProps.isDebugEnabled())
            informativeLog(titleMessage, description, logItems);
    }

    public static void debugMessage(String namespace, Object message) {
        if (LosGallysProps.isDebugEnabled())
            info(namespace, message);
    }
    public static void debugMessage(Object message) {
        if (LosGallysProps.isDebugEnabled())
            info(LosGallysProps.MOD_ID  , message);
    }

    public static void informativeLog(String titleMessage, ArrayList<String> description, ArrayList<LogItem> logItems) {
        LOGGER.info("| " + titleMessage);

        for (int i = 0; i < logItems.size() - 1; i++) {
            LOGGER.info("| " + description.get(i));
        }

        for (int i = 0; i < logItems.size() - 1; i++) {
            LOGGER.info("# " + logItems.get(i).getTitle());
            LOGGER.info("#      " + logItems.get(i).getContains().toString());
            LOGGER.info("#-------------------#");
        }
    }

    public static class LogItem {
        private final String title;
        private final Object contains;

        public LogItem(String title, Object contains) {
            this.title = title;
            this.contains = contains;
        }

        public Object getContains() {
            return contains;
        }

        public String getTitle() {
            return title;
        }
    }
}
