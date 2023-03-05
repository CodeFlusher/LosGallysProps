package me.themiggergames.losgallysprops.block.decorative.tunnel;

import me.themiggergames.losgallysprops.block.decorative.streetProps.VerticalSlab;
import me.themiggergames.losgallysprops.util.ModMath;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TunnelEnd extends HorizontalFacingBlock{

    public static final IntProperty LEFT_PROPERTY = IntProperty.of("left", 0, TunnelBlock.MAX_STYLE-1);
    public static final IntProperty RIGHT_PROPERTY = IntProperty.of("right", 0, TunnelBlock.MAX_STYLE-1);
    public static final BooleanProperty TOP_PROPERTY = BooleanProperty.of("up");
    public static final BooleanProperty BOTTOM_PROPERTY = BooleanProperty.of("down");

    public TunnelEnd(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LEFT_PROPERTY);
        builder.add(RIGHT_PROPERTY);
        builder.add(TOP_PROPERTY);
        builder.add(BOTTOM_PROPERTY);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Direction dir = ctx.getPlayerFacing().getOpposite();

        BlockState state = getDefaultState();
        BlockState leftBlock = world.getBlockState(pos.offset(ModMath.rotateDirection(dir, true)));
        BlockState rightBlock = world.getBlockState(pos.offset(ModMath.rotateDirection(dir, false)));
        BlockState topBlock = world.getBlockState(pos.offset(Direction.UP));
        BlockState bottomBlock = world.getBlockState(pos.offset(Direction.DOWN));
        return state.with(LEFT_PROPERTY, getNeighbourType(leftBlock))
                .with(RIGHT_PROPERTY, getNeighbourType(rightBlock))
                .with(TOP_PROPERTY, canConnect(topBlock))
                .with(BOTTOM_PROPERTY, canConnect(bottomBlock))
                .with(FACING, dir);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VerticalSlab.voxelShapeController.getVoxelOutline(state.get(FACING).getOpposite());
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos){

        Direction dir = state.get(FACING);
            BlockState leftBlock = world.getBlockState(pos.offset(ModMath.rotateDirection(dir, true)));
            BlockState rightBlock = world.getBlockState(pos.offset(ModMath.rotateDirection(dir, false)));
            BlockState topBlock = world.getBlockState(pos.offset(Direction.UP));
            BlockState bottomBlock = world.getBlockState(pos.offset(Direction.DOWN));
            return state.with(LEFT_PROPERTY, getNeighbourType(leftBlock))
                    .with(RIGHT_PROPERTY, getNeighbourType(rightBlock))
                    .with(TOP_PROPERTY, canConnect(topBlock))
                    .with(BOTTOM_PROPERTY, canConnect(bottomBlock));
        }

        public static Integer getNeighbourType(BlockState state){
            if (state.getBlock() instanceof TunnelEnd) {
                return 2;
            }
            if (!(state.getBlock() instanceof TunnelBlock)){
                return 0;
            }
            return state.get(TunnelBlock.STYLES);
        }

    public static boolean canConnect(BlockState block){
        return block.getBlock() instanceof TunnelEnd;
    }
}
