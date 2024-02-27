package me.themiggergames.losgallysprops.debugtools;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DebugBlock extends HorizontalFacingBlock implements BlockRotatable {
    public DebugBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        if (LosGallysProps.isDebugEnabled())
            placedBy.sendMessage(Text.of(blockState.toString() + " " + blockPos.toString() + " " + placedBy.getHeadYaw()), true);
        if (!world.isClient)
            world.playSound(null, blockPos, ModSounds.LORE_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockRotatable.appendRotationProperties(builder);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw())).with(ROTATION, BlockRotatable.getRotation(ctx.getPlayerYaw()));
    }
}
