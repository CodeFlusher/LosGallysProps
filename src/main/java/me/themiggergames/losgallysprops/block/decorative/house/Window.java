package me.themiggergames.losgallysprops.block.decorative.house;

import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class Window extends HorizontalFacingBlock {
    public static final BooleanProperty TOP_STATE = BooleanProperty.of("top");
    public static final BooleanProperty BOTTOM_STATE = BooleanProperty.of("bottom");
    public static final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(1, 0.125f, 1, 0, 0, 0.4375f);

    public Window(Settings settings) {
        super(settings.nonOpaque());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TOP_STATE).add(BOTTOM_STATE).add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return controller.getVoxelOutline(state.get(FACING));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction != Direction.DOWN && direction != Direction.UP) {
            return state;
        }
        boolean valid;
        if (neighborState.getBlock() instanceof Window) {
            valid = (neighborState.get(FACING) == state.get(FACING));
        } else {
            valid = false;
        }
        return state.with(direction == Direction.UP ? TOP_STATE : BOTTOM_STATE, valid);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())
                .with(TOP_STATE, world.getBlockState(pos.offset(Direction.UP)).getBlock() instanceof Window && world.getBlockState(pos.offset(Direction.UP)).get(FACING) == ctx.getHorizontalPlayerFacing().getOpposite())
                .with(BOTTOM_STATE, world.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof Window && world.getBlockState(pos.offset(Direction.DOWN)).get(FACING) == ctx.getHorizontalPlayerFacing().getOpposite());
    }
}
