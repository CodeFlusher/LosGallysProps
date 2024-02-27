package me.themiggergames.losgallysprops.block.decorative.road;

import me.themiggergames.losgallysprops.block.decorative.streetProps.FancyPost;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightBlock;
import me.themiggergames.losgallysprops.util.BlockConnactable;
import me.themiggergames.losgallysprops.util.BlockRotatable;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class RoadSign extends HorizontalFacingBlock implements BlockRotatable, Waterloggable, BlockConnactable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public RoadSign(Settings settings) {
        super(settings);

        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1f);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        return getDefaultState().with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw())).with(ROTATION, BlockRotatable.getRotation(ctx.getPlayerYaw())).with(NORTH, canConnect(ctx.getWorld(), blockPos, Direction.NORTH))
                .with(SOUTH, canConnect(ctx.getWorld(), blockPos, Direction.SOUTH))
                .with(EAST, canConnect(ctx.getWorld(), blockPos, Direction.EAST))
                .with(WEST, canConnect(ctx.getWorld(), blockPos, Direction.WEST))
                .with(UP, canConnect(ctx.getWorld(), blockPos, Direction.UP))
                .with(DOWN, canConnect(ctx.getWorld(), blockPos, Direction.DOWN));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockRotatable.appendRotationProperties(builder);
        BlockConnactable.appendConnectionProperties(builder, ConnectionTypes.EVERYTHING);
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public boolean canConnect(World world, BlockPos pos, Direction dir) {
        BlockPos neighbourPos = pos.offset(dir);
        BlockState neighbourState = world.getBlockState(neighbourPos);
        Block block = world.getBlockState(neighbourPos).getBlock();
        boolean bl1 = block instanceof TrafficLightBlock;
        boolean bl2 = block instanceof RoadSign;
        boolean bl3 = block instanceof FancyPost;
        return (block.isShapeFullCube(neighbourState, world, neighbourPos) || bl2 || bl1 || bl3);
    }

    public boolean canConnect(WorldAccess world, BlockPos pos, Direction dir) {
        BlockPos neighbourPos = pos.offset(dir);
        BlockState neighbourState = world.getBlockState(neighbourPos);
        Block block = world.getBlockState(neighbourPos).getBlock();
        boolean bl1 = block instanceof TrafficLightBlock;
        boolean bl2 = block instanceof RoadSign;
        boolean bl3 = block instanceof FancyPost;
        return (block.isShapeFullCube(neighbourState, world, neighbourPos) || bl2 || bl1 || bl3);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            // This is for 1.17 and below: world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return state.with(NORTH, canConnect(world, pos, Direction.NORTH))
                .with(SOUTH, canConnect(world, pos, Direction.SOUTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(WEST, canConnect(world, pos, Direction.WEST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN));
    }
}


