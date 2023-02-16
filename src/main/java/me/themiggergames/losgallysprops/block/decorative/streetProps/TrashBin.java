package me.themiggergames.losgallysprops.block.decorative.streetProps;

import me.themiggergames.losgallysprops.util.ProgressionProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrashBin extends HorizontalFacingBlock {

    public static IntProperty filledStatement = IntProperty.of("fill", 0, 6);

    public TrashBin(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(getDefaultState().with(filledStatement,0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(filledStatement).add(FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if(state.get(filledStatement) == 6){
            return ActionResult.PASS;
        }
        else if(stack.getCount()>1){
            stack.setCount(stack.getCount()-1);
            player.setStackInHand(hand, stack);
            if(state.get(filledStatement)!=6){
                world.setBlockState(pos, state.with(filledStatement, state.get(filledStatement)+1));
                world.createAndScheduleBlockTick(pos, this, 40);
            }
            return ActionResult.SUCCESS;
        } else if (stack.getCount()==1) {
            player.setStackInHand(hand, ItemStack.EMPTY);
            if(state.get(filledStatement)!=6){
                world.setBlockState(pos, state.with(filledStatement, state.get(filledStatement)+1));
                world.createAndScheduleBlockTick(pos, this, 40);
            }
            return ActionResult.SUCCESS;
        }else{
            return ActionResult.SUCCESS;
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(filledStatement,0).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(filledStatement)!=0){
            world.setBlockState(pos,state.with(filledStatement,state.get(filledStatement)-1));
            world.createAndScheduleBlockTick(pos, this, 40);
        }
    }
}
