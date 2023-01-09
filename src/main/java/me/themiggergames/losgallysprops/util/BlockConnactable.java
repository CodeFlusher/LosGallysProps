package me.themiggergames.losgallysprops.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public interface BlockConnactable {
    //Available types of connections with neighbouring blocks
    enum ConnectionTypes{
        HORISONTAL_ONLY,
        EVERYTHING
    }

    //Properties
    final BooleanProperty SOUTH = BooleanProperty.of("north");
    final BooleanProperty NORTH = BooleanProperty.of("south");
    final BooleanProperty EAST = BooleanProperty.of("east");
    final BooleanProperty WEST = BooleanProperty.of("west");
    final BooleanProperty UP = BooleanProperty.of("up");
    final BooleanProperty DOWN = BooleanProperty.of("down");

    static void appendConnectionProperties(StateManager.Builder<Block, BlockState> stateManager, ConnectionTypes type){
        //Appending properties for block
        stateManager.add(NORTH);
        stateManager.add(EAST);
        stateManager.add(SOUTH);
        stateManager.add(WEST);
        if(type != ConnectionTypes.HORISONTAL_ONLY){
            //Appending Some properties if someone needs this.
            stateManager.add(UP);
            stateManager.add(DOWN);
        }
    }

     static BlockState setDefaultConnectionsTo(BlockState state, boolean statement, ConnectionTypes type){
        //Maybe this will help sometimes.
        if(type==ConnectionTypes.EVERYTHING) {
            state.with(UP, statement).with(DOWN, statement);
        }
        state.with(SOUTH, statement).with(EAST, statement).with(WEST, statement).with(NORTH, statement);
        return state;
    }
}
