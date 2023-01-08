package me.themiggergames.losgallysprops.util;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.checkerframework.checker.units.qual.C;

public interface BlockConnactable {
    enum ConnectionTypes{
        HORISONTAL_ONLY,
        EVERYTHING
    }

    final BooleanProperty SOUTH = BooleanProperty.of("north");
    final BooleanProperty NORTH = BooleanProperty.of("south");
    final BooleanProperty EAST = BooleanProperty.of("east");
    final BooleanProperty WEST = BooleanProperty.of("west");
    final BooleanProperty UP = BooleanProperty.of("up");
    final BooleanProperty DOWN = BooleanProperty.of("down");

    static void appendConnectionProperties(StateManager.Builder<Block, BlockState> stateManager, ConnectionTypes type){
        stateManager.add(NORTH);
        stateManager.add(EAST);
        stateManager.add(SOUTH);
        stateManager.add(WEST);
        if(type != ConnectionTypes.HORISONTAL_ONLY){
            stateManager.add(UP);
            stateManager.add(DOWN);
        }
    }

     static BlockState setDefaultConnectionsTo(BlockState state, boolean statement, ConnectionTypes type){
        if(type==ConnectionTypes.EVERYTHING) {
            state.with(UP, statement).with(DOWN, statement);
        }
        state.with(SOUTH, statement).with(EAST, statement).with(WEST, statement).with(NORTH, statement);
        return state;
    }
}
