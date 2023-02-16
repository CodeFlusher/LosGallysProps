package me.themiggergames.losgallysprops.util;

import net.minecraft.util.StringIdentifiable;

public enum ProgressionProperty implements StringIdentifiable {
    DISABLED("disabled"),
    BEING_ENABLED("being_enabled"),
    ENABLED("enabled"),
    BEING_DISABLED("being_disabled")
    ;

    private String name;
    ProgressionProperty(String string){
        this.name = string;
    }

    @Override
    public String asString() {
        return name;
    }
}
