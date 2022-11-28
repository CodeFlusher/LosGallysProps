package me.themiggergames.losgallysprops.block.decorative.streetProps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import java.util.stream.Stream;

public class DrainPipe extends Block implements Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    // Let default value of the WATERLOGGED property become ``false``
    public DrainPipe(Settings settings) {
        super(settings.noCollision());
        setDefaultState(this.stateManager.getDefaultState()
                .with(WATERLOGGED, false));
    }

    public static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 15, 16, 16, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 1),
            Block.createCuboidShape(15, 0, 1, 16, 16, 15),
            Block.createCuboidShape(0, 0, 1, 1, 16, 15),
            Block.createCuboidShape(5, 0, 1, 7, 16, 2),
            Block.createCuboidShape(3, 0, 1, 5, 16, 3),
            Block.createCuboidShape(1, 0, 1, 3, 16, 3),
            Block.createCuboidShape(1, 0, 3, 3, 16, 5),
            Block.createCuboidShape(1, 0, 5, 2, 16, 7),
            Block.createCuboidShape(9, 0, 1, 11, 16, 2),
            Block.createCuboidShape(11, 0, 1, 13, 16, 3),
            Block.createCuboidShape(13, 0, 1, 15, 16, 3),
            Block.createCuboidShape(13, 0, 3, 15, 16, 5),
            Block.createCuboidShape(14, 0, 5, 15, 16, 7),
            Block.createCuboidShape(5, 0, 14, 7, 16, 15),
            Block.createCuboidShape(3, 0, 13, 5, 16, 15),
            Block.createCuboidShape(1, 0, 13, 3, 16, 15),
            Block.createCuboidShape(1, 0, 11, 3, 16, 13),
            Block.createCuboidShape(1, 0, 9, 2, 16, 11),
            Block.createCuboidShape(9, 0, 14, 11, 16, 15),
            Block.createCuboidShape(11, 0, 13, 13, 16, 15),
            Block.createCuboidShape(13, 0, 13, 15, 16, 15),
            Block.createCuboidShape(13, 0, 11, 15, 16, 13),
            Block.createCuboidShape(14, 0, 9, 15, 16, 11)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_N;
    }

    // Make the block recognize the property, otherwise setting the property will through exceptions.
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.WATERLOGGED);
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
