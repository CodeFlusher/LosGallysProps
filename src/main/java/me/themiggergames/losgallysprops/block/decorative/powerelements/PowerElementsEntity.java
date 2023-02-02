package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
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
        if(event.getAnimatable().getCachedState().get(PowerElements.ISPOWERED)){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("switch_on", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("switch_off", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        }
        return PlayState.CONTINUE;
    }
    private PlayState openPredicate(AnimationEvent<PowerElementsEntity> event) {
        if(event.getAnimatable().getCachedState().get(PowerElements.ISOPEN)){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("door_open", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        }else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("door_close", ILoopType.EDefaultLoopTypes.HOLD_ON_LAST_FRAME));
        }
        return PlayState.CONTINUE;
    }
    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "switch_controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "door_controller", 0, this::openPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
