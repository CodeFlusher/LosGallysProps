package me.themiggergames.losgallysprops.items;


import me.themiggergames.losgallysprops.LosGallysProps;
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
//    public static final Item MAJORITY_ROAD_END = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item ONE_WAY_END = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item GIVE_WAY = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item LIMIT30 = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item LIMIT60 = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item LIMIT70 = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item LIMIT90 = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item LIMIT100 = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item LIMIT120 = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item MAJORITY_ROAD = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item NO_TURN_RIGHT = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item NO_TURN_LEFT = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item ONE_WAY = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item T_ROAD = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item TURN = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//    public static final Item X_ROAD = new Item(new Item.Settings().group(ModItemGroup.LOSGALLYS));
//
//



    public static void registerItems(){

        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_one"), MCE_ONE);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_two"), MCE_TWO);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_five"), MCE_FIVE);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "mce_ten"), MCE_TEN);

        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "gld_one"), GLD_ONE);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "gld_ten"), GLD_TEN);
        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "gld_hundred"), GLD_HUNDRED);

        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "configurator"), CONFIGURATIOR);

//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "majority_road_end"), MAJORITY_ROAD_END);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "one_way_end"), ONE_WAY_END);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "give_way"), GIVE_WAY);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "limit_30"), LIMIT30);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "limit_60"), LIMIT60);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "limit_70"), LIMIT70);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "limit_90"), LIMIT90);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "limit_100"), LIMIT100);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "limit_120"), LIMIT120);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "majority_road"), MAJORITY_ROAD);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "no_turn_right"), NO_TURN_RIGHT);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "no_turn_left"), NO_TURN_LEFT);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "one_way"), ONE_WAY);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "t_road"), T_ROAD);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "turn_roadsign"), TURN);
//        Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, "x_road"), X_ROAD);


    }
}
