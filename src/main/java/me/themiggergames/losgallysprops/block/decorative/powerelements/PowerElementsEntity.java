package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PowerElementsEntity extends BlockEntity implements IAnimatable {
    AnimationFactory factory = new AnimationFactory(this);

    public PowerElementsEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.POWER_ELEMENTS_ENTITY, pos, state);
    }

    private PlayState predicate(AnimationEvent<PowerElementsEntity> event) {
        BlockState state = event.getAnimatable().getWorld().getBlockState(event.getAnimatable().getPos());
        Block block = state.getBlock();
        if (!(block instanceof PowerElements))
            return PlayState.CONTINUE;
        switch (state.get(PowerElements.ISPOWERED)) {
            case BEING_DISABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("switch_off", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            case ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("switch_enabled", ILoopType.EDefaultLoopTypes.LOOP));
                break;
            case BEING_ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("switch_on", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            default:
                event.getController().setAnimation(new AnimationBuilder());
                break;
        }
        return PlayState.CONTINUE;
    }

    private PlayState openPredicate(AnimationEvent<PowerElementsEntity> event) {
        BlockState state = event.getAnimatable().getWorld().getBlockState(event.getAnimatable().getPos());
        Block block = state.getBlock();
        if (!(block instanceof PowerElements))
            return PlayState.CONTINUE;
        switch (state.get(PowerElements.ISOPEN)) {
            case BEING_DISABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("door_close", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            case ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("door_opened", ILoopType.EDefaultLoopTypes.LOOP));
                break;
            case BEING_ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("door_open", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            default:
                event.getController().setAnimation(new AnimationBuilder());
        }
        return PlayState.CONTINUE;
    }

    private PlayState powerPredicate(AnimationEvent<PowerElementsEntity> event) {
        BlockState state = event.getAnimatable().getWorld().getBlockState(event.getAnimatable().getPos());
        Block block = state.getBlock();
        if (!(block instanceof PowerElements))
            return PlayState.CONTINUE;
        switch (state.get(PowerElements.ISPOWERED)) {
            case BEING_DISABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("power_pointer_off", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            case ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("power_pointer_idle", ILoopType.EDefaultLoopTypes.LOOP));
                break;
            case BEING_ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("power_pointer_on", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            default:
                event.getController().setAnimation(new AnimationBuilder());
                break;
        }
        return PlayState.CONTINUE;
    }

    private PlayState idlePredicate(AnimationEvent<PowerElementsEntity> event) {
        BlockState state = event.getAnimatable().getWorld().getBlockState(event.getAnimatable().getPos());
        Block block = state.getBlock();
        if (!(block instanceof PowerElements))
            return PlayState.CONTINUE;
        switch (state.get(PowerElements.ISPOWERED)) {
            case BEING_DISABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_off", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            case ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", ILoopType.EDefaultLoopTypes.LOOP));
                break;
            case BEING_ENABLED:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("idle_on", ILoopType.EDefaultLoopTypes.PLAY_ONCE));
                break;
            default:
                event.getController().setAnimation(new AnimationBuilder());
                break;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "switch_controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "door_controller", 0, this::openPredicate));
        animationData.addAnimationController(new AnimationController(this, "power_pointer_controller", 0, this::powerPredicate));
        animationData.addAnimationController(new AnimationController(this, "idle_controller", 0, this::idlePredicate));
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
