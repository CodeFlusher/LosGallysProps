package me.themiggergames.losgallysprops.block;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.block.decorative.*;
import me.themiggergames.losgallysprops.block.decorative.cctv.CCTVBlock;
import me.themiggergames.losgallysprops.block.decorative.handrails.LeftHandRail;
import me.themiggergames.losgallysprops.block.decorative.handrails.LeftHandRailEnd;
import me.themiggergames.losgallysprops.block.decorative.handrails.RightHandRail;
import me.themiggergames.losgallysprops.block.decorative.handrails.RightHandRailEnd;
import me.themiggergames.losgallysprops.block.decorative.lavalamp.LavaLamp;
import me.themiggergames.losgallysprops.block.decorative.road.RoadMarking;
import me.themiggergames.losgallysprops.block.decorative.road.RoadSign;
import me.themiggergames.losgallysprops.block.decorative.roof.RoofSlopeBlock;
import me.themiggergames.losgallysprops.block.decorative.roof.RoofTopBlock;
import me.themiggergames.losgallysprops.block.decorative.streetProps.BioToilet;
import me.themiggergames.losgallysprops.block.decorative.streetProps.DrainPipe;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightBlock;
import me.themiggergames.losgallysprops.block.trafficlightcontroller.TrafficLightControllerBlock;
import me.themiggergames.losgallysprops.debugtools.DebugBlock;
import me.themiggergames.losgallysprops.items.ModItemGroup;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShapes;

public class ModBlocks {


   // public static final BankMachine BANK = new BankMachine(FabricBlockSettings.of(Material.METAL).hardness(4f));
    public static final Phone PHONE = new Phone(FabricBlockSettings.of(Material.REDSTONE_LAMP).strength(2f),
           ModSounds.PHONE_SOUND_EVENT,
           VoxelShapes.cuboid(0.1, 0, 0.1f, 0.9f, 0.3f, 0.9f));
    public static final Intercom METAKOM = new Intercom(FabricBlockSettings.of(Material.METAL).strength(2f).nonOpaque(),
            ModSounds.METAKOM_SOUND_EVENT,
            new SymmetricVoxelShapeController(0.5f, 0.1f, 1f, 0.25f, 0f, 0.9f));


    //Concrete Stairs

    //Ambient Gen
//    public static final Block ELECTRICITY_AMBIENT_GEN = Registry.register(Registry.BLOCK, new Identifier("electricity_ambient_gen", LosGallysProps.MOD_ID), new AmbientGenerator(FabricBlockSettings.of(Material.METAL).strength(4f)));
    //Traffic Marking

