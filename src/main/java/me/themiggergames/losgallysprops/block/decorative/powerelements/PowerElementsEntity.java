package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.LosGallysPropsNetworking;
import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.util.BlockEntityWithStyles;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.ProgressionProperty;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PowerElementsEntity extends BlockEntity implements GeoBlockEntity, BlockEntityWithStyles {
    protected static final RawAnimation PP_IDLE = RawAnimation.begin().thenLoop("power_pointer_idle");
    protected static final RawAnimation PP_OFF = RawAnimation.begin().thenLoop("power_pointer_off");
    protected static final RawAnimation PP_ON = RawAnimation.begin().thenLoop("power_pointer_on");
    protected static final RawAnimation IDLE_ON = RawAnimation.begin().thenLoop("idle_on");
    protected static final RawAnimation IDLE_OFF = RawAnimation.begin().thenLoop("idle_off");
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    protected static final RawAnimation DOOR_OPEN = RawAnimation.begin().thenLoop("door_open");
    protected static final RawAnimation DOOR_CLOSE = RawAnimation.begin().thenLoop("door_close");
    protected static final RawAnimation DOOR_OPENED = RawAnimation.begin().thenLoop("door_opened");
    protected static final RawAnimation SWITCH_ON = RawAnimation.begin().thenLoop("switch_on");
    protected static final RawAnimation SWITCH_OFF = RawAnimation.begin().thenLoop("switch_off");
    protected static final RawAnimation SWITCH_ENABLED = RawAnimation.begin().thenLoop("switch_enabled");
    public static final String NBT_STYLE_IDENTIFIER = "block_style";
    private Integer CURRENT_STYLE = null;
    private ProgressionProperty POWER_STATE = null;
    private ProgressionProperty DOOR_STATE = null;
    public static final String NBT_DOOR_STATE_IDENTIFIER = "block_door_state";
    public static final String NBT_POWER_STATE_IDENTIFIER = "block_power_state";
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public PowerElementsEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_ELEMENTS_ENTITY, pos, state);
    }

    private <E extends BlockEntity & GeoAnimatable> PlayState idleAnimController(final AnimationState<E> event) {
        var state = ((PowerElementsEntity)event.getAnimatable()).getPowerState();
        PlayState state1 = PlayState.CONTINUE;
        switch (state) {
            case ENABLED -> state1 = event.setAndContinue(IDLE);
            case BEING_ENABLED -> state1 = event.setAndContinue(IDLE_ON);
            case BEING_DISABLED -> event.setAnimation(IDLE_OFF);
            case DISABLED -> {
                return PlayState.STOP;
            }
        }
        return state1;

    }

    private <E extends BlockEntity & GeoAnimatable> PlayState doorAnimationController(final AnimationState<E> event) {
//        InformativeLogger.debugMessage("Power Elements Door Anim Controller",getBlockEntityByAnimationState(event).DOOR_STATE);
//        InformativeLogger.debugMessage("Power Elements Door Anim Controller", this.toString());
//        InformativeLogger.debugMessage("Power Elements Door Anim Controller", event.);
        var state = getBlockEntityByAnimationState(event).getDoorState();
        var animatable = event.getAnimatable();
        var world = animatable.getWorld();
        var pos = animatable.getPos();
        if (world == null)
            return PlayState.CONTINUE;
        PlayState state1 = PlayState.CONTINUE;

        event.setControllerSpeed(0.1f);
        switch (state) {
            case BEING_DISABLED -> event.setAnimation(DOOR_CLOSE);
            case BEING_ENABLED -> event.setAnimation(DOOR_OPEN);
            case ENABLED -> {
                return event.setAndContinue(DOOR_OPENED);
            }
            case DISABLED -> {
                return PlayState.STOP;
            }
        }
        return state1;
    }

    private <E extends BlockEntity & GeoAnimatable> PlayState switchAnimationController(final AnimationState<E> event) {
        var state = ((PowerElementsEntity)event.getAnimatable()).getPowerState();
        PlayState state1 = PlayState.CONTINUE;
        switch (state) {
            case BEING_DISABLED -> event.setAnimation(SWITCH_OFF);
            case BEING_ENABLED -> event.setAnimation(SWITCH_ON);
            case ENABLED -> {
                return event.setAndContinue(SWITCH_ENABLED);
            }
            case DISABLED -> {
                return PlayState.STOP;
            }
        }
        return state1;
    }

    private <E extends BlockEntity & GeoAnimatable> PlayState powerPointerAnimController(final AnimationState<E> event) {
        var state = ((PowerElementsEntity)event.getAnimatable()).getPowerState();
        PlayState state1 = PlayState.CONTINUE;
        switch (state) {
            case ENABLED -> state1 = event.setAndContinue(PP_IDLE);
            case BEING_ENABLED -> state1 = event.setAndContinue(PP_ON);
            case DISABLED -> {
                return PlayState.STOP;
            }
            default -> event.setAnimation(PP_OFF);
        }
        return state1;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "idle_anim_controller", this::idleAnimController),
