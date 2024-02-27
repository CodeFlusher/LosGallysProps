package me.themiggergames.losgallysprops.block.decorative.house;

import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ComputerMonitor extends HorizontalFacingBlock implements StyledBlock {

    public static final Integer MAX_STYLE_COUNT = 12;
    public static final IntProperty STYLES = IntProperty.of("styles", 0, MAX_STYLE_COUNT - 1);

    private static final ArrayList<Text> titles = new ArrayList<>() {
        {
            for (int i = 0; i <= MAX_STYLE_COUNT - 1; i++)
                add(Text.translatable("ui.losgallysprops.styles.monitor." + i));
        }
    };

    public ComputerMonitor(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(STYLES, 0);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2f, 0, 0.2f, 0.8f, 1f, 0.8f);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STYLES).add(FACING);
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLE_COUNT;
    }

    @Override
    public void changeStyle(int style, World world, BlockPos pos) {
        var state = world.getBlockState(pos);
        world.setBlockState(pos,state.with(STYLES, style));
    }

    @Override
    public boolean substandardTitling() {
        return true;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return titles;
    }
}
