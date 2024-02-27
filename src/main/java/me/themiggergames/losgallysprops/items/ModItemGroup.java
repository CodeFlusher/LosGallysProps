package me.themiggergames.losgallysprops.items;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup LOSGALLYS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(LosGallysProps.MOD_ID, "losgallys"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.MCE_ONE))
                    .displayName(Text.translatable("itemGroup.losgallysprops.losgallys"))
                    .entries((displayContext, entries) -> {
                        ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.losgallys"))).forEach(it -> entries.add(it.getItem()));
                        ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.losgallys"))).forEach(it -> entries.add(it.getItem()));
                    })
                    .build());
    public static final ItemGroup LGROAD = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.LIMIT30))
            .displayName(Text.translatable("itemGroup.losgallysprops.lgroad"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.lgroad"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.lgroad"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();
    public static final ItemGroup LGBUILDINGBLOCKS = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.WHITE_CONCRETE_STAIRS))
            .displayName(Text.translatable("itemGroup.losgallysprops.lgbuildblock"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.lgbuildblock"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.lgbuildblock"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();
    public static final ItemGroup LGDECOOFFICE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.LAVA_LAMP))
            .displayName(Text.translatable("itemGroup.losgallysprops.decoration_office"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.decoration_office"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.decoration_office"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();

    public static final ItemGroup LGDECOHOUSE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.LAVA_LAMP))
            .displayName(Text.translatable("itemGroup.losgallysprops.decoration_house"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.decoration_house"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.decoration_house"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();
    public static final ItemGroup LGOUTSIDE = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.TRASH_BIN_TYPE_1))
            .displayName(Text.translatable("itemGroup.losgallysprops.decoration_building"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.decoration_building"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.decoration_building"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();
    public static final ItemGroup SPECIAL = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CONFIGURATIOR))
            .displayName(Text.translatable("itemGroup.losgallysprops.special"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.special"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.special"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();
    public static final ItemGroup LGSUBWAY = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.POWER_RAIL_WITH_POST))
            .displayName(Text.translatable("itemGroup.losgallysprops.subway"))
            .entries((displayContext, entries) -> {
                ModBlocks.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.subway"))).forEach(it -> entries.add(it.getItem()));
                ModItems.entries.stream().filter(it -> it.getText().equals(Text.translatable("itemGroup.losgallysprops.subway"))).forEach(it -> entries.add(it.getItem()));
            })
            .build();

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "lgroad"), LGROAD);
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "lgbuildblock"), LGBUILDINGBLOCKS);
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "decoration_office"), LGDECOOFFICE);
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "decoration_house"), LGDECOHOUSE);
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "decoration_building"), LGOUTSIDE);
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "special"), SPECIAL);
        Registry.register(Registries.ITEM_GROUP, new Identifier(LosGallysProps.MOD_ID, "subway"), LGSUBWAY);
    }


}
