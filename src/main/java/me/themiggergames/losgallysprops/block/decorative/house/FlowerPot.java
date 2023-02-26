package me.themiggergames.losgallysprops.block.decorative.house;

import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class FlowerPot extends HorizontalFacingBlock implements BlockRotatable, StyledBlock {

    public static final Integer MAX_STYLE = 8;
    public static final IntProperty STYLES = IntProperty.of("style", 0, MAX_STYLE-1);
    public static final ArrayList<Text> TITLES = new ArrayList<>(){
        {
            for(int i =0; i<MAX_STYLE; i++)
                add(Text.translatable("ui.losgallysprops.styles.flower_pot."+i));
        }
    };

    public FlowerPot(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(STYLES,0).with(ROTATION, BlockRotatable.getRotation(ctx.getPlayerYaw())).with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw()));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Item itemInHand = player.getStackInHand(hand).getItem();

        if (itemInHand == ModItems.STYLE_EDITOR){
            return ActionResult.CONSUME;
        }

        if (player.getStackInHand(hand) == ItemStack.EMPTY || itemInHand == ModItems.CONFIGURATIOR) {
            world.setBlockState(pos, state.with(STYLES, 0));
            return ActionResult.SUCCESS;
        }

        if(!(Block.getBlockFromItem(itemInHand) instanceof SaplingBlock))
            return ActionResult.SUCCESS;

        if (itemInHand == Items.ACACIA_SAPLING){
            world.setBlockState(pos, state.with(STYLES, 1));
        }else if(itemInHand == Items.BIRCH_SAPLING){
            world.setBlockState(pos, state.with(STYLES,2));
        }else if(itemInHand == Items.DARK_OAK_SAPLING){
            world.setBlockState(pos, state.with(STYLES,3));
        }else if(itemInHand == Items.JUNGLE_SAPLING){
            world.setBlockState(pos, state.with(STYLES,4));
        }else if(itemInHand == Items.OAK_SAPLING){
            world.setBlockState(pos, state.with(STYLES,5));
        }else if(itemInHand == Items.SPRUCE_SAPLING){
            world.setBlockState(pos, state.with(STYLES,6));
        } else {
            world.setBlockState(pos, state.with(STYLES, 7));
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STYLES);
        BlockRotatable.appendRotationProperties(builder);
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
        return TITLES;
    }
}
