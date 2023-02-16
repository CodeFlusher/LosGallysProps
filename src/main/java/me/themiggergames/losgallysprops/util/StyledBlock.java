package me.themiggergames.losgallysprops.util;

import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;

import java.util.ArrayList;

public interface StyledBlock {
    int getMaxStyle();
    IntProperty getIntProperty();
    boolean usuesUnstandartTiteling();
    ArrayList<Text> getTitlesList();
}