    //public static final Post FANCY_IRON_POST = new Post(FabricBlockSettings.of(Material.METAL).strength(3f));
    public static final FancyPost FANCY_IRON_POST = new FancyPost(FabricBlockSettings.of(Material.METAL).strength(3f));
    public static final RoadMarking STRAIGHT_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_RIGHT_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_LEFT_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_BOTH_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());

    public static final RoadMarking LEFT_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking RIGHT_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LEFT_RIGHT_ARROW = new RoadMarking(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    //RoadSign

    public static final RoadSign NO_TURN_LEFT = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign NO_TURN_RIGHT = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign CROSSING = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign GIVE_WAY = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign ONE_WAY = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign ONE_WAY_END = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign MAJORITY_ROAD = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign MAJORITY_ROAD_END = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());

    //SpeedRoadSign
    public static final RoadSign LIMIT30 = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT60 = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT70 = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT90 = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT100 = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT120 = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision().nonOpaque());

    //SpeedRoadMarkings
    public static final RoadMarking LIMIT30MARK = new RoadMarking(FabricBlockSettings.of(Material.BARRIER).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT60MARK = new RoadMarking(FabricBlockSettings.of(Material.BARRIER).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT70MARK = new RoadMarking(FabricBlockSettings.of(Material.BARRIER).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT90MARK = new RoadMarking(FabricBlockSettings.of(Material.BARRIER).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT100MARK = new RoadMarking(FabricBlockSettings.of(Material.BARRIER).strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT120MARK = new RoadMarking(FabricBlockSettings.of(Material.BARRIER).strength(2f).noCollision().nonOpaque());

    public static final Block WHITE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block ORANGE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block MAGENTA_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block YELLOW_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block LIME_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block PINK_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block GRAY_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block CYAN_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block PURPLE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block BLUE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block BROWN_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block GREEN_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block RED_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block BLACK_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.of(Material.STONE).strength(3f));

    public static final Block WHITE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block ORANGE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block MAGENTA_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block YELLOW_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block LIME_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block PINK_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block GRAY_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block CYAN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block PURPLE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block BLUE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block BROWN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block GREEN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block RED_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final Block BLACK_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(3f));
    public static final RoadSign DRAIN = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision());
    public static final RoadSign DRAIN_T = new RoadSign(FabricBlockSettings.of(Material.METAL).strength(2f).noCollision());

    public static final DrainPipe DRAIN_PIPE_STONE = new DrainPipe(FabricBlockSettings.of(Material.STONE).strength(2f));
    public static final DrainPipe DRAIN_PIPE_STONEBRICKS = new DrainPipe(FabricBlockSettings.of(Material.STONE).strength(2f));
    public static final DrainPipe DRAIN_PIPE_LIGHT_GRAY_CONCRETE = new DrainPipe(FabricBlockSettings.of(Material.STONE).strength(2f));
    public static final DrainPipe DRAIN_PIPE_GRAY_CONCRETE = new DrainPipe(FabricBlockSettings.of(Material.STONE).strength(2f));
    public static final DecorPanel OAK_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel SPRUCE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel ACACIA_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel DARK_OAK_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel JUNGLE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel MANGROVE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel BIRCH_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel CRIMSON_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel WARPED_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel STONE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final DecorPanel QUARTZ_DECOR_PANEL = new DecorPanel(FabricBlockSettings.of(Material.WOOD).strength(1f));
    public static final TrapdoorBlock MANHOLE = new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(4f).nonOpaque());
    public static final TrapdoorBlock STREET_DRAIN = new TrapdoorBlock(FabricBlockSettings.of(Material.WOOD).strength(4f).nonOpaque());
    //registering block
    public static final DebugBlock DEBUG_BLOCK = new DebugBlock(FabricBlockSettings.of(Material.STONE));
    public static final TrafficLightBlock MODERN_TRAFFIC_LIGHT = new TrafficLightBlock(FabricBlockSettings.of(Material.METAL).strength(4f));
    public static final TrafficLightBlock.OnWallTrafficLight ONWALL_MODERN_TRAFFIC_LIGHT = new TrafficLightBlock.OnWallTrafficLight(FabricBlockSettings.of(Material.METAL).strength(4f), new SymmetricVoxelShapeController(0.7f,0.15f,2f,0.15f,-0.5f,0f));
    public static final TrafficLightBlock.PedestrianTrafficLight PEDESTRIAN_MODERN_TRAFFIC_LIGHT = new TrafficLightBlock.PedestrianTrafficLight(FabricBlockSettings.of(Material.METAL), true);
    public static final TrafficLightControllerBlock TRAFFIC_LIGHT_CONTROLLER_BLOCK = new TrafficLightControllerBlock(FabricBlockSettings.of(Material.METAL));
    public static final RightHandRail RIGHT_HAND_OAK_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_OAK_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_OAK_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_OAK_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_SPRUCE_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_SPRUCE_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_SPRUCE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_SPRUCE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_JUNGLE_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_JUNGLE_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_JUNGLE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_JUNGLE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_DARK_OAK_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_DARK_OAK_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_DARK_OAK_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_DARK_OAK_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_ACACIA_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_ACACIA_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_ACACIA_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_ACACIA_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_BIRCH_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_BIRCH_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_BIRCH_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_BIRCH_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_MANGROVE_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_MANGROVE_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_MANGROVE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_MANGROVE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_CRIMSON_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_CRIMSON_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_CRIMSON_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_CRIMSON_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_WARPED_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_WARPED_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_WARPED_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_WARPED_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_STONE_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_STONE_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_STONE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_STONE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRail RIGHT_HAND_QUARTZ_HANDRAIL = new RightHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRail LEFT_HAND_QUARTZ_HANDRAIL = new LeftHandRail(FabricBlockSettings.of(Material.WOOD));
    public static final RightHandRailEnd RIGHT_HAND_QUARTZ_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final LeftHandRailEnd LEFT_HAND_QUARTZ_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab BRICK_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.STONE));
    public static final VerticalSlab OAK_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab BIRCH_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab SPRUCE_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab JUNGLE_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab ACACIA_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab DARK_OAK_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab MANGROVE_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab WARPED_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final VerticalSlab CRIMSON_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.of(Material.WOOD));
    public static final RoofTopBlock SMOOTH_STONE_ROOF_TOP = new RoofTopBlock(FabricBlockSettings.of(Material.STONE));
    public static final RoofTopBlock BRICK_ROOF_TOP = new RoofTopBlock(FabricBlockSettings.of(Material.STONE));
    public static final RoofSlopeBlock SMOOTH_STONE_ROOF_SLOPE = new RoofSlopeBlock(FabricBlockSettings.of(Material.STONE));
    public static final RoofSlopeBlock BRICK_ROOF_SLOPE = new RoofSlopeBlock(FabricBlockSettings.of(Material.STONE));
    public static final LavaLamp LAVA_LAMP = new LavaLamp(FabricBlockSettings.of(Material.GLASS).nonOpaque());
    public static final PowerElements FUSE_BOX_TYPE_1 = new PowerElements(FabricBlockSettings.of(Material.METAL));
    public static final CCTVBlock CCTV= new CCTVBlock(FabricBlockSettings.of(Material.METAL));

    public static void registerBlocks(){
        RegisterBlock("phone", PHONE, ModItemGroup.LOSGALLYS);
        RegisterBlock("metakom", METAKOM, ModItemGroup.LOSGALLYS);
        RegisterBlock("debugblock", DEBUG_BLOCK, null);
        RegisterBlock("fancy_iron_post",FANCY_IRON_POST, ModItemGroup.LOSGALLYS);
        RegisterBlock("lava_lamp", LAVA_LAMP, ModItemGroup.LOSGALLYS);
//        registerAmbientGenerators();

        registerDrains();

        registerRoadSigns();

        registerSpeedLimitMarkings();

        registerSpeedLimitBlocks();

        registerRoadMarks();

        registerModConcrete();

        registerDecorPanels();

        registerTrafficLights();

        registerHandRails();

        registerVerticalSlabs();

        registerRoofSlopes();

        registerPowerSupplies();

        registerCCTVs();
    }

    public static Block RegisterBlock(String name, Block block, ItemGroup itemGroup){
        RegisterBlockItem(name, block, itemGroup);
        return Registry.register(Registry.BLOCK, new Identifier(LosGallysProps.MOD_ID, name), block);
    }

    //Registering block item
    public static Item RegisterBlockItem(String name, Block block, ItemGroup itemGroup){
        return Registry.register(Registry.ITEM, new Identifier(LosGallysProps.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
    }

//    public static void registerAmbientGenerators(){
//        RegisterBlock("electricity_ambient_generator", ELECTRICITY_AMBIENT_GEN,ModItemGroup.LOSGALLYS);
//    }

    private static void registerHandRails(){
        RegisterBlock("right_hand_oak_handrail", RIGHT_HAND_OAK_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_oak_handrail", LEFT_HAND_OAK_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_oak_handrail_end", RIGHT_HAND_OAK_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_oak_handrail_end", LEFT_HAND_OAK_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_dark_oak_handrail", RIGHT_HAND_DARK_OAK_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_dark_oak_handrail", LEFT_HAND_DARK_OAK_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_dark_oak_handrail_end", RIGHT_HAND_DARK_OAK_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_dark_oak_handrail_end", LEFT_HAND_DARK_OAK_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_jungle_handrail", RIGHT_HAND_JUNGLE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_jungle_handrail", LEFT_HAND_JUNGLE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_jungle_handrail_end", RIGHT_HAND_JUNGLE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_jungle_handrail_end", LEFT_HAND_JUNGLE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_spruce_handrail", RIGHT_HAND_SPRUCE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_spruce_handrail", LEFT_HAND_SPRUCE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_spruce_handrail_end", RIGHT_HAND_SPRUCE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_spruce_handrail_end", LEFT_HAND_SPRUCE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_acacia_handrail", RIGHT_HAND_ACACIA_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_acacia_handrail", LEFT_HAND_ACACIA_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_acacia_handrail_end", RIGHT_HAND_ACACIA_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_acacia_handrail_end", LEFT_HAND_ACACIA_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_birch_handrail", RIGHT_HAND_BIRCH_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_birch_handrail", LEFT_HAND_BIRCH_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_birch_handrail_end", RIGHT_HAND_BIRCH_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_birch_handrail_end", LEFT_HAND_BIRCH_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_mangrove_handrail", RIGHT_HAND_MANGROVE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_mangrove_handrail", LEFT_HAND_MANGROVE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_mangrove_handrail_end", RIGHT_HAND_MANGROVE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_mangrove_handrail_end", LEFT_HAND_MANGROVE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_crimson_handrail", RIGHT_HAND_CRIMSON_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_crimson_handrail", LEFT_HAND_CRIMSON_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_crimson_handrail_end", RIGHT_HAND_CRIMSON_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_crimson_handrail_end", LEFT_HAND_CRIMSON_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_warped_handrail", RIGHT_HAND_WARPED_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_warped_handrail", LEFT_HAND_WARPED_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_warped_handrail_end", RIGHT_HAND_WARPED_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_warped_handrail_end", LEFT_HAND_WARPED_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_stone_handrail", RIGHT_HAND_STONE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_stone_handrail", LEFT_HAND_STONE_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_stone_handrail_end", RIGHT_HAND_STONE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_stone_handrail_end", LEFT_HAND_STONE_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("right_hand_quartz_handrail", RIGHT_HAND_QUARTZ_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_quartz_handrail", LEFT_HAND_QUARTZ_HANDRAIL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("right_hand_quartz_handrail_end", RIGHT_HAND_QUARTZ_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("left_hand_quartz_handrail_end", LEFT_HAND_QUARTZ_HANDRAIL_END, ModItemGroup.LGBUILDINGBLOCKS);
    }

    private static void registerRoofSlopes(){
        RegisterBlock("smooth_stone_roof_top", SMOOTH_STONE_ROOF_TOP, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("smooth_stone_roof_slope", SMOOTH_STONE_ROOF_SLOPE, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("brick_roof_top", BRICK_ROOF_TOP, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("brick_roof_slope", BRICK_ROOF_SLOPE, ModItemGroup.LGBUILDINGBLOCKS);
    } private static void registerCCTVs(){
        RegisterBlock("cctv", CCTV, ModItemGroup.LOSGALLYS);
    }
    private static void registerDecorPanels(){
        RegisterBlock("oak_decoration_panel", OAK_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("spruce_decoration_panel", SPRUCE_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("birch_decoration_panel", BIRCH_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("acacia_decoration_panel", ACACIA_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("dark_oak_decoration_panel", DARK_OAK_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("jungle_decoration_panel", JUNGLE_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("crimson_decoration_panel", CRIMSON_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("warped_decoration_panel", WARPED_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("mangrove_decoration_panel", MANGROVE_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("quartz_decoration_panel", QUARTZ_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("stone_decoration_panel", STONE_DECOR_PANEL, ModItemGroup.LGBUILDINGBLOCKS);
    }

    private static void registerVerticalSlabs(){
        RegisterBlock("brick_vertical_slab", BRICK_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("acacia_vertical_slab", ACACIA_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("warped_vertical_slab", WARPED_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("crimson_vertical_slab", CRIMSON_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("spruce_vertical_slab", SPRUCE_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("jungle_vertical_slab", JUNGLE_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("oak_vertical_slab", OAK_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("birch_vertical_slab", BIRCH_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("dark_oak_vertical_slab", DARK_OAK_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("mangrove_vertical_slab", MANGROVE_VERTICAL_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
    }

    private static void registerTrafficLights(){
        RegisterBlock("modern_traffic_light",MODERN_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("pedestrian_modern_traffic_light",PEDESTRIAN_MODERN_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("on_wall_modern_traffic_light",ONWALL_MODERN_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("traffic_light_controller", TRAFFIC_LIGHT_CONTROLLER_BLOCK, ModItemGroup.LOSGALLYS);
    }

    private static void registerDrains(){
        RegisterBlock("drain_t", DRAIN_T, ModItemGroup.LOSGALLYS);
        RegisterBlock("drain", DRAIN, ModItemGroup.LOSGALLYS);
        RegisterBlock("manhole", MANHOLE, ModItemGroup.LOSGALLYS);
        RegisterBlock("street_drain", STREET_DRAIN, ModItemGroup.LOSGALLYS);

        RegisterBlock("drain_pipe_block", DRAIN_PIPE_STONE, ModItemGroup.LOSGALLYS);
        RegisterBlock("drain_pipe_block_bricks", DRAIN_PIPE_STONEBRICKS, ModItemGroup.LOSGALLYS);
        RegisterBlock("drain_pipe_block_light_gray_concrete", DRAIN_PIPE_LIGHT_GRAY_CONCRETE, ModItemGroup.LOSGALLYS);
        RegisterBlock("drain_pipe_block_gray_concrete", DRAIN_PIPE_GRAY_CONCRETE, ModItemGroup.LOSGALLYS);

    }

    private static void registerModConcrete(){
        RegisterBlock("white_concrete_stairs", WHITE_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("orange_concrete_stairs", ORANGE_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("magenta_concrete_stairs", MAGENTA_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("light_blue_concrete_stairs", LIGHT_BLUE_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("yellow_concrete_stairs", YELLOW_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("lime_concrete_stairs", LIME_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("pink_concrete_stairs", PINK_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("gray_concrete_stairs", GRAY_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("light_gray_concrete_stairs", LIGHT_GRAY_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("cyan_concrete_stairs", CYAN_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("purple_concrete_stairs", PURPLE_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("blue_concrete_stairs", BLUE_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("brown_concrete_stairs", BROWN_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("green_concrete_stairs", GREEN_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("red_concrete_stairs", RED_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("black_concrete_stairs", BLACK_CONCRETE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("white_concrete_slab", WHITE_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("orange_concrete_slab", ORANGE_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("magenta_concrete_slab", MAGENTA_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("light_blue_concrete_slab", LIGHT_BLUE_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("yellow_concrete_slab", YELLOW_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("lime_concrete_slab", LIME_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("pink_concrete_slab", PINK_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("gray_concrete_slab", GRAY_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("light_gray_concrete_slab", LIGHT_GRAY_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("cyan_concrete_slab", CYAN_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("purple_concrete_slab", PURPLE_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("blue_concrete_slab", BLUE_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("brown_concrete_slab", BROWN_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("green_concrete_slab", GREEN_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("red_concrete_slab", RED_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("black_concrete_slab", BLACK_CONCRETE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);

    }

    private static void registerRoadSigns(){
        RegisterBlock("give_way",GIVE_WAY, ModItemGroup.LGROAD);
        RegisterBlock("one_way",ONE_WAY, ModItemGroup.LGROAD);
        RegisterBlock("one_way_end",ONE_WAY_END, ModItemGroup.LGROAD);
        RegisterBlock("majority_road",MAJORITY_ROAD, ModItemGroup.LGROAD);
        RegisterBlock("majority_road_end",MAJORITY_ROAD_END, ModItemGroup.LGROAD);
        RegisterBlock("no_turn_left",NO_TURN_LEFT, ModItemGroup.LGROAD);
        RegisterBlock("no_turn_right",NO_TURN_RIGHT, ModItemGroup.LGROAD);
        RegisterBlock("crossing",CROSSING, ModItemGroup.LGROAD);
    }

    private static void registerRoadMarks(){
        RegisterBlock("straight_arrow", STRAIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("straight_right_arrow", STRAIGHT_RIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("straight_left_arrow", STRAIGHT_LEFT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("straight_both_arrow", STRAIGHT_BOTH_ARROW, ModItemGroup.LGROAD);

        RegisterBlock("left_right_arrow", LEFT_RIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("right_arrow", RIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("left_arrow", LEFT_ARROW, ModItemGroup.LGROAD);
    }

    private static void registerSpeedLimitMarkings(){
        RegisterBlock("limit_30_mark",LIMIT30MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_60_mark",LIMIT60MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_70_mark",LIMIT70MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_90_mark",LIMIT90MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_100_mark",LIMIT100MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_120_mark",LIMIT120MARK, ModItemGroup.LGROAD);
    }

    private static void registerSpeedLimitBlocks(){
        RegisterBlock("limit_30", LIMIT30, ModItemGroup.LGROAD);
        RegisterBlock("limit_60", LIMIT60, ModItemGroup.LGROAD);
        RegisterBlock("limit_70", LIMIT70, ModItemGroup.LGROAD);
        RegisterBlock("limit_90", LIMIT90, ModItemGroup.LGROAD);
        RegisterBlock("limit_100", LIMIT100, ModItemGroup.LGROAD);
        RegisterBlock("limit_120", LIMIT120, ModItemGroup.LGROAD);
    }

    private static void registerPowerSupplies(){
        RegisterBlock("fuse_box_type1", FUSE_BOX_TYPE_1, ModItemGroup.LOSGALLYS);
    }
}