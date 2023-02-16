package me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight;

import me.themiggergames.losgallysprops.util.BlockConnactable;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class PedestrianTrafficLight extends TrafficLightBlock {

    /*
    0-off
    1-broken(on)
    2-always red
    3-always green
    4-always blinking yellow
    */

    public static final Integer MAX_STYLE_COUNT = 4;
    public static final IntProperty STATES = IntProperty.of("statement", 0, MAX_STYLE_COUNT);
    ArrayList<Text> titles = new ArrayList<>() {
        {
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_0"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_1"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_2"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_5"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_6"));
        }
    };

    public PedestrianTrafficLight(Settings settings, boolean useAdditionalStates) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        BlockRotatable.appendRotationProperties(stateManager);
        BlockConnactable.appendConnectionProperties(stateManager, ConnectionTypes.EVERYTHING);
        stateManager.add(STATES);
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

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(NORTH, canConnect(world, pos, Direction.SOUTH))
                .with(SOUTH, canConnect(world, pos, Direction.NORTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(WEST, canConnect(world, pos, Direction.WEST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN));
    }

    @Override
    public IntProperty getIntProperty() {
        return STATES;
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLE_COUNT;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return titles;
    }
}
