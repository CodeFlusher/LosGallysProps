package me.themiggergames.losgallysprops.block.decorative.streetProps;

import me.themiggergames.losgallysprops.block.decorative.tunnel.TunnelBlock;
import me.themiggergames.losgallysprops.block.decorative.tunnel.TunnelEnd;
import me.themiggergames.losgallysprops.util.ScaffoldingPropertyTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class ScaffoldingBlock extends Block {

    public static final EnumProperty<ScaffoldingPropertyTypes> NORTH = EnumProperty.of("north", ScaffoldingPropertyTypes.class);
    public static final EnumProperty<ScaffoldingPropertyTypes> EAST = EnumProperty.of("east", ScaffoldingPropertyTypes.class);
    public static final EnumProperty<ScaffoldingPropertyTypes> SOUTH = EnumProperty.of("south", ScaffoldingPropertyTypes.class);
    public static final EnumProperty<ScaffoldingPropertyTypes> WEST = EnumProperty.of("west", ScaffoldingPropertyTypes.class);

    public ScaffoldingBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH).add(SOUTH).add(EAST).add(WEST);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = getDefaultState();
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        return state.with(NORTH, getNeighbourType(world, pos, Direction.NORTH))
                .with(SOUTH, getNeighbourType(world, pos, Direction.SOUTH))
                .with(EAST, getNeighbourType(world, pos, Direction.EAST))
                .with(WEST, getNeighbourType(world, pos, Direction.WEST))
                ;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(NORTH, getNeighbourType(world, pos, Direction.NORTH))
                .with(SOUTH, getNeighbourType(world, pos, Direction.SOUTH))
                .with(EAST, getNeighbourType(world, pos, Direction.EAST))
                .with(WEST, getNeighbourType(world, pos, Direction.WEST))
                ;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0, 0.9375f, 0, 1, 1, 1);
    }

    public ScaffoldingPropertyTypes getNeighbourType(WorldAccess world, BlockPos pos, Direction dir) {
        if (!canConnect(world, pos, dir)) {
            return ScaffoldingPropertyTypes.NONE;
        }
        Block block = world.getBlockState(pos.offset(dir)).getBlock();
        if (block instanceof TunnelEnd || block instanceof TunnelBlock) {
            return ScaffoldingPropertyTypes.EXTENDED;
        }
        return ScaffoldingPropertyTypes.STANDARD;
    }

    public boolean canConnect(WorldAccess world, BlockPos pos, Direction dir) {
        BlockState state = world.getBlockState(pos.offset(dir));
        boolean isFullCube = world.getBlockState(pos.offset(dir)).getBlock().isShapeFullCube(state, world, pos);
        return isFullCube || state.getBlock() instanceof TunnelBlock || state.getBlock() instanceof TunnelEnd || state.getBlock() instanceof ScaffoldingBlock;
    }

    public boolean canConnect(World world, BlockPos pos, Direction dir) {
        return canConnect((WorldAccess) world, pos, dir);
    }
}
