package me.themiggergames.losgallysprops.block.decorative.lavalamp;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class LavaLampEntityRenderer extends GeoBlockRenderer<LavaLampEntity> {
    public LavaLampEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new LavaLampModel());
    }

    @Override
    public RenderLayer getRenderType(LavaLampEntity animatable, float partialTick, MatrixStack poseStack, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, int packedLight, Identifier texture) {
            return RenderLayer.getEntityTranslucentCull(getTextureLocation(animatable));
    }
}
