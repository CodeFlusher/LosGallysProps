package me.themiggergames.losgallysprops.block.decorative.tunnel;

import me.themiggergames.losgallysprops.block.decorative.streetProps.VerticalSlab;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class TunnelBlock extends HorizontalFacingBlock implements StyledBlock {
    public static final Integer MAX_STYLE = 3;
    public static final BooleanProperty STATE_LOCK = BooleanProperty.of("state_lock");
    public static final BooleanProperty IS_CEILING = BooleanProperty.of("is_ceiling");
    public static final IntProperty STYLES = IntProperty.of("tunnel_type", 0, MAX_STYLE-1);
    private static final ArrayList<Text> titles = new ArrayList<>(){
        {
            for(int i = 0; i<MAX_STYLE; i++)
                add(Text.translatable("ui.losgallysprops.styles.tunnel_block.style."+i));
        }
    };
    public TunnelBlock(Settings settings) {
        super(settings.nonOpaque());
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = getDefaultState();
        state = state.with(FACING, ctx.getPlayerFacing().getOpposite()).with(STATE_LOCK, false);
        state = state.with(STYLES, getStyle(ctx.getWorld(), ctx.getBlockPos())).with(IS_CEILING, isCeiling(ctx.getWorld(), ctx.getBlockPos()));
        return state;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(STATE_LOCK))
            return state;
        state = state.with(STYLES, getStyle((World) world, pos)).with(IS_CEILING, isCeiling((World) world, pos));
        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(IS_CEILING)){
            return VoxelShapes.cuboid(0,0.5f,0, 1, 1, 1);
        }
        return VerticalSlab.voxelShapeController.getVoxelOutline(state.get(FACING).getOpposite());
    }

    protected int getStyle(World world, BlockPos pos){
        BlockState stateUp = world.getBlockState(pos.offset(Direction.UP));
        BlockState stateDown = world.getBlockState(pos.offset(Direction.DOWN));
        if(!(stateUp.getBlock() instanceof TunnelBlock) || !(stateDown.getBlock() instanceof TunnelBlock)){
            return 0;
        }
        int minimumOfTwoStates = Math.min(stateUp.get(STYLES), stateDown.get(STYLES));
        return minimumOfTwoStates == 2 ? 2 : minimumOfTwoStates+1;
    }

    protected boolean isCeiling(World world, BlockPos pos){
        return world.getBlockState(pos.offset(Direction.DOWN)).getBlock() instanceof AirBlock;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(STATE_LOCK);
        builder.add(IS_CEILING);
        builder.add(STYLES);
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLE;
    }

    @Override
    public IntProperty getIntProperty() {
        return STYLES;
    }

    @Override
    public boolean usuesUnstandartTiteling() {
        return true;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return titles;
    }
}
