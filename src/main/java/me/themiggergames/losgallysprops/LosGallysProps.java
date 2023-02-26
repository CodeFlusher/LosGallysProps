package me.themiggergames.losgallysprops;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.block.ModBlocks;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LosGallysProps implements ModInitializer {
    protected static final boolean onDebugTools = true;

    public static final String MOD_ID = "losgallysprops";

    @Override
    public void onInitialize() {
        InformativeLogger.info("LosGallysProps v1.0.0. Init.");
        ModItems.registerItems();
        DebugLogger.sendMessage("Items Initialized");
        ModBlocks.registerBlocks();
        DebugLogger.sendMessage("Blocks Initialized");
        ModSounds.registerSounds();
        DebugLogger.sendMessage("Sounds Initialized");
        ModBlockEntities.registerBlockEntities();
        DebugLogger.sendMessage("Block Entities Initialized");
        LosGallysPropsNetworking.initializeServerNetworking();
        DebugLogger.sendMessage("Initializing Server Packet Receiver");
        InformativeLogger.info("Hey stranger! Yes, you. HELLO!!!!!");
        InformativeLogger.info("What's gonna ruin this game, nothing right?");
        InformativeLogger.info("LOL NOT. I'm gonna do this 😈");
    }
    public static boolean isDebugEnabled(){
        return onDebugTools;
    }
}
