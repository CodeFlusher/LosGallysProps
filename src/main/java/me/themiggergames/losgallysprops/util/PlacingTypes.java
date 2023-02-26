package me.themiggergames.losgallysprops.util;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public enum PlacingTypes implements StringIdentifiable {
    TOP("top"),
    BOTTOM("bottom"),
    WALL("wall");

    private final String str;

    PlacingTypes(String string){
        this.str = string;
    }

    @Override
    public String asString() {
        return str;
    }

    public static PlacingTypes getPlacement(Direction dir){
        switch (dir){
            case UP:
                return TOP;
            case DOWN:
                return BOTTOM;
            default:
                return WALL;
        }
    }
}