package me.themiggergames.losgallysprops.block;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.decorative.cctv.CCTVEntity;
import me.themiggergames.losgallysprops.block.decorative.lavalamp.LavaLampEntity;
import me.themiggergames.losgallysprops.block.decorative.powerelements.PowerElementsEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<LavaLampEntity> LAVA_LAMP_ENTITY = FabricBlockEntityTypeBuilder.create(LavaLampEntity::new, ModBlocks.LAVA_LAMP).build(null);
    public static BlockEntityType<CCTVEntity> CCTV_ENTITY = FabricBlockEntityTypeBuilder.create(CCTVEntity::new, ModBlocks.CCTV).build(null);
    public static BlockEntityType<PowerElementsEntity> POWER_ELEMENTS_ENTITY = FabricBlockEntityTypeBuilder.create(PowerElementsEntity::new, ModBlocks.FUSE_BOX).build(null);
    public static BlockEntityType<PowerElementsEntity> DOOR_ENTITY = FabricBlockEntityTypeBuilder.create(PowerElementsEntity::new, ModBlocks.FUSE_BOX).build(null);

    public static void registerBlockEntities(){
       registerBlockEntity("lava_lamp_entity", LAVA_LAMP_ENTITY);
       registerBlockEntity("cctv_entity", CCTV_ENTITY);
       registerBlockEntity("power_element_entity", POWER_ELEMENTS_ENTITY);
    }
    public static BlockEntityType registerBlockEntity(String name, BlockEntityType block){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(LosGallysProps.MOD_ID, name), block);
    }
}
