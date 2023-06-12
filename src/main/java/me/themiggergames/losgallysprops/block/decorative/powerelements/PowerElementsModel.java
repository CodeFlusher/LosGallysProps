package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PowerElementsModel extends AnimatedGeoModel<PowerElementsEntity> {

    @Override
    public Identifier getModelResource(PowerElementsEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "geo/fuse_box_" + object.getCachedState().get(PowerElements.STYLES) + ".geo.json");
    }

    @Override
    public Identifier getTextureResource(PowerElementsEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "textures/animated/fuse_box/fuse_box_" + object.getCachedState().get(PowerElements.STYLES) + ".png");
    }

    @Override
    public Identifier getAnimationResource(PowerElementsEntity animatable) {
        return new Identifier(LosGallysProps.MOD_ID, "animations/fuse_box_" + animatable.getCachedState().get(PowerElements.STYLES) + ".animation.json");
    }
}
