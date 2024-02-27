package me.themiggergames.losgallysprops.items;


import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.ModBlocks;
import me.themiggergames.losgallysprops.items.customitems.StyleEditor;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItems {
    public static final ArrayList<ModBlocks.ItemRegistryEntry> entries = new ArrayList<>();
    public static final Item CONFIGURATIOR = new Item(new Item.Settings().fireproof().maxCount(1).maxDamage(3));
    public static final Item MCE_ONE = new Item(new Item.Settings());
    public static final Item MCE_TWO = new Item(new Item.Settings());
    public static final Item MCE_FIVE = new Item(new Item.Settings());
    public static final Item MCE_TEN = new Item(new Item.Settings());
    public static final Item GLD_ONE = new Item(new Item.Settings());
    public static final Item GLD_TEN = new Item(new Item.Settings());
    public static final Item GLD_HUNDRED = new Item(new Item.Settings());
    public static final StyleEditor STYLE_EDITOR = new StyleEditor(new Item.Settings().fireproof().maxCount(1));

    public static void registerItems() {
        InformativeLogger.info("LosGallysProps Main","Registering Items");

        registerItem(MCE_ONE, "mce_one");
        registerItem(MCE_TWO, "mce_two");
        registerItem(MCE_FIVE, "mce_five");
        registerItem(MCE_TEN, "mce_ten");

        registerItem(GLD_ONE, "gld_one");
        registerItem(GLD_TEN, "gld_ten");
        registerItem(GLD_HUNDRED, "gld_hundred");

        registerItem(CONFIGURATIOR, "configurator");
        registerItem(STYLE_EDITOR, "style_editor");
    }

    private static void registerItem(Item item, String identifier) {
        registerItem(item, identifier, ModItemGroup.LOSGALLYS);
    }

    private static void registerItem(Item item, String identifier, ItemGroup group) {
        entries.add(new ModBlocks.ItemRegistryEntry(item, group));
        Registry.register(Registries.ITEM, new Identifier(LosGallysProps.MOD_ID, identifier), item);
    }
}
