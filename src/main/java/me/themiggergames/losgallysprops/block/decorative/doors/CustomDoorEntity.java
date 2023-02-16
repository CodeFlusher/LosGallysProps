package me.themiggergames.losgallysprops.block.decorative.doors;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.block.decorative.powerelements.PowerElements;
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

public class CustomDoorEntity extends BlockEntity implements IAnimatable {
    AnimationFactory factory = new AnimationFactory(this);

    public CustomDoorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DOOR_ENTITY, pos, state);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<CustomDoorEntity>(
                this, "door_controller", 0, this::predicate
        ));
    }

    private PlayState predicate(AnimationEvent<CustomDoorEntity> event){
        BlockState state = event.getAnimatable().getWorld().getBlockState(event.getAnimatable().getPos());
        Block block = state.getBlock();
        if(!(block instanceof PowerElements))
            return PlayState.CONTINUE;
        switch (state.get(PowerElements.ISPOWERED)){
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
                break;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
