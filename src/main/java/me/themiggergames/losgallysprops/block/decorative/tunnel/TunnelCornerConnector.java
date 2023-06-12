package me.themiggergames.losgallysprops.block.decorative.tunnel;

import me.themiggergames.losgallysprops.util.BlockConnactable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TunnelCornerConnector extends Block implements BlockConnactable {
    public TunnelCornerConnector(Settings settings) {
        super(settings.nonOpaque());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockConnactable.appendConnectionProperties(builder, ConnectionTypes.EVERYTHING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = getDefaultState();
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        return state.with(NORTH, canConnect(world, pos, Direction.NORTH))
                .with(SOUTH, canConnect(world, pos, Direction.SOUTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN))
                .with(WEST, canConnect(world, pos, Direction.WEST));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(NORTH, canConnect(world, pos, Direction.NORTH))
                .with(SOUTH, canConnect(world, pos, Direction.SOUTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN))
                .with(WEST, canConnect(world, pos, Direction.WEST));
    }

    @Override
    public boolean canConnect(WorldAccess world, BlockPos pos, Direction dir) {
        return world.getBlockState(pos.offset(dir)).getBlock() instanceof TunnelBlock;
    }

    @Override
    public boolean canConnect(World world, BlockPos pos, Direction dir) {
        return world.getBlockState(pos.offset(dir)).getBlock() instanceof TunnelBlock;
    }
}
