package me.themiggergames.losgallysprops.block.decorative;

import me.themiggergames.losgallysprops.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PowerElements extends HorizontalFacingBlock {

    private static BooleanProperty ISOPEN = BooleanProperty.of("open");

    public PowerElements(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(ISOPEN, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(ISOPEN);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        ctx.getWorld().createAndScheduleBlockTick(ctx.getBlockPos(), this, 20);
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(ISOPEN, false);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.playSound(null, pos, ModSounds.ELECTRICITY_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        world.createAndScheduleBlockTick(pos, this, 20);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient){
            boolean temp = !state.get(ISOPEN);
            world.setBlockState(pos, state.with(ISOPEN, temp));
        }
        return ActionResult.PASS;
    }
}
