package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.LosGallysPropsNetworking;
import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.ProgressionProperty;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.screenhandler.client.ClientNetworking;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.security.InvalidParameterException;

public class PowerElements extends HorizontalFacingBlock implements BlockEntityProvider, StyledBlock {

    public static final Integer MAX_STYLE = 2;
//    public static final IntProperty STYLES = IntProperty.of("style", 0, MAX_STYLE - 1);
//    public static final EnumProperty<ProgressionProperty> ISOPEN = EnumProperty.of("open", ProgressionProperty.class);
//    public static final EnumProperty<ProgressionProperty> ISPOWERED = EnumProperty.of("ispowered", ProgressionProperty.class);
    public static final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.8f, 0.35f, 1, 0.1f, 0, 0.65f);

    public final int tickCounter = 10;
    private int doorTick = 0;
    private int powerTick = 0;

    public PowerElements(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        ctx.getWorld().scheduleBlockTick(ctx.getBlockPos(), this, 20);
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    public static PowerElementsEntity getBlockEntity(World world, BlockPos pos){
        var entity = world.getBlockEntity(pos);
        if (entity == null) {
            InformativeLogger.error("Power Elements", "Couldn't get because because entity is null");
            InformativeLogger.error("Power Elements", new InvalidParameterException());
            return null;
        }
        return ((PowerElementsEntity)(entity));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            InformativeLogger.debugMessage("Power Elements", "Clicking on entity: + " + world.getBlockEntity(pos));
            return ActionResult.SUCCESS;
        }

        var powerElementsEntity = getBlockEntity(world, pos);
        if (powerElementsEntity == null){
            return ActionResult.CONSUME;
        }

        var doorState = powerElementsEntity.getDoorState();

//        InformativeLogger.debugMessage("Power Elements", powerElementsEntity);

        if (doorState.isDisabledOrBeingDisabled() || player.getStackInHand(hand).getItem() != ModItems.CONFIGURATIOR) {
            if (doorState.isEnabledOrBeingEnabled() ){
                world.playSound(null, pos, ModSounds.FUSE_BOX_DOOR_CLOSE_EVENT, SoundCategory.BLOCKS, 1f, 1f);
            }else{
                world.playSound(null, pos, ModSounds.FUSE_BOX_DOOR_OPEN_EVENT, SoundCategory.BLOCKS, 1f, 1f);
            }
            powerElementsEntity.setDoorState(doorState.switchProperty());
            return ActionResult.SUCCESS;
        }

        var powerState = powerElementsEntity.getPowerState();
        if (powerState.isEnabledOrBeingEnabled()){
            world.playSound(null, pos, ModSounds.FUSE_BOX_CLICK_OFF_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        }else{
            world.playSound(null, pos, ModSounds.FUSE_BOX_CLICK_ON_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        }
        powerElementsEntity.setPowerState(powerState.switchProperty());
        return ActionResult.SUCCESS;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        var entity = world.getBlockEntity(pos);
        if (entity == null) {
            InformativeLogger.error("Power Elements", "Couldn't change style because entity is null");
            InformativeLogger.error("Power Elements", new InvalidParameterException());
            this.createBlockEntity(pos,state);
            return 0;
        }
        return ((PowerElementsEntity)(entity)).getPowerState() == ProgressionProperty.ENABLED ? 15 : 0;
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        var entity = world.getBlockEntity(pos);
        if (entity == null) {
            InformativeLogger.error("Power Elements", "Couldn't change style because entity is null");
            InformativeLogger.error("Power Elements", new InvalidParameterException());
            this.createBlockEntity(pos,state);
            return 0;
        }
        return ((PowerElementsEntity)(entity)).getPowerState() == ProgressionProperty.ENABLED && state.get(FACING) == direction ? 15 : 0;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var entity = world.getBlockEntity(pos);
        if (entity == null) {
            return controller.getVoxelOutline(state.get(FACING));
        }
        Integer currentStyle = ((PowerElementsEntity)entity).getStyleNBT();
        if (currentStyle == null || currentStyle == 1) {
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
        return new PowerElementsEntity(pos, state);
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLE;
    }

    @Override
    public void changeStyle(int style, World world, BlockPos pos) {
        var entity = ((PowerElementsEntity) world.getBlockEntity(pos));
        if (entity == null) {
            InformativeLogger.error("Power Elements", "Couldn't change style because entity is null");
            InformativeLogger.error("Power Elements", new InvalidParameterException());
            this.createBlockEntity(pos,world.getBlockState(pos));
            return;
        }
        entity.setStyleNBT(style);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return ((world1, pos, state1, blockEntity) -> {
            var entity = world.getBlockEntity(pos);
            if (entity == null) {
//                this.createBlockEntity(pos,state);
                return;
            }
            var powerElementsEntity = ((PowerElementsEntity)entity);
            var doorState = powerElementsEntity.getDoorState();
            var powerState = powerElementsEntity.getPowerState();

//            InformativeLogger.debugMessage("Power Elements", "powerState = " + powerState);
//            InformativeLogger.debugMessage("Power Elements", "doorState = " + doorState);

            if (doorState.isChangingState()){
                if (doorTick >= tickCounter){
                    powerElementsEntity.setDoorState(doorState.finalizeProperty());
                    doorTick = 0;
                }else{
                    doorTick ++;
                }
            }

            if (powerState.isChangingState()){
                if (powerTick >= tickCounter){
                    powerElementsEntity.setPowerState(powerState.finalizeProperty());
                    powerTick = 0;
                }else {
                    powerTick++;
                }
            }
        });
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }



}
