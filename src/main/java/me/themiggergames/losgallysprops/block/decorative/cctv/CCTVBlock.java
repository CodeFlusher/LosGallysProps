package me.themiggergames.losgallysprops.block.decorative.cctv;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.PlacingTypes;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CCTVBlock extends HorizontalFacingBlock implements BlockEntityProvider, StyledBlock {

    public static final Integer MAX_STYLE_COUNT = 2;
    public static final EnumProperty<PlacingTypes> TYPES = EnumProperty.of("placedon",PlacingTypes.class);
    public static final IntProperty STYLE = IntProperty.of("style", 0, MAX_STYLE_COUNT-1);
    public final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.4f,0.6f, 0.4f,0.3f, 0.3f, 0f);
    private Integer styleType;

    public CCTVBlock(Settings settings) {
        super(settings.nonOpaque());
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
        setDefaultState(getDefaultState().with(STYLE,0));
        this.styleType = 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STYLE);
        builder.add(TYPES);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        if(ctx.getSide() != Direction.UP && ctx.getSide() != Direction.DOWN) {
            return getDefaultState().with(STYLE, styleType).with(FACING, ctx.getSide().getOpposite()).with(TYPES, PlacingTypes.WALL);
        }else{
            return getDefaultState().with(STYLE, styleType).with(FACING, ctx.getPlayerFacing()).with(TYPES,PlacingTypes.getPlacement(ctx.getSide().getOpposite()));
        }
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(TYPES) == PlacingTypes.WALL)
            return controller.getVoxelOutline(state.get(FACING));
        else if (state.get(TYPES) == PlacingTypes.TOP) {
            return controller.getVoxelOutline(Direction.UP);
        }else{
            return controller.getVoxelOutline(Direction.DOWN);
        }
    }


    @Override
    public String toString() {
        return "Int Property " + getIntProperty().toString() + " Placing Property " + TYPES.toString() + " Max Style " + MAX_STYLE_COUNT;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CCTVEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLE_COUNT;
    }

    @Override
    public IntProperty getIntProperty() {
        return STYLE;
    }

    @Override
    public boolean usuesUnstandartTiteling() {
        return false;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return null;
    }
}
