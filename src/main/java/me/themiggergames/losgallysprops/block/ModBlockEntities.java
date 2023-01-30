package me.themiggergames.losgallysprops.block;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.decorative.cctv.CCTVEntity;
import me.themiggergames.losgallysprops.block.decorative.lavalamp.LavaLampEntity;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightEntity;
import me.themiggergames.losgallysprops.block.trafficlightcontroller.TrafficLightControllerEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<TrafficLightControllerEntity> TRAFFIC_LIGHT_CONTROLLER_ENTITY = FabricBlockEntityTypeBuilder.create(TrafficLightControllerEntity::new, ModBlocks.TRAFFIC_LIGHT_CONTROLLER_BLOCK).build(null);
    public static BlockEntityType<TrafficLightEntity> TRAFFIC_LIGHT_ENTITY = FabricBlockEntityTypeBuilder.create(TrafficLightEntity::new, ModBlocks.MODERN_TRAFFIC_LIGHT).build(null);
    public static BlockEntityType<LavaLampEntity> LAVA_LAMP_ENTITY = FabricBlockEntityTypeBuilder.create(LavaLampEntity::new, ModBlocks.LAVA_LAMP).build(null);
    public static BlockEntityType<CCTVEntity> CCTV_ENTITY = FabricBlockEntityTypeBuilder.create(CCTVEntity::new, ModBlocks.CCTV).build(null);

    public static void registerBlockEntities(){
       registerBlockEntity("traffic_light_controller_entity", TRAFFIC_LIGHT_CONTROLLER_ENTITY);
       registerBlockEntity("traffic_light_entity", TRAFFIC_LIGHT_ENTITY);
       registerBlockEntity("lava_lamp_entity", LAVA_LAMP_ENTITY);
       registerBlockEntity("cctv_entity", CCTV_ENTITY);
    }
    public static BlockEntityType registerBlockEntity(String name, BlockEntityType block){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LosGallysProps.MOD_ID, name), block);
    }
}
