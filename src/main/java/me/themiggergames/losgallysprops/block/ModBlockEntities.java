package me.themiggergames.losgallysprops.block;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.decorative.cctv.CCTVEntity;
import me.themiggergames.losgallysprops.block.decorative.lavalamp.LavaLampEntity;
import me.themiggergames.losgallysprops.block.decorative.powerelements.PowerElementsEntity;
import me.themiggergames.losgallysprops.block.decorative.streetProps.entities.BenchEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static void registerBlockEntities() {
        registerBlockEntity("lava_lamp_entity", LAVA_LAMP_ENTITY);
        registerBlockEntity("cctv_entity", CCTV_ENTITY);
        registerBlockEntity("power_element_entity", POWER_ELEMENTS_ENTITY);
        //Registering Bench Entity
        Registry.register(Registries.ENTITY_TYPE,new Identifier(LosGallysProps.MOD_ID, "bench_entity"), BENCH_ENTITY);
    }
    public static void registerBlockEntity(String name, BlockEntityType<?> block) {
        Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(LosGallysProps.MOD_ID, name), block);
    }
    public static BlockEntityType<LavaLampEntity> LAVA_LAMP_ENTITY = FabricBlockEntityTypeBuilder.create(LavaLampEntity::new, ModBlocks.LAVA_LAMP).build();
    public static BlockEntityType<CCTVEntity> CCTV_ENTITY = FabricBlockEntityTypeBuilder.create(CCTVEntity::new, ModBlocks.CCTV).build();
    public static BlockEntityType<PowerElementsEntity> POWER_ELEMENTS_ENTITY = FabricBlockEntityTypeBuilder.create(PowerElementsEntity::new, ModBlocks.FUSE_BOX).build();
    public static EntityType<BenchEntity> BENCH_ENTITY = FabricEntityTypeBuilder.create(SpawnGroup.MISC, BenchEntity::new).build();


}
