package me.themiggergames.losgallysprops.items;


import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.customitems.StyleEditor;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item CONFIGURATIOR = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS).fireproof().maxCount(1).maxDamage(3));
    public static final Item MCE_ONE = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final Item MCE_TWO = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final Item MCE_FIVE = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final Item MCE_TEN = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final Item GLD_ONE = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final Item GLD_TEN = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final Item GLD_HUNDRED = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
    public static final StyleEditor STYLE_EDITOR = new StyleEditor(new Item.Settings().group(ModItemGroup.LOSGALLYS).fireproof().maxCount(1));

    public static void registerItems(){
        DebugLogger.sendMessage("Registering Items");
        DebugLogger.sendMessage("MCE");
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_one"), MCE_ONE);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_two"), MCE_TWO);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_five"), MCE_FIVE);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_ten"), MCE_TEN);
        DebugLogger.sendMessage("GLD");
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "gld_one"), GLD_ONE);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "gld_ten"), GLD_TEN);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "gld_hundred"), GLD_HUNDRED);
        DebugLogger.sendMessage("CONFIGURATOR");
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "configurator"), CONFIGURATIOR);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "style_editor"), STYLE_EDITOR);
    }
}
