package me.themiggergames.losgallysprops.block.decorative.cctv;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class CCTVEntityRenderer extends GeoBlockRenderer<CCTVEntity> {
    public CCTVEntityRenderer(BlockEntityRendererFactory.Context context) {
        super(new CCTVModel());
        }

    @Override
    public RenderLayer getRenderType(CCTVEntity animatable, float partialTick, MatrixStack poseStack, @Nullable VertexConsumerProvider bufferSource, @Nullable VertexConsumer buffer, int packedLight, Identifier texture) {
        return RenderLayer.getEntityTranslucentCull(getTextureLocation(animatable));
        }

    @Override
    protected void rotateBlock(Direction facing, MatrixStack poseStack) {
        switch (facing) {
            case SOUTH -> poseStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));
            case WEST -> poseStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
            case NORTH -> poseStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0));
            case EAST -> poseStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(270));
            default -> poseStack.isEmpty();
        }
    }
}
