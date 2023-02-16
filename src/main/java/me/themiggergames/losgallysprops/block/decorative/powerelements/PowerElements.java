package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.ProgressionProperty;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PowerElements extends HorizontalFacingBlock implements BlockEntityProvider, StyledBlock {

    public static final Integer MAX_STYLE = 2;
    public static final IntProperty STYLES = IntProperty.of("style", 0, MAX_STYLE-1);
    public static final EnumProperty<ProgressionProperty> ISOPEN = EnumProperty.of("open", ProgressionProperty.class);
    public static final EnumProperty<ProgressionProperty> ISPOWERED = EnumProperty.of("ispowered", ProgressionProperty.class);
    public static final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.8f, 0.35f, 1, 0.1f, 0, 0.65f);

    public PowerElements(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(ISOPEN, ProgressionProperty.DISABLED).with(ISPOWERED, ProgressionProperty.DISABLED).with(STYLES, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(ISOPEN).add(ISPOWERED).add(STYLES);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        ctx.getWorld().createAndScheduleBlockTick(ctx.getBlockPos(), this, 20);
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(ISOPEN, ProgressionProperty.DISABLED).with(ISPOWERED, ProgressionProperty.DISABLED).with(STYLES, 0);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(ISPOWERED)==ProgressionProperty.BEING_ENABLED){
            world.setBlockState(pos, state.with(ISPOWERED, ProgressionProperty.ENABLED));
        }else if(state.get(ISPOWERED) == ProgressionProperty.BEING_DISABLED){
            world.setBlockState(pos, state.with(ISPOWERED, ProgressionProperty.DISABLED));
        }
        if(state.get(ISOPEN)==ProgressionProperty.BEING_ENABLED){
            world.setBlockState(pos, state.with(ISOPEN, ProgressionProperty.ENABLED));
        }else if(state.get(ISOPEN) == ProgressionProperty.BEING_DISABLED){
            world.setBlockState(pos, state.with(ISOPEN, ProgressionProperty.DISABLED));
        }
        if(!world.isClient && state.get(ISPOWERED) == ProgressionProperty.ENABLED)
            world.playSound(null, pos, ModSounds.ELECTRICITY_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        world.createAndScheduleBlockTick(pos, this, 10);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient){
            return ActionResult.SUCCESS;
        }
        if(player.getStackInHand(hand).getItem() == ModItems.CONFIGURATIOR && state.get(ISOPEN) == ProgressionProperty.ENABLED){
            if(state.get(ISPOWERED) == ProgressionProperty.ENABLED){
                world.createAndScheduleBlockTick(pos, this,10);
                world.setBlockState(pos, state.with(ISPOWERED, ProgressionProperty.BEING_DISABLED));
                world.playSound(null, pos, ModSounds.FUSE_BOX_CLICK_OFF_EVENT, SoundCategory.BLOCKS, 1f,1f);
            }else{
                world.createAndScheduleBlockTick(pos, this,10);
                world.setBlockState(pos, state.with(ISPOWERED, ProgressionProperty.BEING_ENABLED));
                world.playSound(null, pos, ModSounds.FUSE_BOX_CLICK_ON_EVENT, SoundCategory.BLOCKS, 1f,1f);
            }
        }else{
            if(state.get(ISOPEN) == ProgressionProperty.ENABLED){
                world.createAndScheduleBlockTick(pos, this,10);
                world.setBlockState(pos, state.with(ISOPEN, ProgressionProperty.BEING_DISABLED));
                world.playSound(null, pos, ModSounds.FUSE_BOX_DOOR_CLOSE_EVENT, SoundCategory.BLOCKS, 1f,1f);
            }else{
                world.createAndScheduleBlockTick(pos, this,10);
                world.setBlockState(pos, state.with(ISOPEN, ProgressionProperty.BEING_ENABLED));
                world.playSound(null, pos, ModSounds.FUSE_BOX_DOOR_OPEN_EVENT, SoundCategory.BLOCKS, 1f,1f);
            }
        }

        return ActionResult.SUCCESS;
    }
    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return (state.get(ISPOWERED) == ProgressionProperty.ENABLED || state.get(ISPOWERED) == ProgressionProperty.BEING_ENABLED) ? 15 : 0;
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        DebugLogger.sendMessage("Current Block Facing " + state.get(FACING));
        DebugLogger.sendMessage("Direction "+direction.asString());
        return (state.get(ISPOWERED) == ProgressionProperty.ENABLED || state.get(ISPOWERED) == ProgressionProperty.BEING_ENABLED) && state.get(FACING) == direction ? 15 : 0;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        DebugLogger.sendMessage("Block Updated");
        if((state.get(ISPOWERED) == ProgressionProperty.ENABLED || state.get(ISPOWERED) == ProgressionProperty.BEING_ENABLED)){
            DebugLogger.sendMessage("Block is powered");
            this.updateNeighbors(state,world,pos);
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(STYLES)==1){
            return VoxelShapes.fullCube();
        }
        return controller.getVoxelOutline(state.get(FACING));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PowerElementsEntity(pos,state);
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
        return false;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return null;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(state.get(FACING).getOpposite()), this);
    }
}
