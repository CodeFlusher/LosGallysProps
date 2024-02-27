package me.themiggergames.losgallysprops;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.block.ModBlocks;
import me.themiggergames.losgallysprops.commands.ModCommandManager;
import me.themiggergames.losgallysprops.items.ModItemGroup;
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
        System.setProperty("libgui.allowItemUse", "true");
        Long nanoSecs = System.nanoTime();
        InformativeLogger.info("Mod Initialization","LosGallysProps v1.0.0. Init.");
        ModItems.registerItems();
        Long itemNanoSecs = System.nanoTime();
        InformativeLogger.debugMessage("Mod Initialization","Items Initialized within " + (double)(itemNanoSecs - nanoSecs) / 1000000000 );
        ModBlocks.registerBlocks();
        Long blockNanoSecs = System.nanoTime();
        InformativeLogger.debugMessage("Mod Initialization","Blocks Initialized within " + (double)(blockNanoSecs - itemNanoSecs) / 1000000000 );
        ModSounds.registerSounds();
        Long soundNanoSecs = System.nanoTime();
        InformativeLogger.debugMessage("Mod Initialization","Sounds Initialized within " + (double)(soundNanoSecs - blockNanoSecs) / 1000000000 );
        ModBlockEntities.registerBlockEntities();
        InformativeLogger.debugMessage("Mod Initialization","Block Entities Initialized");
        ModItemGroup.registerItemGroups();
        InformativeLogger.debugMessage("Mod Initialization", "Registering item groups and adding items to them");
        LosGallysPropsNetworking.initializeServerNetworking();
        LosGallysPropsNetworking.initializeClientNetworking();
        InformativeLogger.debugMessage("Mod Initialization","Initializing Packet Receiver");
        ModCommandManager.registerCommands();
        InformativeLogger.debugMessage("Mod Initialization","Registered Commands");
        InformativeLogger.info("Mod Initialization","Hey stranger! Yes, you. HELLO!!!!!");
        InformativeLogger.info("Mod Initialization","What's gonna ruin this game, nothing right?");
        InformativeLogger.info("Mod Initialization","LOL NOT. I'm gonna do this 😈");
    }
}
