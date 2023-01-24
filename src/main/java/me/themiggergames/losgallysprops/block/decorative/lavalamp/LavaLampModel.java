package me.themiggergames.losgallysprops.block.decorative.lavalamp;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static me.themiggergames.losgallysprops.block.decorative.lavalamp.LavaLamp.TEST;

public class LavaLampModel extends AnimatedGeoModel<LavaLampEntity> {

    @Override
    public Identifier getModelResource(LavaLampEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "geo/lava_lamp.geo.json");
    }

    @Override
    public Identifier getTextureResource(LavaLampEntity object) {
            return new Identifier(LosGallysProps.MOD_ID, "textures/animated/lava_lamp/lava_lamp_orange.png");
    }

    @Override
    public Identifier getAnimationResource(LavaLampEntity animatable) {
        return new Identifier(LosGallysProps.MOD_ID, "animations/lava_lamp.animation.json");
    }
}
