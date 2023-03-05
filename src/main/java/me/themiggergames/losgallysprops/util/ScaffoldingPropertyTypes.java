package me.themiggergames.losgallysprops.util;

import me.themiggergames.losgallysprops.block.decorative.streetProps.ScaffoldingBlock;
import net.minecraft.util.StringIdentifiable;

public enum ScaffoldingPropertyTypes implements StringIdentifiable {
    NONE("none"),
    STANDARD("std"),
    EXTENDED("extended")
    ;

    private final String str;

    @Override
    public String asString() {
        return str;
    }

    ScaffoldingPropertyTypes(String str){
        this.str = str;
    }
}
