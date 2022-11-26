package me.themiggergames.losgallysprops.util;

import net.minecraft.state.property.IntProperty;

public class CustomIntProperty extends IntProperty {
    public CustomIntProperty(String name, int min, int max) {
        super(name, min, max);
    }

}
