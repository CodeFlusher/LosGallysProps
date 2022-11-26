package me.themiggergames.losgallysprops.block.streetProps.trafficlight;

import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.block.BlockRotatable;
import me.themiggergames.losgallysprops.util.CustomIntProperty;
import me.themiggergames.losgallysprops.util.IntegerStatementManager;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
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
import net.minecraft.world.block.NeighborUpdater;
import org.jetbrains.annotations.Nullable;

public class TrafficLightBlock extends BlockRotatable implements BlockEntityProvider {

    public static IntegerStatementManager manager = IntegerStatementManager.of(0,6);
    /*
    0-off
    1-broken(on)
    2-always red
    3-always yellow
    4-always blinking yellow
    5-always green
    6-always blinking yellow
     */
    public static final CustomIntProperty MODE = new CustomIntProperty("statement", 0,6);
    public static final BooleanProperty SOUTH = BooleanProperty.of("north");
    public static final BooleanProperty NORTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(BlockRotatable.rotation);
        stateManager.add(MODE);
//        stateManager.add(NORTH);
//        stateManager.add(EAST);
//        stateManager.add(SOUTH);
//        stateManager.add(WEST);
//        stateManager.add(UP);
//        stateManager.add(DOWN);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrafficLightEntity(pos, state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.25f,0f,0.25f, 0.75f, 1f, 0.75f);
    }

    boolean addStatements;

    public TrafficLightBlock(Settings settings, boolean useAdditionalStates) {
        super(settings, useAdditionalStates);
        addStatements = useAdditionalStates;
//        setDefaultState(this.getDefaultState().with(EAST, false)
//                .with(WEST, false)
//                .with(SOUTH, false)
//                .with(NORTH, false)
//                .with(UP, false)
//                .with(DOWN, false)
//        );
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        DebugLogger.sendMessage("Click Spotted");
        if(placedBy.getInventory().getMainHandStack().getItem() == ModItems.CONFIGURATIOR){
            DebugLogger.sendMessage("Click Spotted");
            world.setBlockState(blockPos, blockState.with(MODE, manager.changeStatement()));
        } else {
            placedBy.sendMessage(Text.translatable("block.losgallysprops.trafficlight.setstatement."+manager.getCurrentStatement()), true);
        }

        return ActionResult.SUCCESS;
    }

    public static class OnWallTrafficLight extends TrafficLightBlock {
        SymmetricVoxelShapeController voxelShapeController;

        public OnWallTrafficLight(Settings settings, SymmetricVoxelShapeController shapeController) {
            super(settings, false);
            voxelShapeController = shapeController;
        }

        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
            Direction dir = state.get(FACING);
            switch (dir) {
                case NORTH:
                    return voxelShapeController.getNorthShape();
                case EAST:
                    return voxelShapeController.getEastShape();
                case SOUTH:
                    return voxelShapeController.getSouthShape();
                case WEST:
                    return voxelShapeController.getWestShape();
                default:
                    return VoxelShapes.cuboid(0f, -0.5f, 0f, 1f, 1.5f, 0.5f);
            }
        }
    }
    
    public static class PedestrianTrafficLight extends TrafficLightBlock {

        public static IntegerStatementManager pedestrianmanager = IntegerStatementManager.of(0,4);

        /*
        0-off
        1-broken(on)
        2-always red
        5-always green
        6-always blinking yellow
        */
        public PedestrianTrafficLight(Settings settings, boolean useAdditionalStates) {
            super(settings, useAdditionalStates);
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
                world.setBlockState(blockPos, blockState.with(MODE, pedestrianmanager.changeStatement()));
            } else {
                placedBy.sendMessage(Text.translatable("block.losgallysprops.pedestriantrafficlight.setstatement."+manager.getCurrentStatement()), true);
            }

            return ActionResult.SUCCESS;
        }

    }


}
