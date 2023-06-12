package me.themiggergames.losgallysprops.block.decorative.streetProps;

import me.themiggergames.losgallysprops.util.BlockConnactable;
import me.themiggergames.losgallysprops.util.DirectionalVoxelShapeCombiner;
import me.themiggergames.losgallysprops.util.ModMath;
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
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Bench extends HorizontalFacingBlock {

    public static final BooleanProperty leftConnection = BooleanProperty.of("left");
    public static final BooleanProperty rightConnection = BooleanProperty.of("right");
    public static final DirectionalVoxelShapeCombiner combiner = new DirectionalVoxelShapeCombiner(
            new SymmetricVoxelShapeController(0.6f, 0.2f, 0.5f, 0.2f, 0, 0f),
            BlockConnactable.ConnectionTypes.HORISONTAL_ONLY,
            VoxelShapes.cuboid(0.2f, 0f, 0.2f, 0.8f, 0.5f, 0.8f)
    );

    public Bench(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(leftConnection, false).with(rightConnection, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        ArrayList<Direction> directions = new ArrayList<>() {
            {
                if (state.get(leftConnection))
                    add(ModMath.rotateDirection(state.get(FACING), true));
                if (state.get(rightConnection))
                    add(ModMath.rotateDirection(state.get(FACING), false));
            }
        };
        return combiner.getShape(directions);
        //return VoxelShapes.cuboid(0.3f,0,0.3f,0.7f,0.7f,0.7f);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(rightConnection, canConnect(ctx.getBlockPos().offset(ModMath.rotateDirection(ctx.getPlayerFacing().getOpposite(), false)), ctx.getWorld(), getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite())))
                .with(leftConnection, canConnect(ctx.getBlockPos().offset(ModMath.rotateDirection(ctx.getPlayerFacing().getOpposite(), true)), ctx.getWorld(), getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite())));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(rightConnection, canConnect(pos.offset(ModMath.rotateDirection(state.get(FACING), false)), (World) world, state))
                .with(leftConnection, canConnect(pos.offset(ModMath.rotateDirection(state.get(FACING), true)), (World) world, state));
    }

    private Boolean canConnect(BlockPos pos, World world, BlockState selfState) {
        if (!(world.getBlockState(pos).getBlock() instanceof Bench)) {
            return false;
        }
        return world.getBlockState(pos).get(FACING) == selfState.get(FACING);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(leftConnection);
        builder.add(rightConnection);
        builder.add(FACING);
    }
}
