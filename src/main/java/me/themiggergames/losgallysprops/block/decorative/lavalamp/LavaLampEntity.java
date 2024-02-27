package me.themiggergames.losgallysprops.block.decorative.lavalamp;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
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

public class LavaLampEntity extends BlockEntity implements GeoBlockEntity {
    protected static final RawAnimation DEPLOY = RawAnimation.begin().thenLoop("misc.idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public LavaLampEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CCTV_ENTITY, pos, state);
    }

    private <E extends BlockEntity & GeoAnimatable> PlayState deployAnimController(final AnimationState<E> state) {

        return state.setAndContinue(DEPLOY);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>
                (this, this::deployAnimController));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

}
