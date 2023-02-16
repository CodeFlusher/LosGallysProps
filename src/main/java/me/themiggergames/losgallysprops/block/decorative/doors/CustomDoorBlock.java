package me.themiggergames.losgallysprops.block.decorative.doors;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.ProgressionProperty;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CustomDoorBlock extends HorizontalFacingBlock implements BlockEntityProvider, StyledBlock {

    public static final Integer MAX_STYLE = 1;
    public static final EnumProperty<ProgressionProperty> ISOPEN = EnumProperty.of("isopen", ProgressionProperty.class);
    public static final IntProperty STYLES = IntProperty.of("style", 0, MAX_STYLE);

    public CustomDoorBlock(Settings settings) {
        super(settings);
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ISOPEN).add(FACING);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        onPower(world, state, pos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(neighborState.emitsRedstonePower()){
            if(neighborState.getWeakRedstonePower(world, neighborPos, direction)>=1){
               return onPower(world, state, pos);
            }
        }
        return state;
    }

    private BlockState onPower(WorldAccess world,BlockState state, BlockPos pos){
        switch (state.get(ISOPEN)){
            case DISABLED, BEING_DISABLED:
                world.setBlockState(pos, state.with(ISOPEN, ProgressionProperty.BEING_ENABLED),0);
                world.createAndScheduleBlockTick(pos, state.getBlock(), 10);
                return state.with(ISOPEN, ProgressionProperty.BEING_ENABLED);
            case BEING_ENABLED:
                world.setBlockState(pos,state.with(ISOPEN, ProgressionProperty.ENABLED), 0);
                world.createAndScheduleBlockTick(pos, state.getBlock(), 10);
                return state.with(ISOPEN, ProgressionProperty.ENABLED);
            default:
                world.createAndScheduleBlockTick(pos, state.getBlock(), 10);
                return state;
        }
    }
    @Override
    public int getMaxStyle() {
        return MAX_STYLE;
    }

    @Override
    public IntProperty getIntProperty() {
        return STYLES;
    }

    @Override
    public boolean usuesUnstandartTiteling() {
        return false;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CustomDoorEntity(pos, state);
    }
}
