package me.themiggergames.losgallysprops.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public interface BlockConnactable {
    //Properties
    BooleanProperty SOUTH = BooleanProperty.of("south");
    BooleanProperty NORTH = BooleanProperty.of("north");
    BooleanProperty EAST = BooleanProperty.of("east");
    BooleanProperty WEST = BooleanProperty.of("west");
    BooleanProperty UP = BooleanProperty.of("up");
    BooleanProperty DOWN = BooleanProperty.of("down");

    static BooleanProperty getProperty(Direction dir) {
        switch (dir) {
            case EAST:
                return EAST;
            case DOWN:
                return DOWN;
            case UP:
                return UP;
            case WEST:
                return WEST;
            case NORTH:
                return NORTH;
            default:
                return SOUTH;
        }
    }

    static void appendConnectionProperties(StateManager.Builder<Block, BlockState> stateManager, ConnectionTypes type) {
        //Appending properties for block
        stateManager.add(NORTH);
        stateManager.add(EAST);
        stateManager.add(SOUTH);
        stateManager.add(WEST);
        if (type != ConnectionTypes.HORISONTAL_ONLY) {
            //Appending Some properties if someone needs this.
            stateManager.add(UP);
            stateManager.add(DOWN);
        }
    }

    static BlockState setDefaultConnectionsTo(BlockState state, boolean statement, ConnectionTypes type) {
        //Maybe this will help sometimes.
        if (type == ConnectionTypes.EVERYTHING) {
            state.with(UP, statement).with(DOWN, statement);
        }
        state.with(SOUTH, statement).with(EAST, statement).with(WEST, statement).with(NORTH, statement);
        return state;
    }

    boolean canConnect(WorldAccess world, BlockPos pos, Direction dir);

    boolean canConnect(World world, BlockPos pos, Direction dir);

    //Available types of connections with neighbouring blocks
    enum ConnectionTypes {
        HORISONTAL_ONLY,
        EVERYTHING
    }
}
