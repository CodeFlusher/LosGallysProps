package me.themiggergames.losgallysprops.block.decorative.road;

import me.themiggergames.losgallysprops.block.decorative.streetProps.Bench;
import me.themiggergames.losgallysprops.util.BlockConnactable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class SpeedBump extends Block implements BlockConnactable {

    public SpeedBump(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockConnactable.appendConnectionProperties(builder, ConnectionTypes.HORISONTAL_ONLY);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Bench.combiner.getShape(state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        return getDefaultState().with(NORTH, canConnect(world, pos, Direction.NORTH))
                .with(SOUTH, canConnect(world, pos, Direction.SOUTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(WEST, canConnect(world, pos, Direction.WEST));
    }

    @Override
    public boolean canConnect(WorldAccess world, BlockPos pos, Direction dir) {
        return world.getBlockState(pos.offset(dir)).getBlock() instanceof SpeedBump;
    }

    @Override
    public boolean canConnect(World world, BlockPos pos, Direction dir) {
        return canConnect((WorldAccess) world, pos, dir);
    }
}
