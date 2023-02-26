package me.themiggergames.losgallysprops.util;

import me.themiggergames.losgallysprops.LosGallysProps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class InformativeLogger {

    public static class LogItem{
        private String title;
        private Object contains;
        public LogItem(String title, Object contains){
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

    private static final Logger LOGGER = LogManager.getLogger(LosGallysProps.MOD_ID);

    public static void info(Object message){
        LOGGER.info(message);
    }

    public static void informativeLog(String titleMessage, ArrayList<String> description, ArrayList<LogItem> logItems){
        LOGGER.info("| "+titleMessage);

        for(int i = 0; i<logItems.size()-1; i++) {
            LOGGER.info("| "+description.get(i));
        }

        for(int i = 0; i<logItems.size()-1; i++){
            LOGGER.info("# "+logItems.get(i).getTitle());
            LOGGER.info("#      "+logItems.get(i).getContains().toString());
            LOGGER.info("#-------------------#");
        }
    }
}