//                new AnimationController<>(this, "door_anim_controller", this::doorAnimationController),
                new AnimationController<>(this, "door_anim_controller", 2, state -> {
                    switch (this.getPowerState()) {
                        case BEING_DISABLED -> state.setAnimation(DOOR_CLOSE);
                        case BEING_ENABLED -> state.setAnimation(DOOR_OPEN);
                        case ENABLED -> {
                            return state.setAndContinue(DOOR_OPENED);
                        }
                        case DISABLED -> {
                            return PlayState.STOP;
                        }
                    }
                    return PlayState.STOP;
                }),
                new AnimationController<>(this, "power_pointer_anim_controller", this::powerPointerAnimController),
                new AnimationController<>(this, "switch_anim_controller", this::switchAnimationController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void setStyleNBT(int newStyle) {
        this.CURRENT_STYLE = newStyle;
        onChangedNbt();
    }

    @Override
    public void onChangedNbt() {
        if (world == null){
            return;
        }
        if (world.isClient){
            return;
        }
        try {
            var buf = PacketByteBufs.create();
            buf.writeBlockPos(pos);
            var entity = world.getBlockEntity(pos);
            InformativeLogger.debugMessage("Power Elements onChangedNbt",entity);
            if (entity == null){
                InformativeLogger.debugMessage("Power Elements onChangedNbt", "The block entity is null");
                return;
            }
            var nbt = entity.createNbt();
            InformativeLogger.debugMessage("Power Elements onChangedNbt", nbt.asString());
            buf.writeString(nbt.asString());
            for (PlayerEntity player : world.getPlayers()) {
                ServerPlayNetworking.send((ServerPlayerEntity) player, LosGallysPropsNetworking.RECIEVE_NBT_DATA_FROM_SERVER, buf);
            }
        }
        catch (Exception e){
            InformativeLogger.error("Power Elements onChangedNbt","Failed to write and send data to client");
            InformativeLogger.error("Power Elements onChangedNbt", e);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        if (!nbt.contains(NBT_POWER_STATE_IDENTIFIER))
            POWER_STATE = ProgressionProperty.DISABLED;
        else
            POWER_STATE = ProgressionProperty.parseProperty(nbt.getString(NBT_POWER_STATE_IDENTIFIER));

        if (!nbt.contains(NBT_STYLE_IDENTIFIER))
            CURRENT_STYLE = 0;
        else
            CURRENT_STYLE = nbt.getInt(NBT_STYLE_IDENTIFIER);

        if (!nbt.contains(NBT_DOOR_STATE_IDENTIFIER))
            DOOR_STATE = ProgressionProperty.DISABLED;
        else
            DOOR_STATE = ProgressionProperty.parseProperty(nbt.getString(NBT_DOOR_STATE_IDENTIFIER));

        onChangedNbt();
    }

    public final NbtCompound getNbt(){
        var nbt = this.createNbt();
        writeNbt(nbt);
        return nbt;
    }

    @Override
    public Integer getStyleNBT() {
        if (CURRENT_STYLE == null)
            return 0;
        return CURRENT_STYLE;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        if (this.CURRENT_STYLE != null)
            nbt.putInt(NBT_STYLE_IDENTIFIER, CURRENT_STYLE);
        if (this.POWER_STATE != null)
            nbt.putString(NBT_POWER_STATE_IDENTIFIER, POWER_STATE.asString());
        if (this.DOOR_STATE != null)
            nbt.putString(NBT_DOOR_STATE_IDENTIFIER, DOOR_STATE.asString());
        super.writeNbt(nbt);
    }

    public ProgressionProperty getDoorState() {
        if (DOOR_STATE == null)
            return ProgressionProperty.DISABLED;
        return DOOR_STATE;
    }
    public ProgressionProperty getPowerState() {
        if (POWER_STATE == null)
            return ProgressionProperty.DISABLED;
        return POWER_STATE;
    }
    public void setDoorState(ProgressionProperty property) {
        this.DOOR_STATE = property;
        onChangedNbt();
    }
    public void setPowerState(ProgressionProperty property) {
        this.POWER_STATE = property;
        onChangedNbt();
    }

    @Override
    public String toString() {
        return "PowerElementsEntity{" +
                "CURRENT_STYLE=" + CURRENT_STYLE +
                ", POWER_STATE=" + POWER_STATE +
                ", DOOR_STATE=" + DOOR_STATE +
                ", cache=" + cache +
                ", world=" + world +
                ", pos=" + pos +
                ", removed=" + removed +
                ", super=" + super.toString() +
                '}';
    }

    public static <E extends BlockEntity & GeoAnimatable> PowerElementsEntity getBlockEntityByAnimationState(final AnimationState<E> event){
        return (PowerElementsEntity) event.getAnimatable();
    }
}
