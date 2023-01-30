package me.themiggergames.losgallysprops.block.decorative.cctv;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CCTVModel extends AnimatedGeoModel<CCTVEntity> {

    private Identifier getPlacement(String base, CCTVEntity object){
        return new Identifier(LosGallysProps.MOD_ID, base+"_"+object.getCachedState().get(CCTVBlock.TYPES).asString()+".geo.json");
    }

    @Override
    public Identifier getModelResource(CCTVEntity object) {
        switch(object.getCachedState().get(CCTVBlock.STYLE)){
            case 0:
                return getPlacement("geo/cctv_type_0", object);
            default:
                return getPlacement("geo/cctv_type_1", object);
        }
    }

    @Override
    public Identifier getTextureResource(CCTVEntity object) {
        switch(object.getCachedState().get(CCTVBlock.STYLE)){
            case 0:
                return new Identifier(LosGallysProps.MOD_ID, "textures/animated/cctv/cctv_type_0.png");
            default:
                return new Identifier(LosGallysProps.MOD_ID, "textures/animated/cctv/cctv_type_1.png");
        }
    }

    @Override
    public Identifier getAnimationResource(CCTVEntity animatable) {
        return new Identifier(LosGallysProps.MOD_ID, "animations/cctv_type_0.animation.json");
    }

}
