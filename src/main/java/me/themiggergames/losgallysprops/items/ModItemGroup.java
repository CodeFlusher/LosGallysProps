package me.themiggergames.losgallysprops.items;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.ModBlocks;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup LOSGALLYS = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "losgallys"),
        () -> new ItemStack(ModItems.MCE_ONE));

    public static final ItemGroup LGROAD = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "lgroad"),
            () -> new ItemStack(ModBlocks.LIMIT30));

    public static final ItemGroup LGBUILDINGBLOCKS = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "lgbuildblock"),
            () -> new ItemStack(ModBlocks.WHITE_CONCRETE_STAIRS));
    public static final ItemGroup LGDECOOFFICE = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "decoration_office"),
            () -> new ItemStack(ModBlocks.PHONE));
    public static final ItemGroup LGDECOHOUSE = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "decoration_house"),
            () -> new ItemStack(ModBlocks.LAVA_LAMP));
    public static final ItemGroup LGOUTSIDE = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "decoration_building"),
            () -> new ItemStack(ModBlocks.TRASH_BIN_TYPE_1));
    public static final ItemGroup SPECIAL = FabricItemGroupBuilder.build(new Identifier(LosGallysProps.MOD_ID, "special"),
            () -> new ItemStack(ModItems.CONFIGURATIOR));
}
