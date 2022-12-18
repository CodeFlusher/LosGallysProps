package me.themiggergames.losgallysprops.block.decorative;

import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class DecorPanel extends HorizontalFacingBlock {

    private SymmetricVoxelShapeController controller;

    public DecorPanel(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)){
            case NORTH:
                return VoxelShapes.cuboid(0f, 0f, 0.4f, 1f, 1f, 0.6f);
            case SOUTH:
                return VoxelShapes.cuboid(0f, 0f, 0.4f, 1f, 1f, 0.6f);
            case WEST:
                return VoxelShapes.cuboid(0.4f, 0f, 0f, 0.6f, 1f, 1f);
            case EAST:
                return VoxelShapes.cuboid(0.4f, 0f, 0f, 0.6f, 1f, 1f);
            default:
                return VoxelShapes.cuboid(0.4f, 0f, 0f, 0.6f, 1f, 1f);
        }
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
}
