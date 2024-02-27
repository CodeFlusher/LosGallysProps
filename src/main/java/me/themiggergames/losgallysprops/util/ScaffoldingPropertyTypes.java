package me.themiggergames.losgallysprops.util;

import net.minecraft.util.StringIdentifiable;

public enum ScaffoldingPropertyTypes implements StringIdentifiable {
    NONE("none"),
    STANDARD("std"),
    EXTENDED("extended");

    private final String str;

    ScaffoldingPropertyTypes(String str) {
        this.str = str;
    }

    @Override
    public String asString() {
        return str;
    }
}
