package me.themiggergames.losgallysprops.util;

import net.minecraft.util.StringIdentifiable;

import java.util.Objects;

public enum ProgressionProperty implements StringIdentifiable {
    DISABLED("disabled"),
    BEING_ENABLED("being_enabled"),
    ENABLED("enabled"),
    BEING_DISABLED("being_disabled");

    private final String name;

    ProgressionProperty(String string) {
        this.name = string;
    }

    @Override
    public String asString() {
        return name;
    }
    public ProgressionProperty switchProperty(){
        if(this.isDisabledOrBeingDisabled()){
            return BEING_ENABLED;
        }
        return BEING_DISABLED;
    }

    public ProgressionProperty finalizeProperty(){
        if (this == BEING_DISABLED){
            return DISABLED;
        }
        if (this == BEING_ENABLED){
            return ENABLED;
        }
        return this;
    }
    public static ProgressionProperty parseProperty(String str){
        for (ProgressionProperty value : values()) {
            if (Objects.equals(value.name, str)){
                return value;
            }
        }
        return null;
    }
    public boolean isChangingState(){
        return this == BEING_DISABLED || this == BEING_ENABLED;
    }
    public boolean isEnabledOrBeingEnabled(){
        return this == ENABLED || this == BEING_ENABLED;
    }
    public boolean isDisabledOrBeingDisabled(){
        return this == DISABLED || this == BEING_DISABLED;
    }
}
