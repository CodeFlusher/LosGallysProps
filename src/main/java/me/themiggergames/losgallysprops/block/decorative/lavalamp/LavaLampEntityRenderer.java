package me.themiggergames.losgallysprops.block.decorative.lavalamp;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LavaLampEntityRenderer extends GeoBlockRenderer<LavaLampEntity> {
    public LavaLampEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new LavaLampModel());
    }

}
