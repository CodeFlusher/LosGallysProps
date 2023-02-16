package me.themiggergames.losgallysprops.debugtools;


import me.themiggergames.losgallysprops.LosGallysProps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DebugLogger {
    private static final Logger DEBUGLOGGER = LogManager.getLogger(LosGallysProps.MOD_ID);
    public static void sendMessage(Object object){
        if (LosGallysProps.isDebugEnabled())
            DEBUGLOGGER.info(object);
    }

}
