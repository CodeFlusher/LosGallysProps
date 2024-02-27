package me.themiggergames.losgallysprops.block.decorative.cctv;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CCTVEntityRenderer extends GeoBlockRenderer<CCTVEntity> {
    public CCTVEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new CCTVModel());
    }
}
