package me.themiggergames.losgallysprops.block.decorative.doors;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CustomDoorModel extends AnimatedGeoModel<CustomDoorEntity> {

    @Override
    public Identifier getModelResource(CustomDoorEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "geo/fuse_box_0.geo.json");
    }

    @Override
    public Identifier getTextureResource(CustomDoorEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "textures/animated/fuse_box/fuse_box_0.png");
    }

    @Override
    public Identifier getAnimationResource(CustomDoorEntity animatable) {
        return new Identifier(LosGallysProps.MOD_ID, "animations/fuse_box_0.animation.json");
    }
}
