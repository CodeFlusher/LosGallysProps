package me.themiggergames.losgallysprops.block.decorative.roof;

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
import org.jetbrains.annotations.Nullable;

public class RoofTopBlock extends Block implements BlockConnactable {

    public RoofTopBlock(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(this.getStateManager().getDefaultState().with(NORTH, false)
                .with(WEST, false)
                .with(SOUTH, false)
                .with(EAST, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockConnactable.appendConnectionProperties(builder, ConnectionTypes.HORISONTAL_ONLY);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState1 = world.getBlockState(blockPos.north());
        BlockState blockState2 = world.getBlockState(blockPos.east());
        BlockState blockState3 = world.getBlockState(blockPos.south());
        BlockState blockState4 = world.getBlockState(blockPos.west());
        boolean north_con = blockState1.getBlock() instanceof RoofTopBlock;
        boolean east_con = blockState2.getBlock() instanceof RoofTopBlock;
        boolean south_con = blockState3.getBlock() instanceof RoofTopBlock;
        boolean west_con = blockState4.getBlock() instanceof RoofTopBlock;

        return getDefaultState().with(NORTH, north_con)
                .with(EAST, east_con)
                .with(SOUTH, south_con)
                .with(WEST, west_con);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState blockState1 = world.getBlockState(pos.north());
        BlockState blockState2 = world.getBlockState(pos.east());
        BlockState blockState3 = world.getBlockState(pos.south());
        BlockState blockState4 = world.getBlockState(pos.west());
        boolean north_con = blockState1.getBlock() instanceof RoofTopBlock;
        boolean east_con = blockState2.getBlock() instanceof RoofTopBlock;
        boolean south_con = blockState3.getBlock() instanceof RoofTopBlock;
        boolean west_con = blockState4.getBlock() instanceof RoofTopBlock;

        return state.with(NORTH, north_con)
                .with(EAST, east_con)
                .with(SOUTH, south_con)
                .with(WEST, west_con);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0, 0, 0, 1, 0.5, 1);
    }

    @Override
    public boolean canConnect(WorldAccess world, BlockPos pos, Direction dir) {
        return false;
    }

    @Override
    public boolean canConnect(World world, BlockPos pos, Direction dir) {
        return false;
    }
}
