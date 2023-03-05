package me.themiggergames.losgallysprops.block.decorative.house;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class ModStairsBlock extends StairsBlock {

    public ModStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
    }

}
