package me.themiggergames.losgallysprops.block.decorative.house;

import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;

public class ModStairsBlock extends StairsBlock {

    public ModStairsBlock(BlockState baseBlockState, Settings settings) {
        super(baseBlockState, settings);
        InformativeLogger.debugMessage(this.getClass().getName() + " Init");
    }

}
