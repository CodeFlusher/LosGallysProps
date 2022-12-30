package me.themiggergames.losgallysprops.block.decorative;

import me.themiggergames.losgallysprops.block.decorative.road.RoadSign;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

//temporary unused cuz of this shit broken as hell.
public class FancyPost extends Block {

    public FancyPost(Settings settings) {
        super(settings);
    }
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(NORTH);
        stateManager.add(EAST);
        stateManager.add(SOUTH);
        stateManager.add(WEST);
        stateManager.add(UP);
        stateManager.add(DOWN);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockPos blockPos2 = blockPos.north();
        BlockPos blockPos3 = blockPos.east();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockPos blockPos6 = blockPos.up();
        BlockPos blockPos7 = blockPos.down();
        BlockState blockState = blockView.getBlockState(blockPos2);
        BlockState blockState2 = blockView.getBlockState(blockPos3);
        BlockState blockState3 = blockView.getBlockState(blockPos4);
        BlockState blockState4 = blockView.getBlockState(blockPos5);
        BlockState blockState5 = blockView.getBlockState(blockPos6);
        BlockState blockState6 = blockView.getBlockState(blockPos7);

        return super.getPlacementState(ctx)
                .with(NORTH, this.canConnect(blockState, blockState.isSideSolidFullSquare(blockView, blockPos2, Direction.NORTH), Direction.NORTH))
                .with(EAST, this.canConnect(blockState2, blockState2.isSideSolidFullSquare(blockView, blockPos3, Direction.WEST), Direction.WEST))
                .with(SOUTH, this.canConnect(blockState3, blockState3.isSideSolidFullSquare(blockView, blockPos4, Direction.SOUTH), Direction.SOUTH))
                .with(WEST, this.canConnect(blockState4, blockState4.isSideSolidFullSquare(blockView, blockPos5, Direction.EAST), Direction.EAST))
                .with(UP, this.canConnect(blockState5, blockState5.isSideSolidFullSquare(blockView, blockPos6, Direction.UP), Direction.UP))
                .with(DOWN, this.canConnect(blockState6, blockState6.isSideSolidFullSquare(blockView, blockPos7, Direction.DOWN), Direction.DOWN));
    }

    public boolean canConnect(BlockState state, boolean neighborIsFullSquare, Direction dir) {
        Block block = state.getBlock();
        boolean bl1 = block instanceof TrafficLightBlock;
        boolean bl2 = block instanceof RoadSign;
        return (neighborIsFullSquare || bl2 || bl1);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2f,0.2f,0.2f,0.8f,0.8f,0.8f);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Block block = world.getBlockState(pos.offset(direction.getOpposite())).getBlock();
        Direction.Axis axis = direction.getAxis();
        boolean bl = (block instanceof TrafficLightBlock || block instanceof FancyPost);
        switch (direction) {
            case NORTH:
                return (BlockState) state.with(NORTH, bl);
            case SOUTH:
                return (BlockState) state.with(SOUTH, bl);
            case EAST:
                return (BlockState) state.with(EAST, bl);
            case WEST:
                return (BlockState) state.with(WEST, bl);
            case UP:
                return (BlockState) state.with(UP, bl);
            case DOWN:
                return (BlockState) state.with(DOWN, bl);

        }
        return (BlockState) state.with(NORTH, bl);
    }
}
