package me.themiggergames.losgallysprops.util;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;


public interface BlockRotatable {

    IntProperty ROTATION = IntProperty.of("rotation", 0, 3);

    static void appendRotationProperties(StateManager.Builder<Block, BlockState> stateManager) {
        //Appends needed properties for rotatable block
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(ROTATION);
    }

    static Direction getHeadDirection(double yaw) {
        //Unique system of head positioning.
        if (ModMath.isInRange(yaw, -15, 75, true)) {
            return Direction.NORTH;
        } else if (ModMath.isInRange(yaw, 75, 165, false)) {
            return Direction.EAST;
        } else if (ModMath.isInRange(yaw, 165, -105, true)) {
            return Direction.SOUTH;
        } else {
            return Direction.WEST;
        }
    }

    static int getRotation(double yaw) {
        //When Block is faced south, and you try to place it, it places incorrect
        //idk why, any else position is placing correctly
        //this shit should fix it
        //my eyes hurts
        if (ModMath.isInRange(yaw, 165, 180, true)) {
            return 0;
        }

        Direction dir = getHeadDirection(yaw);
        //Calculating of rotation is strange a little but its working like this.
        yaw = (Math.abs(yaw + 15)) % 90;
        int result;

        //South and west directions is inverted
        if (dir == Direction.NORTH || dir == Direction.EAST) {
            result = ModMath.getSector(0, 90, 4, yaw);
        } else {
            result = ModMath.getSector(0, 90, 4, 90 - yaw);
        }
        //4th sector is the same as 0
        if (result == 4) {
            return 0;
        }
        return result;
    }

}
