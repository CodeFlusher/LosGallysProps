package me.themiggergames.losgallysprops.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public interface BlockConnactable {
    final BooleanProperty SOUTH = BooleanProperty.of("north");
    final BooleanProperty NORTH = BooleanProperty.of("south");
    final BooleanProperty EAST = BooleanProperty.of("east");
    final BooleanProperty WEST = BooleanProperty.of("west");
    final BooleanProperty UP = BooleanProperty.of("up");
    final BooleanProperty DOWN = BooleanProperty.of("down");

    static void appendConnectionProperties(StateManager.Builder<Block, BlockState> stateManager){
        stateManager.add(NORTH);
        stateManager.add(EAST);
        stateManager.add(SOUTH);
        stateManager.add(WEST);
        stateManager.add(UP);
        stateManager.add(DOWN);
    }
}
