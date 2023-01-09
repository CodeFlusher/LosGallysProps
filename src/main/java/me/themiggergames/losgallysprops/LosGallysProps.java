package me.themiggergames.losgallysprops;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.block.ModBlocks;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.ModMath;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LosGallysProps implements ModInitializer {
    protected static final boolean onDebugTools = true;
    public static final ModMath unifiedMath = new ModMath();

    public static final String MOD_ID = "losgallysprops";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModSounds.registerSounds();
        ModBlockEntities.registerBlockEntities();
        LOGGER.info("LosGallysProps v0.7.0. Init.");
    }
    public static boolean isDebugEnabled(){
        return onDebugTools;
    }
}
