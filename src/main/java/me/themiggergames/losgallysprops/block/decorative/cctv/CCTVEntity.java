package me.themiggergames.losgallysprops.block.decorative.cctv;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.util.BlockEntityWithStyles;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
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

public class CCTVEntity extends BlockEntity implements GeoBlockEntity, BlockEntityWithStyles {
    protected static final RawAnimation DEPLOY = RawAnimation.begin().thenLoop("misc.idle");
    protected static final String NBT_STYLE_IDENTIFIER = "block_style";
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public CCTVEntity(BlockPos pos, BlockState state) {
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

    @Override
    public void setStyleNBT(int newStyle) {
        var nbt = getNbt();
        nbt.putInt(NBT_STYLE_IDENTIFIER, newStyle);
        this.writeNbt(nbt);
    }

    @Override
    public void onChangedNbt() {

    }

    public final NbtCompound getNbt(){
        var nbt = this.createNbt();
        this.readNbt(nbt);
        return nbt;
    }

    @Override
    public Integer getStyleNBT() {
        return getNbt().getInt(NBT_STYLE_IDENTIFIER);
    }
}
