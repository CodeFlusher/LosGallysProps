package me.themiggergames.losgallysprops.block.decorative.road;

import me.themiggergames.losgallysprops.util.BlockRotatable;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class RoadMarking extends HorizontalFacingBlock implements BlockRotatable, Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public RoadMarking(Settings settings) {
        super(settings);
        InformativeLogger.debugMessage(this.getClass().getName() + " Init");
        setDefaultState(getDefaultState().with(WATERLOGGED, false));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.1f, 1f);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw())).with(ROTATION, BlockRotatable.getRotation(ctx.getPlayerYaw()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockRotatable.appendRotationProperties(builder);
        builder.add(WATERLOGGED);
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
}
