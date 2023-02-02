package me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight;

import me.themiggergames.losgallysprops.block.decorative.FancyPost;
import me.themiggergames.losgallysprops.block.decorative.road.RoadSign;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.gui.trafficlight.TrafficLightPhaseListDescription;
import me.themiggergames.losgallysprops.gui.trafficlight.TrafficLightScreen;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.BlockConnactable;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import me.themiggergames.losgallysprops.util.IntegerStatementManager;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TrafficLightBlock extends HorizontalFacingBlock implements BlockEntityProvider, BlockRotatable, BlockConnactable {
    public static IntegerStatementManager manager = IntegerStatementManager.of(0,6);
    public static final IntProperty MODE = IntProperty.of("statement", 0,6);
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        BlockRotatable.appendRotationProperties(stateManager);
        BlockConnactable.appendConnectionProperties(stateManager, ConnectionTypes.EVERYTHING);
        stateManager.add(MODE);
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrafficLightEntity(pos, state);
    }
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25f,0f,0.25f, 0.75f, 1f, 0.75f);
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        return getDefaultState().with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw())).with(ROTATION, BlockRotatable.getRotation(ctx.getPlayerYaw())).with(NORTH, canConnect(ctx.getWorld(), blockPos, Direction.NORTH))
                .with(SOUTH, canConnect(ctx.getWorld(), blockPos, Direction.SOUTH))
                .with(EAST, canConnect(ctx.getWorld(), blockPos, Direction.EAST))
                .with(WEST, canConnect(ctx.getWorld(), blockPos, Direction.WEST))
                .with(UP, canConnect(ctx.getWorld(), blockPos, Direction.UP))
                .with(DOWN, canConnect(ctx.getWorld(), blockPos, Direction.DOWN));
    }

    public boolean canConnect(World world, BlockPos pos, Direction dir) {
        BlockPos neighbourPos = pos.offset(dir);
        BlockState neighbourState = world.getBlockState(neighbourPos);
        Block block = world.getBlockState(neighbourPos).getBlock();
        boolean bl1 = block instanceof TrafficLightBlock;
        boolean bl2 = block instanceof RoadSign;
        boolean bl3 = block instanceof FancyPost;
        return (block.isShapeFullCube(neighbourState, world, neighbourPos) || bl2 || bl1 || bl3);
    }
    public boolean canConnect(WorldAccess world, BlockPos pos, Direction dir) {
        BlockPos neighbourPos = pos.offset(dir);
        BlockState neighbourState = world.getBlockState(neighbourPos);
        Block block = world.getBlockState(neighbourPos).getBlock();
        boolean bl1 = block instanceof TrafficLightBlock;
        boolean bl2 = block instanceof RoadSign;
        boolean bl3 = block instanceof FancyPost;
        return (block.isShapeFullCube(neighbourState, world, neighbourPos) || bl2 || bl1 || bl3);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(NORTH, canConnect(world, pos, Direction.SOUTH))
                .with(SOUTH, canConnect(world, pos, Direction.NORTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(WEST, canConnect(world, pos, Direction.WEST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN));
    }

    public TrafficLightBlock(Settings settings) {
        super(settings);
    }



    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        DebugLogger.sendMessage("Click Spotted");
        if(placedBy.getInventory().getMainHandStack().getItem() == ModItems.CONFIGURATIOR && placedBy.isCreative()){
            DebugLogger.sendMessage("Click Spotted");
            MinecraftClient.getInstance().setScreen(new TrafficLightScreen(new TrafficLightPhaseListDescription(world, blockState, blockPos)));
            //world.setBlockState(blockPos, blockState.with(MODE, manager.changeStatement()));
        } else {
            placedBy.sendMessage(Text.translatable("block.losgallysprops.trafficlight.setstatement."+blockState.get(MODE)), true);
        }

        return ActionResult.SUCCESS;
    }

    public static class OnWallTrafficLight extends TrafficLightBlock {
        SymmetricVoxelShapeController voxelShapeController;

        public OnWallTrafficLight(Settings settings, SymmetricVoxelShapeController shapeController) {
            super(settings);
            voxelShapeController = shapeController;
            setDefaultState(this.getDefaultState().with(NORTH, false).with(SOUTH, false).with(UP, false).with(DOWN,false).with(EAST, false).with(WEST,false));
        }

        @Override
        public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
            return getDefaultState().with(FACING, ctx.getPlayerFacing()).with(ROTATION,0);
        }

        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
            Direction dir = state.get(FACING);
            return voxelShapeController.getVoxelOutline(dir);
        }

    }
    
    public static class PedestrianTrafficLight extends TrafficLightBlock {

        public static IntProperty MODES = IntProperty.of("statement", 0, 4);

        /*
        0-off
        1-broken(on)
        2-always red
        3-always green
        4-always blinking yellow
        */
        public PedestrianTrafficLight(Settings settings, boolean useAdditionalStates) {
            super(settings);
        }

        @Override
        public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
            BlockPos blockPos = ctx.getBlockPos();
            return getDefaultState().with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw())).with(ROTATION,BlockRotatable.getRotation(ctx.getPlayerYaw())).with(NORTH, canConnect(ctx.getWorld(), blockPos, Direction.NORTH))
                    .with(SOUTH, canConnect(ctx.getWorld(), blockPos, Direction.SOUTH))
                    .with(EAST, canConnect(ctx.getWorld(), blockPos, Direction.EAST))
                    .with(WEST, canConnect(ctx.getWorld(), blockPos, Direction.WEST))
                    .with(UP, canConnect(ctx.getWorld(), blockPos, Direction.UP))
                    .with(DOWN, canConnect(ctx.getWorld(), blockPos, Direction.DOWN));
        }

        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
            return VoxelShapes.cuboid(0.4f, 0f, 0.4f, 0.6f, 1f, 0.6f);
        }

        @Override
        public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
            DebugLogger.sendMessage("Click Spotted");
            if(placedBy.getInventory().getMainHandStack().getItem() == ModItems.CONFIGURATIOR){
                DebugLogger.sendMessage("Click Spotted");
                world.setBlockState(blockPos, blockState.with(MODE, blockState.get(MODE)));
            } else {
                placedBy.sendMessage(Text.translatable("block.losgallysprops.pedestriantrafficlight.setstatement."+manager.getCurrentStatement()), true);
            }

            return ActionResult.SUCCESS;
        }

        public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
            return state.with(NORTH, canConnect(world, pos, Direction.SOUTH))
                    .with(SOUTH, canConnect(world, pos, Direction.NORTH))
                    .with(EAST, canConnect(world, pos, Direction.EAST))
                    .with(WEST, canConnect(world, pos, Direction.WEST))
                    .with(UP, canConnect(world, pos, Direction.UP))
                    .with(DOWN, canConnect(world, pos, Direction.DOWN));
        }

    }


}
