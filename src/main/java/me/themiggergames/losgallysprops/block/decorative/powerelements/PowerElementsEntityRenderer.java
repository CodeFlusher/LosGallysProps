package me.themiggergames.losgallysprops.block.decorative.powerelements;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PowerElementsEntityRenderer extends GeoBlockRenderer<PowerElementsEntity> {
    public PowerElementsEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new PowerElementsModel());
    }

}
