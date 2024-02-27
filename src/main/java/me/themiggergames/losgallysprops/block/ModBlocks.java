package me.themiggergames.losgallysprops.block;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.block.decorative.cctv.CCTVBlock;
import me.themiggergames.losgallysprops.block.decorative.handrails.LeftHandRail;
import me.themiggergames.losgallysprops.block.decorative.handrails.LeftHandRailEnd;
import me.themiggergames.losgallysprops.block.decorative.handrails.RightHandRail;
import me.themiggergames.losgallysprops.block.decorative.handrails.RightHandRailEnd;
import me.themiggergames.losgallysprops.block.decorative.house.*;
import me.themiggergames.losgallysprops.block.decorative.lavalamp.LavaLamp;
import me.themiggergames.losgallysprops.block.decorative.lighting.OldLamp;
import me.themiggergames.losgallysprops.block.decorative.powerelements.PowerElements;
import me.themiggergames.losgallysprops.block.decorative.powerelements.PowerSocket;
import me.themiggergames.losgallysprops.block.decorative.road.RoadMarking;
import me.themiggergames.losgallysprops.block.decorative.road.RoadSign;
import me.themiggergames.losgallysprops.block.decorative.road.RoadSignNotRotatable;
import me.themiggergames.losgallysprops.block.decorative.road.SpeedBump;
import me.themiggergames.losgallysprops.block.decorative.roof.RoofSlopeBlock;
import me.themiggergames.losgallysprops.block.decorative.roof.RoofTopBlock;
import me.themiggergames.losgallysprops.block.decorative.streetProps.*;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.OnWallTrafficLight;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.PedestrianTrafficLight;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightBlock;
import me.themiggergames.losgallysprops.block.decorative.tunnel.SubwayPowerRail;
import me.themiggergames.losgallysprops.block.decorative.tunnel.TunnelBlock;
import me.themiggergames.losgallysprops.block.decorative.tunnel.TunnelCornerConnector;
import me.themiggergames.losgallysprops.block.decorative.tunnel.TunnelEnd;
import me.themiggergames.losgallysprops.block.trafficlightcontroller.TrafficLightControllerBlock;
import me.themiggergames.losgallysprops.debugtools.DebugBlock;
import me.themiggergames.losgallysprops.items.ModItemGroup;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.shape.VoxelShapes;

import java.util.ArrayList;

public class ModBlocks {

    public static class ItemRegistryEntry {
        private final Item item;
        private final Text text;
        private final ItemGroup group;

        public ItemRegistryEntry(Item item, ItemGroup group) {
            this.item = item;
            this.text = group.getDisplayName();
            this.group = group;
        }

        public Item getItem() {
            return item;
        }

        public ItemGroup getGroup() {
            return group;
        }

        @Override
        public String toString() {
            return "ItemRegistryEntry{" +
                    "item=" + item +
                    ", text=" + text +
                    ", group=" + group +
                    '}';
        }

        public Text getText() {
            return text;
        }
    }

    public static final ArrayList<ItemRegistryEntry> entries = new ArrayList<>();

