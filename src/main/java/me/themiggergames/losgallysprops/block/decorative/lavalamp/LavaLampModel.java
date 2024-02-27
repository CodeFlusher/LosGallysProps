package me.themiggergames.losgallysprops.block.decorative.lavalamp;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LavaLampModel extends GeoModel<LavaLampEntity> {

    @Override
    public Identifier getModelResource(LavaLampEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "geo/lava_lamp.geo.json");
    }

    @Override
    public Identifier getTextureResource(LavaLampEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "textures/animated/lava_lamp/lava_lamp.png");
    }

    @Override
    public Identifier getAnimationResource(LavaLampEntity animatable) {
        return new Identifier(LosGallysProps.MOD_ID, "animations/lava_lamp.animation.json");
    }
}
