package me.themiggergames.losgallysprops.block.decorative;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class VerticalSlab extends HorizontalFacingBlock implements Waterloggable {

    protected static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(WATERLOGGED, false);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        SymmetricVoxelShapeController voxelShapeController = new SymmetricVoxelShapeController(1, 0.5f, 1,0, 0,0);
            return voxelShapeController.getVoxelOutline(state.get(FACING));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(WATERLOGGED);
        stateManager.add(FACING);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            // This is for 1.17 and below: world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public VerticalSlab(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false).with(FACING, Direction.NORTH));
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
    }
}