    public static final Phone PHONE = new Phone(FabricBlockSettings.create().strength(2f),
            ModSounds.PHONE_SOUND_EVENT,
            VoxelShapes.cuboid(0.1, 0, 0.1f, 0.9f, 0.3f, 0.9f));
    public static final Intercom METAKOM = new Intercom(FabricBlockSettings.create().strength(2f).nonOpaque(),
            ModSounds.METAKOM_SOUND_EVENT,
            new SymmetricVoxelShapeController(0.5f, 0.1f, 1f, 0.25f, 0f, 0.9f));
    public static final FancyPost FANCY_IRON_POST = new FancyPost(FabricBlockSettings.create().strength(3f));
    public static final RoadMarking TAXI_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking ONLY_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking ROAD_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking TOLL_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking GIVE_WAY_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking BUS_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking TRAM_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STOP_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking DISABLED_MARKING = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_RIGHT_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_LEFT_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking STRAIGHT_BOTH_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LEFT_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking RIGHT_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LEFT_RIGHT_ARROW = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign NO_TURN_LEFT = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign NO_TURN_RIGHT = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign CROSSING = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign GIVE_WAY = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign ONE_WAY = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign ONE_WAY_END = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign MAJORITY_ROAD = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign MAJORITY_ROAD_END = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT30 = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT60 = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT70 = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT90 = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT100 = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadSign LIMIT120 = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT30MARK = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT60MARK = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT70MARK = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT90MARK = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT100MARK = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final RoadMarking LIMIT120MARK = new RoadMarking(FabricBlockSettings.create().strength(2f).noCollision().nonOpaque());
    public static final Block WHITE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block ORANGE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block MAGENTA_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block LIGHT_BLUE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block YELLOW_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block LIME_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block PINK_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block GRAY_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block LIGHT_GRAY_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block CYAN_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block PURPLE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block BLUE_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block BROWN_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block GREEN_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block RED_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block BLACK_CONCRETE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final Block WHITE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block ORANGE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block MAGENTA_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block LIGHT_BLUE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block YELLOW_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block LIME_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block PINK_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block GRAY_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block LIGHT_GRAY_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block CYAN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block PURPLE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block BLUE_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block BROWN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block GREEN_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block RED_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final Block BLACK_CONCRETE_SLAB = new SlabBlock(FabricBlockSettings.create().strength(3f));
    public static final RoadSign DRAIN = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision());
    public static final RoadSign DRAIN_T = new RoadSign(FabricBlockSettings.create().strength(2f).noCollision());
    public static final DrainPipe DRAIN_PIPE_STONE = new DrainPipe(FabricBlockSettings.create().strength(2f));
    public static final DrainPipe DRAIN_PIPE_STONEBRICKS = new DrainPipe(FabricBlockSettings.create().strength(2f));
    public static final DrainPipe DRAIN_PIPE_LIGHT_GRAY_CONCRETE = new DrainPipe(FabricBlockSettings.create().strength(2f));
    public static final DrainPipe DRAIN_PIPE_GRAY_CONCRETE = new DrainPipe(FabricBlockSettings.create().strength(2f));
    public static final DecorPanel OAK_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel SPRUCE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel ACACIA_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel DARK_OAK_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel JUNGLE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel MANGROVE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel BIRCH_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel CRIMSON_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel WARPED_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel STONE_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final DecorPanel QUARTZ_DECOR_PANEL = new DecorPanel(FabricBlockSettings.create().strength(1f));
    public static final TrapdoorBlock MANHOLE = new TrapdoorBlock(FabricBlockSettings.create().strength(4f).nonOpaque(), BlockSetType.IRON);
    public static final TrapdoorBlock STREET_DRAIN = new TrapdoorBlock(FabricBlockSettings.create().strength(4f).nonOpaque(), BlockSetType.IRON);
    public static final DebugBlock DEBUG_BLOCK = new DebugBlock(FabricBlockSettings.create());
    public static final TrafficLightBlock MODERN_TRAFFIC_LIGHT = new TrafficLightBlock(FabricBlockSettings.create().strength(4f));
    public static final TrafficLightBlock OLD_TRAFFIC_LIGHT = new TrafficLightBlock(FabricBlockSettings.create().strength(4f));
    public static final OnWallTrafficLight ONWALL_MODERN_TRAFFIC_LIGHT = new OnWallTrafficLight(FabricBlockSettings.create().strength(4f), new SymmetricVoxelShapeController(0.7f, 0.15f, 2f, 0.15f, -0.5f, 0f));
    public static final OnWallTrafficLight ONWALL_OLD_TRAFFIC_LIGHT = new OnWallTrafficLight(FabricBlockSettings.create().strength(4f), new SymmetricVoxelShapeController(0.7f, 0.15f, 2f, 0.15f, -0.5f, 0f));
    public static final PedestrianTrafficLight PEDESTRIAN_MODERN_TRAFFIC_LIGHT = new PedestrianTrafficLight(FabricBlockSettings.create(), true);
    public static final PedestrianTrafficLight PEDESTRIAN_OLD_TRAFFIC_LIGHT = new PedestrianTrafficLight(FabricBlockSettings.create(), true);
    public static final TrafficLightControllerBlock TRAFFIC_LIGHT_CONTROLLER_BLOCK = new TrafficLightControllerBlock(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_OAK_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_OAK_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_OAK_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_OAK_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_SPRUCE_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_SPRUCE_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_SPRUCE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_SPRUCE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_JUNGLE_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_JUNGLE_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_JUNGLE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_JUNGLE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_DARK_OAK_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_DARK_OAK_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_DARK_OAK_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_DARK_OAK_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_ACACIA_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_ACACIA_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_ACACIA_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_ACACIA_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_BIRCH_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_BIRCH_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_BIRCH_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_BIRCH_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_MANGROVE_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_MANGROVE_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_MANGROVE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_MANGROVE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_CRIMSON_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_CRIMSON_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_CRIMSON_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_CRIMSON_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_WARPED_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_WARPED_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_WARPED_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_WARPED_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_STONE_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_STONE_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_STONE_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_STONE_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final RightHandRail RIGHT_HAND_QUARTZ_HANDRAIL = new RightHandRail(FabricBlockSettings.create());
    public static final LeftHandRail LEFT_HAND_QUARTZ_HANDRAIL = new LeftHandRail(FabricBlockSettings.create());
    public static final RightHandRailEnd RIGHT_HAND_QUARTZ_HANDRAIL_END = new RightHandRailEnd(FabricBlockSettings.create());
    public static final LeftHandRailEnd LEFT_HAND_QUARTZ_HANDRAIL_END = new LeftHandRailEnd(FabricBlockSettings.create());
    public static final VerticalSlab BRICK_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab OAK_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab BIRCH_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab SPRUCE_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab JUNGLE_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab ACACIA_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab DARK_OAK_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab MANGROVE_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab WARPED_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final VerticalSlab CRIMSON_VERTICAL_SLAB = new VerticalSlab(FabricBlockSettings.create());
    public static final RoofTopBlock SMOOTH_STONE_ROOF_TOP = new RoofTopBlock(FabricBlockSettings.create());
    public static final RoofTopBlock BRICK_ROOF_TOP = new RoofTopBlock(FabricBlockSettings.create());
    public static final RoofSlopeBlock SMOOTH_STONE_ROOF_SLOPE = new RoofSlopeBlock(FabricBlockSettings.create());
    public static final RoofSlopeBlock BRICK_ROOF_SLOPE = new RoofSlopeBlock(FabricBlockSettings.create());
    public static final LavaLamp LAVA_LAMP = new LavaLamp(FabricBlockSettings.create().nonOpaque());
    public static final PowerElements FUSE_BOX = new PowerElements(FabricBlockSettings.create());
    public static final CCTVBlock CCTV = new CCTVBlock(FabricBlockSettings.create());
    //    public static final TriggerBlock TRIGGER_BLOCK = new TriggerBlock(FabricBlockSettings.of(Material.AIR));
    public static final TrashBin OAK_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin ACACIA_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin JUNGLE_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin DARK_OAK_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin SPRUCE_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin MANGROVE_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin BIRCH_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin WARPED_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin CRIMSON_TRASH_BIN_TYPE_0 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin TRASH_BIN_TYPE_1 = new TrashBin(FabricBlockSettings.create());
    public static final TrashBin TRASH_BIN_TYPE_2 = new TrashBin(FabricBlockSettings.create());
    public static final PowerSocket POWER_SOCKET_TYPE_0 = new PowerSocket(FabricBlockSettings.create());
    public static final PowerSocket POWER_SOCKET_TYPE_1 = new PowerSocket(FabricBlockSettings.create());
    public static final ComputerMonitor MODERN_COMPUTER_MONITOR = new ComputerMonitor(FabricBlockSettings.create());
    public static final ComputerMonitor MODERN_TV = new ComputerMonitor(FabricBlockSettings.create());
    public static final Prop PENCIL_STORAGE = new Prop(FabricBlockSettings.create(), VoxelShapes.cuboid(0.35f, 0, 0.35f, 0.7f, 0.5f, 0.7f));
    public static final Block ASPHALT_BLOCK = new Block(FabricBlockSettings.create());
    public static final SlabBlock ASPHALT_SLAB = new SlabBlock(FabricBlockSettings.create());
    public static final Block ASPHALT_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final VerticalSlab ASPHALT_V_SLAB = new VerticalSlab(FabricBlockSettings.create().strength(3f));
    public static final Block MARBLE_BLOCK = new Block(FabricBlockSettings.create());
    public static final SlabBlock MARBLE_SLAB = new SlabBlock(FabricBlockSettings.create());
    public static final Block MARBLE_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final VerticalSlab MARBLE_V_SLAB = new VerticalSlab(FabricBlockSettings.create().strength(3f));
    public static final Block FLOOR_TILES_BLOCK = new Block(FabricBlockSettings.create());
    public static final SlabBlock FLOOR_TILES_SLAB = new SlabBlock(FabricBlockSettings.create());
    public static final Block FLOOR_TILES_STAIRS = new ModStairsBlock(ModBlocks.NO_TURN_LEFT.getDefaultState(), FabricBlockSettings.create().strength(3f));
    public static final VerticalSlab FLOOR_TILES_V_SLAB = new VerticalSlab(FabricBlockSettings.create().strength(3f));
    public static final Bench SMOOTH_STONE_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench SMOOTH_SANDSTONE_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench SMOOTH_QUARTZ_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench SMOOTH_STONE_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench SMOOTH_SANDSTONE_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench SMOOTH_QUARTZ_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench ACACIA_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench OAK_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench DARK_OAK_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench JUNGLE_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench MANGROVE_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench BIRCH_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench SPRUCE_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench CRIMSON_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench WARPED_BENCH = new Bench(FabricBlockSettings.create());
    public static final Bench ACACIA_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench OAK_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench DARK_OAK_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench JUNGLE_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench MANGROVE_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench BIRCH_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench SPRUCE_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench CRIMSON_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
    public static final Bench WARPED_BENCH_TYPE_1 = new Bench(FabricBlockSettings.create());
//    public static final FlowerPot FLOWER_POT = new FlowerPot(FabricBlockSettings.create());
    public static final Window WINDOW = new Window(FabricBlockSettings.create());
    public static final TunnelBlock TUNNEL_BLOCK = new TunnelBlock(FabricBlockSettings.create());
    public static final TunnelCornerConnector TUNNEL_CORNER_CONNECTOR = new TunnelCornerConnector(FabricBlockSettings.create());
    public static final TunnelEnd TUNNEL_END = new TunnelEnd(FabricBlockSettings.create());
    public static final SubwayPowerRail POWER_RAIL_WITHOUT_POST = new SubwayPowerRail(FabricBlockSettings.create());
    public static final SubwayPowerRail POWER_RAIL_WITH_POST = new SubwayPowerRail(FabricBlockSettings.create());
    public static final SubwayPowerRail DOUBLE_SIDE_POWER_RAIL_WITHOUT_POST = new SubwayPowerRail(FabricBlockSettings.create());
    public static final SubwayPowerRail DOUBLE_SIDE_POWER_RAIL_WITH_POST = new SubwayPowerRail(FabricBlockSettings.create());
    public static final RoadSignNotRotatable SUBWAY_LOGO = new RoadSignNotRotatable(FabricBlockSettings.create());
    public static final ScaffoldingBlock IRON_SCAFFOLDING = new ScaffoldingBlock(FabricBlockSettings.create());
//    public static final SpeedBump SPEED_BUMP = new SpeedBump(FabricBlockSettings.create());
    public static final OldLamp WALL_GRID_LANTERN = new OldLamp(FabricBlockSettings.create());

    public static void registerBlocks() {
        InformativeLogger.info("LosGallysProps Main", "Registering Blocks");
        RegisterBlock("phone", PHONE, ModItemGroup.LGDECOOFFICE);
        RegisterBlock("metakom", METAKOM, ModItemGroup.LGDECOOFFICE);
        RegisterBlock("debugblock", DEBUG_BLOCK, ModItemGroup.SPECIAL);
        RegisterBlock("fancy_iron_post", FANCY_IRON_POST, ModItemGroup.LGROAD);
//        RegisterBlock("trigger_block",TRIGGER_BLOCK, ModItemGroup.SPECIAL);
        RegisterBlock("lava_lamp", LAVA_LAMP, ModItemGroup.LGDECOHOUSE);
        RegisterBlock("iron_scaffolding", IRON_SCAFFOLDING, ModItemGroup.LGBUILDINGBLOCKS);


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

        registerDoors();

        registerTrashBins();

        registerPowerSockets();

        registerBenches();

        registerPCs();

        registerProps();

        registerMaterialBlocks();

        registerFlowerPots();

        registerTunels();

        registerSpeedBumps();

        registerLanterns();
    }


    public static Block RegisterBlock(String name, Block block, ItemGroup itemGroup) {
        RegisterBlockItem(name, block, itemGroup);
        return Registry.register(Registries.BLOCK, new Identifier(LosGallysProps.MOD_ID, name), block);
    }

    //Registering block item
    public static Item RegisterBlockItem(String name, Block block, ItemGroup itemGroup) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
        entries.add(new ItemRegistryEntry(blockItem, itemGroup));
        return Registry.register(Registries.ITEM, new Identifier(LosGallysProps.MOD_ID, name),
                blockItem);
    }

    private static void registerHandRails() {
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

    private static void registerDoors() {
//        RegisterBlock("custom_door", CUSTOM_DOOR_BLOCK, ModItemGroup.SPECIAL);
    }

    private static void registerRoofSlopes() {
        RegisterBlock("smooth_stone_roof_top", SMOOTH_STONE_ROOF_TOP, ModItemGroup.LGOUTSIDE);
        RegisterBlock("smooth_stone_roof_slope", SMOOTH_STONE_ROOF_SLOPE, ModItemGroup.LGOUTSIDE);
        RegisterBlock("brick_roof_top", BRICK_ROOF_TOP, ModItemGroup.LGOUTSIDE);
        RegisterBlock("brick_roof_slope", BRICK_ROOF_SLOPE, ModItemGroup.LGOUTSIDE);
    }

    private static void registerCCTVs() {
        RegisterBlock("cctv", CCTV, ModItemGroup.LGOUTSIDE);
    }

    private static void registerDecorPanels() {
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

    private static void registerVerticalSlabs() {
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

    private static void registerTrafficLights() {
        RegisterBlock("modern_traffic_light", MODERN_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("old_traffic_light", OLD_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("pedestrian_modern_traffic_light", PEDESTRIAN_MODERN_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("pedestrian_old_traffic_light", PEDESTRIAN_OLD_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("on_wall_modern_traffic_light", ONWALL_MODERN_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("on_wall_old_traffic_light", ONWALL_OLD_TRAFFIC_LIGHT, ModItemGroup.LGROAD);
        RegisterBlock("traffic_light_controller", TRAFFIC_LIGHT_CONTROLLER_BLOCK, ModItemGroup.LGOUTSIDE);
    }

    private static void registerDrains() {
        RegisterBlock("drain_t", DRAIN_T, ModItemGroup.LGOUTSIDE);
        RegisterBlock("drain", DRAIN, ModItemGroup.LGOUTSIDE);
        RegisterBlock("manhole", MANHOLE, ModItemGroup.LGOUTSIDE);
        RegisterBlock("street_drain", STREET_DRAIN, ModItemGroup.LGOUTSIDE);

        RegisterBlock("drain_pipe_block", DRAIN_PIPE_STONE, ModItemGroup.LGOUTSIDE);
        RegisterBlock("drain_pipe_block_bricks", DRAIN_PIPE_STONEBRICKS, ModItemGroup.LGOUTSIDE);
        RegisterBlock("drain_pipe_block_light_gray_concrete", DRAIN_PIPE_LIGHT_GRAY_CONCRETE, ModItemGroup.LGOUTSIDE);
        RegisterBlock("drain_pipe_block_gray_concrete", DRAIN_PIPE_GRAY_CONCRETE, ModItemGroup.LGOUTSIDE);

    }

    private static void registerModConcrete() {
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

    private static void registerRoadSigns() {
        RegisterBlock("give_way", GIVE_WAY, ModItemGroup.LGROAD);
        RegisterBlock("one_way", ONE_WAY, ModItemGroup.LGROAD);
        RegisterBlock("one_way_end", ONE_WAY_END, ModItemGroup.LGROAD);
        RegisterBlock("majority_road", MAJORITY_ROAD, ModItemGroup.LGROAD);
        RegisterBlock("majority_road_end", MAJORITY_ROAD_END, ModItemGroup.LGROAD);
        RegisterBlock("no_turn_left", NO_TURN_LEFT, ModItemGroup.LGROAD);
        RegisterBlock("no_turn_right", NO_TURN_RIGHT, ModItemGroup.LGROAD);
        RegisterBlock("crossing", CROSSING, ModItemGroup.LGROAD);

        RegisterBlock("subway_logo", SUBWAY_LOGO, ModItemGroup.LGOUTSIDE);
    }

    private static void registerRoadMarks() {
        RegisterBlock("straight_arrow", STRAIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("straight_right_arrow", STRAIGHT_RIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("straight_left_arrow", STRAIGHT_LEFT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("straight_both_arrow", STRAIGHT_BOTH_ARROW, ModItemGroup.LGROAD);

        RegisterBlock("give_way_marking", GIVE_WAY_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("stop_marking", STOP_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("bus_marking", BUS_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("tram_marking", TRAM_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("toll_marking", TOLL_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("only_marking", ONLY_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("taxi_marking", TAXI_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("road_marking", ROAD_MARKING, ModItemGroup.LGROAD);
        RegisterBlock("disabled_marking", DISABLED_MARKING, ModItemGroup.LGROAD);

        RegisterBlock("left_right_arrow", LEFT_RIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("right_arrow", RIGHT_ARROW, ModItemGroup.LGROAD);
        RegisterBlock("left_arrow", LEFT_ARROW, ModItemGroup.LGROAD);
    }

    private static void registerSpeedLimitMarkings() {
        RegisterBlock("limit_30_mark", LIMIT30MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_60_mark", LIMIT60MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_70_mark", LIMIT70MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_90_mark", LIMIT90MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_100_mark", LIMIT100MARK, ModItemGroup.LGROAD);
        RegisterBlock("limit_120_mark", LIMIT120MARK, ModItemGroup.LGROAD);
    }

    private static void registerSpeedLimitBlocks() {
        RegisterBlock("limit_30", LIMIT30, ModItemGroup.LGROAD);
        RegisterBlock("limit_60", LIMIT60, ModItemGroup.LGROAD);
        RegisterBlock("limit_70", LIMIT70, ModItemGroup.LGROAD);
        RegisterBlock("limit_90", LIMIT90, ModItemGroup.LGROAD);
        RegisterBlock("limit_100", LIMIT100, ModItemGroup.LGROAD);
        RegisterBlock("limit_120", LIMIT120, ModItemGroup.LGROAD);
    }

    private static void registerPowerSupplies() {
        RegisterBlock("fuse_box", FUSE_BOX, ModItemGroup.LGOUTSIDE);
    }

    private static void registerTrashBins() {
        RegisterBlock("oak_trash_bin_type_0", OAK_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("birch_trash_bin_type_0", BIRCH_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("acacia_trash_bin_type_0", ACACIA_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("spruce_trash_bin_type_0", SPRUCE_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("dark_oak_trash_bin_type_0", DARK_OAK_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("mangrove_trash_bin_type_0", MANGROVE_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("jungle_trash_bin_type_0", JUNGLE_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("warped_trash_bin_type_0", WARPED_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("crimson_trash_bin_type_0", CRIMSON_TRASH_BIN_TYPE_0, ModItemGroup.LGOUTSIDE);
        RegisterBlock("trash_bin_type_1", TRASH_BIN_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("trash_bin_type_2", TRASH_BIN_TYPE_2, ModItemGroup.LGOUTSIDE);
    }

    private static void registerBenches() {
        RegisterBlock("smooth_stone_bench", SMOOTH_STONE_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("smooth_quartz_bench", SMOOTH_QUARTZ_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("smooth_sandstone_bench", SMOOTH_SANDSTONE_BENCH, ModItemGroup.LGOUTSIDE);

        RegisterBlock("smooth_stone_bench_type_1", SMOOTH_STONE_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("smooth_quartz_bench_type_1", SMOOTH_QUARTZ_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("smooth_sandstone_bench_type_1", SMOOTH_SANDSTONE_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);

        RegisterBlock("acacia_bench", ACACIA_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("oak_bench", OAK_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("dark_oak_bench", DARK_OAK_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("spruce_bench", SPRUCE_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("mangrove_bench", MANGROVE_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("jungle_bench", JUNGLE_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("birch_bench", BIRCH_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("warped_bench", WARPED_BENCH, ModItemGroup.LGOUTSIDE);
        RegisterBlock("crimson_bench", CRIMSON_BENCH, ModItemGroup.LGOUTSIDE);

        RegisterBlock("acacia_bench_type_1", ACACIA_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("oak_bench_type_1", OAK_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("dark_oak_bench_type_1", DARK_OAK_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("spruce_bench_type_1", SPRUCE_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("mangrove_bench_type_1", MANGROVE_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("jungle_bench_type_1", JUNGLE_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("birch_bench_type_1", BIRCH_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("warped_bench_type_1", WARPED_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
        RegisterBlock("crimson_bench_type_1", CRIMSON_BENCH_TYPE_1, ModItemGroup.LGOUTSIDE);
    }

    private static void registerPowerSockets() {
        RegisterBlock("power_socket_type_0", POWER_SOCKET_TYPE_0, ModItemGroup.LGDECOHOUSE);
        RegisterBlock("power_socket_type_1", POWER_SOCKET_TYPE_1, ModItemGroup.LGDECOHOUSE);
    }

    private static void registerPCs() {
        RegisterBlock("modern_computer_monitor", MODERN_COMPUTER_MONITOR, ModItemGroup.LGDECOOFFICE);
        RegisterBlock("modern_tv", MODERN_TV, ModItemGroup.LGDECOOFFICE);
    }

    private static void registerProps() {
        RegisterBlock("pencil_storage", PENCIL_STORAGE, ModItemGroup.LGDECOOFFICE);
        RegisterBlock("window", WINDOW, ModItemGroup.LGDECOHOUSE);
    }

    private static void registerMaterialBlocks() {
        RegisterBlock("asphalt_block", ASPHALT_BLOCK, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("asphalt_stairs", ASPHALT_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("asphalt_slab", ASPHALT_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("asphalt_vertical_slab", ASPHALT_V_SLAB, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("marble_block", MARBLE_BLOCK, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("marble_stairs", MARBLE_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("marble_slab", MARBLE_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("marble_vertical_slab", MARBLE_V_SLAB, ModItemGroup.LGBUILDINGBLOCKS);

        RegisterBlock("floor_tiles_block", FLOOR_TILES_BLOCK, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("floor_tiles_stairs", FLOOR_TILES_STAIRS, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("floor_tiles_slab", FLOOR_TILES_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
        RegisterBlock("floor_tiles_vertical_slab", FLOOR_TILES_V_SLAB, ModItemGroup.LGBUILDINGBLOCKS);
    }

    private static void registerFlowerPots() {
//        RegisterBlock("flower_pot", FLOWER_POT, ModItemGroup.SPECIAL);
    }

    private static void registerTunels() {
        RegisterBlock("tunnel_block", TUNNEL_BLOCK, ModItemGroup.LGSUBWAY);
        RegisterBlock("tunnel_connector", TUNNEL_CORNER_CONNECTOR, ModItemGroup.LGSUBWAY);
        RegisterBlock("tunnel_end", TUNNEL_END, ModItemGroup.LGSUBWAY);
        RegisterBlock("contact_rail_without_post", POWER_RAIL_WITHOUT_POST, ModItemGroup.LGSUBWAY);
        RegisterBlock("contact_rail_with_post", POWER_RAIL_WITH_POST, ModItemGroup.LGSUBWAY);
        RegisterBlock("double_side_contact_rail_without_post", DOUBLE_SIDE_POWER_RAIL_WITHOUT_POST, ModItemGroup.LGSUBWAY);
        RegisterBlock("double_side_contact_rail_with_post", DOUBLE_SIDE_POWER_RAIL_WITH_POST, ModItemGroup.LGSUBWAY);
    }

    private static void registerSpeedBumps() {
//        RegisterBlock("speed_bump", SPEED_BUMP, ModItemGroup.LGROAD);
    }

    private static void registerLanterns() {
        RegisterBlock("wall_grid_lantern", WALL_GRID_LANTERN, ModItemGroup.LGOUTSIDE);
    }
}