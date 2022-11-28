package me.themiggergames.losgallysprops.block.decorative.streetProps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BioToilet extends HorizontalFacingBlock {
    public static final BooleanProperty ISOPEN = BooleanProperty.of("open");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(ISOPEN).add(Properties.HORIZONTAL_FACING);
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerLookDirection().getOpposite()).with(ISOPEN, false);
    }

    public BioToilet(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState()
                .with(ISOPEN, false));
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            if(blockState.get(ISOPEN)) {
                getDefaultState().with(ISOPEN, false);
            }
            else {
                getDefaultState().with(ISOPEN, true);
            }
        }
        return ActionResult.SUCCESS;
    }
}
