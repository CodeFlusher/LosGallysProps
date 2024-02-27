package me.themiggergames.losgallysprops.block.decorative.road;

import me.themiggergames.losgallysprops.util.BlockConnactable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class RoadSignNotRotatable extends RoadSign {
    public RoadSignNotRotatable(Settings settings) {
        super(settings.nonOpaque().noCollision());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(NORTH, canConnect(ctx.getWorld(), blockPos, Direction.NORTH))
                .with(SOUTH, canConnect(ctx.getWorld(), blockPos, Direction.SOUTH))
                .with(EAST, canConnect(ctx.getWorld(), blockPos, Direction.EAST))
                .with(WEST, canConnect(ctx.getWorld(), blockPos, Direction.WEST))
                .with(UP, canConnect(ctx.getWorld(), blockPos, Direction.UP))
                .with(DOWN, canConnect(ctx.getWorld(), blockPos, Direction.DOWN));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockConnactable.appendConnectionProperties(builder,ConnectionTypes.EVERYTHING);
        builder.add(WATERLOGGED);
        builder.add(FACING);
    }
}
