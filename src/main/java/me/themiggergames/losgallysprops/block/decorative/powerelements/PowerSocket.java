package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class PowerSocket extends HorizontalFacingBlock {

    SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.3f, 0.2f, 0.3f,0.35f, 0.35f, 0.8f);
    public PowerSocket(Settings settings) {
        super(settings.nonOpaque());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return controller.getVoxelOutline(state.get(FACING));
    }
}
