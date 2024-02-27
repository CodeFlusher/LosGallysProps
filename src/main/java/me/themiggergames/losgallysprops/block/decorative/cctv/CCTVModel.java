package me.themiggergames.losgallysprops.block.decorative.cctv;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CCTVModel extends GeoModel<CCTVEntity> {

    private Identifier getPlacement(String base, CCTVEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, base + "_" + object.getCachedState().get(CCTVBlock.TYPES).asString() + ".geo.json");
    }

    @Override
    public Identifier getModelResource(CCTVEntity object) {
        return getPlacement("geo/cctv_type_" + object.getStyleNBT(), object);
    }

    @Override
    public Identifier getTextureResource(CCTVEntity object) {
        return new Identifier(LosGallysProps.MOD_ID, "textures/animated/cctv/cctv_type_" + object.getStyleNBT() + ".png");
    }

    @Override
    public Identifier getAnimationResource(CCTVEntity animatable) {
        return new Identifier(LosGallysProps.MOD_ID, "animations/cctv_type_0.animation.json");
    }

}
