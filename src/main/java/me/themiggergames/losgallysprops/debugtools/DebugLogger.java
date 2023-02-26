package me.themiggergames.losgallysprops.debugtools;


import me.themiggergames.losgallysprops.LosGallysProps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebugLogger {
    private static Logger DEBUGLOGGER = LogManager.getLogger(LosGallysProps.MOD_ID);;
    public static void sendMessage(Object object){
        DEBUGLOGGER.info(object);
    }
}
