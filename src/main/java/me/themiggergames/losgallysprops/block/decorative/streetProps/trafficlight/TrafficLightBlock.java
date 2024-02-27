package me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight;

import me.themiggergames.losgallysprops.block.decorative.road.RoadSign;
import me.themiggergames.losgallysprops.block.decorative.streetProps.FancyPost;
import me.themiggergames.losgallysprops.util.BlockConnactable;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class TrafficLightBlock extends HorizontalFacingBlock implements BlockRotatable, BlockConnactable, StyledBlock {
    public static final Integer MAX_STYLE = 7;
    public static final IntProperty STATES = IntProperty.of("statement", 0, MAX_STYLE - 1);

    ArrayList<Text> titles = new ArrayList<>() {
        {
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_0"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_1"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_2"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_3"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_4"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_5"));
            add(Text.translatable("ui.losgallysprops.trafficlight.setstatement_6"));
        }
    };

    public TrafficLightBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        BlockRotatable.appendRotationProperties(stateManager);
        BlockConnactable.appendConnectionProperties(stateManager, ConnectionTypes.EVERYTHING);
        stateManager.add(STATES);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return FancyPost.combiner.getShape(state);
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
                .with(DOWN, canConnect(ctx.getWorld(), blockPos, Direction.DOWN))
                .with(STATES, 0);
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
        return state.with(NORTH, canConnect(world, pos, Direction.NORTH))
                .with(SOUTH, canConnect(world, pos, Direction.SOUTH))
                .with(EAST, canConnect(world, pos, Direction.EAST))
                .with(WEST, canConnect(world, pos, Direction.WEST))
                .with(UP, canConnect(world, pos, Direction.UP))
                .with(DOWN, canConnect(world, pos, Direction.DOWN));
    }

    @Override
    public int getMaxStyle() {
        return MAX_STYLE;
    }


    @Override
    public void changeStyle(int style, World world, BlockPos pos) {
        var state = world.getBlockState(pos);
        world.setBlockState(pos,state.with(STATES, style));
    }

    @Override
    public boolean substandardTitling() {
        return true;
    }

    @Override
    public ArrayList<Text> getTitlesList() {
        return titles;
    }
}
