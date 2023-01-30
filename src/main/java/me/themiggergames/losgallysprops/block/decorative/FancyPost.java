package me.themiggergames.losgallysprops.block.decorative;

import me.themiggergames.losgallysprops.block.decorative.road.RoadSign;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightBlock;
import me.themiggergames.losgallysprops.util.BlockConnactable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class FancyPost extends Block implements BlockConnactable {

    public FancyPost(Settings settings) {
        super(settings);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        BlockConnactable.appendConnectionProperties(stateManager, ConnectionTypes.EVERYTHING);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        return this.getDefaultState().with(NORTH, canConnect(ctx.getWorld(), blockPos, Direction.NORTH))
            .with(SOUTH, canConnect(ctx.getWorld(), blockPos, Direction.SOUTH))
            .with(EAST, canConnect(ctx.getWorld(), blockPos, Direction.EAST))
            .with(WEST, canConnect(ctx.getWorld(), blockPos, Direction.WEST))
            .with(UP, canConnect(ctx.getWorld(), blockPos, Direction.UP))
            .with(DOWN, canConnect(ctx.getWorld(), blockPos, Direction.DOWN));

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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2f,0.2f,0.2f,0.8f,0.8f,0.8f);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(NORTH, canConnect(world, pos, Direction.SOUTH))
                .with(SOUTH, canConnect(world, pos, Direction.NORTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(WEST, canConnect(world, pos, Direction.WEST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN));
    }
}
