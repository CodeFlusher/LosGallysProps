package me.themiggergames.losgallysprops.block.test;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.block.BlockRotatable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DebugBlock extends BlockRotatable {
    public DebugBlock(Settings settings, boolean useAdditionalStates) {
        super(settings, useAdditionalStates);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        if(LosGallysProps.isDebugEnabled())
            placedBy.sendMessage(Text.of(blockState.toString() + " " + blockPos.toString() + " " + placedBy.getHeadYaw()), true);
        if(!world.isClient)
            world.playSound(null, blockPos, ModSounds.LORE_SOUND_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        return ActionResult.SUCCESS;
    }
}
