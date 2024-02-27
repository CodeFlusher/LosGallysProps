package me.themiggergames.losgallysprops.block.decorative.lighting;

import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

public class WallLightBlock extends HorizontalFacingBlock implements StyledBlock {

    public static final Class<?>[] BREAKING_WITH = {SnowballEntity.class, EggEntity.class, ArrowEntity.class};
    public static final ArrayList<Text> TITLES = new ArrayList<>() {
        {
            add(Text.translatable("ui.losgallysprops.styles.light.normal"));
            add(Text.translatable("ui.losgallysprops.styles.light.broken"));
            add(Text.translatable("ui.losgallysprops.styles.light.disabled"));
        }
    };
    public static final Integer MAX_STYLES = 3;
    public static final IntProperty STYLES = IntProperty.of("state", 0, MAX_STYLES - 1);

    protected WallLightBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    public static boolean canThisEntityBreakLight(Entity entityBreakLight) {
        if (entityBreakLight == null)
            return false;
        return Arrays.stream(BREAKING_WITH).anyMatch(clazz -> clazz.getName().equals(entityBreakLight.getClass().getName()));
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLES;
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
        return TITLES;
    }
}
