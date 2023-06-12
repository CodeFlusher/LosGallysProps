package me.themiggergames.losgallysprops;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.block.ModBlocks;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.fabricmc.api.ModInitializer;

public class LosGallysProps implements ModInitializer {

    public static final String MOD_ID = "losgallysprops";
    protected static final boolean onDebugTools = true;

    public static boolean isDebugEnabled() {
        return onDebugTools;
    }

    @Override
    public void onInitialize() {
        InformativeLogger.info("LosGallysProps v1.0.0. Init.");
        ModItems.registerItems();
        InformativeLogger.debugMessage("Items Initialized");
        ModBlocks.registerBlocks();
        InformativeLogger.debugMessage("Blocks Initialized");
        ModSounds.registerSounds();
        InformativeLogger.debugMessage("Sounds Initialized");
        ModBlockEntities.registerBlockEntities();
        InformativeLogger.debugMessage("Block Entities Initialized");
        LosGallysPropsNetworking.initializeServerNetworking();
        InformativeLogger.debugMessage("Initializing Packet Receiver");
        InformativeLogger.info("Hey stranger! Yes, you. HELLO!!!!!");
        InformativeLogger.info("What's gonna ruin this game, nothing right?");
        InformativeLogger.info("LOL NOT. I'm gonna do this 😈");
    }
}
