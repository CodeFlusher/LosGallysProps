package me.themiggergames.losgallysprops.util;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public enum PlacingTypes implements StringIdentifiable {
    TOP("top"),
    BOTTOM("bottom"),
    WALL("wall");

    private final String str;

    PlacingTypes(String string) {
        this.str = string;
    }

    public static PlacingTypes getPlacement(Direction dir) {
        return switch (dir) {
            case UP -> TOP;
            case DOWN -> BOTTOM;
            default -> WALL;
        };
    }

    @Override
    public String asString() {
        return str;
    }
}
