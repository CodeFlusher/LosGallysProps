package me.themiggergames.losgallysprops.block.decorative.streetProps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrashBin extends HorizontalFacingBlock {

    public static final int MAX_FILLING = 12;
    public static final IntProperty filledStatement = IntProperty.of("fill", 0, MAX_FILLING);

    public TrashBin(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(getDefaultState().with(filledStatement, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(filledStatement).add(FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.isCreative())
            return super.onUse(state, world, pos, player, hand, hit);

        if (state.get(filledStatement) == MAX_FILLING) {
            return super.onUse(state, world, pos, player, hand, hit);
        }
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getCount() >= 1) {
            if (stack.getCount() == 1)
                player.setStackInHand(hand, ItemStack.EMPTY);
            else {
                stack.setCount(stack.getCount() - 1);
                player.setStackInHand(hand, stack);
            }
            world.setBlockState(pos, state.with(filledStatement, state.get(filledStatement) + 1));
            world.scheduleBlockTick(pos, this, 40);
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(filledStatement, 0).with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(filledStatement) != 0) {
            world.setBlockState(pos, state.with(filledStatement, state.get(filledStatement) - 1));
            world.scheduleBlockTick(pos, this, 40);
        }
    }
}
