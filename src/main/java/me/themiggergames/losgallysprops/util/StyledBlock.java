package me.themiggergames.losgallysprops.util;

import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public interface StyledBlock {
    int getMaxStyle();
    void changeStyle(int style, World world, BlockPos pos);

    default boolean substandardTitling(){
        return false;
    }

    default ArrayList<Text> getTitlesList(){
        return null;
    }
}
