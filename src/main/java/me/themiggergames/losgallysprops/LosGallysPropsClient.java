package me.themiggergames.losgallysprops;

import me.themiggergames.losgallysprops.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class LosGallysPropsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT30, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT60, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT70, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT90, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT100, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT120, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT30MARK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT60MARK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT70MARK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT90MARK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT100MARK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIMIT120MARK, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GIVE_WAY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ONE_WAY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ONE_WAY_END, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAJORITY_ROAD, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAJORITY_ROAD_END, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NO_TURN_LEFT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.NO_TURN_RIGHT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CROSSING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEFT_ARROW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RIGHT_ARROW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LEFT_RIGHT_ARROW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAIGHT_ARROW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAIGHT_RIGHT_ARROW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAIGHT_LEFT_ARROW, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.STRAIGHT_BOTH_ARROW, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MODERN_TRAFFIC_LIGHT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PEDESTRIAN_MODERN_TRAFFIC_LIGHT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ONWALL_MODERN_TRAFFIC_LIGHT, RenderLayer.getCutout());

    }
}

