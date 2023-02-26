package me.themiggergames.losgallysprops.mixins;

import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FenceBlock.class)
abstract class FenceBlockMixin extends HorizontalConnectingBlock {
    @Shadow public abstract boolean canConnect(BlockState state, boolean neighborIsFullSquare, Direction dir);
    @Final
    private static final BooleanProperty NW = BooleanProperty.of("north_west");
    @Final
    private static final BooleanProperty NE = BooleanProperty.of("north_east");
    @Final
    private static final BooleanProperty SW = BooleanProperty.of("south_west");
    @Final
    private static final  BooleanProperty SE = BooleanProperty.of("south_east");

    private static final SymmetricVoxelShapeController stdController = new SymmetricVoxelShapeController(0.25f, 0.4f, 1f, 0.375f, 0, 0.6f);
    private static final SymmetricVoxelShapeController diagonalController = new SymmetricVoxelShapeController(0.4f, 0.4f, 1f, 0.6f, 0, 0.6f);

    public FenceBlockMixin(float radius1, float radius2, float boundingHeight1, float boundingHeight2, float collisionHeight, Settings settings) {
        super(2.0F, 2.0F, 16.0F, 16.0F, 24.0F, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false)
                .with(NW, false).with(NE,false).with(SW,false).with(SE,false)
        );
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(AbstractBlock.Settings settings, CallbackInfo info) {
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false)
                .with(NW, false).with(NE,false).with(SW,false).with(SE,false)
        );
    }

    @Inject(method = "appendProperties", at = @At("HEAD"))
    private void injectedAppendedProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci){
        builder.add(NE).add(NW).add(SW).add(SE);
    }

    /**
     * @author
     * @reason return statement calls getPlacementState, and I want to access blockstate as a variable inside this func.
     */
    @Overwrite
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockPos blockPos2 = blockPos.north();
        BlockPos blockPos3 = blockPos.east();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockPos blockPos6 = blockPos.west().south();
        BlockPos blockPos7 = blockPos.west().north();
        BlockPos blockPos8 = blockPos.east().south();
        BlockPos blockPos9 = blockPos.east().north();
        BlockState blockState2 = blockView.getBlockState(blockPos2);
        BlockState blockState3 = blockView.getBlockState(blockPos3);
        BlockState blockState4 = blockView.getBlockState(blockPos4);
        BlockState blockState5 = blockView.getBlockState(blockPos5);
        BlockState blockState6 = blockView.getBlockState(blockPos6);
        BlockState blockState7 = blockView.getBlockState(blockPos7);
        BlockState blockState8 = blockView.getBlockState(blockPos8);
        BlockState blockState9 = blockView.getBlockState(blockPos9);
        BlockState state = this.getDefaultState();
        state = state.with(NORTH, canConnect(blockState2, blockState2.isFullCube(world, blockPos2), Direction.NORTH));
        state = state.with(EAST, canConnect(blockState3, blockState3.isFullCube(world, blockPos3), Direction.EAST));
        state = state.with(SOUTH, canConnect(blockState4, blockState4.isFullCube(world, blockPos4), Direction.SOUTH));
        state = state.with(WEST, canConnect(blockState5, blockState5.isFullCube(world, blockPos5), Direction.WEST));

        state = state.with(NE, canConnect(blockState9, blockState9.isFullCube(world, blockPos9), Direction.UP));
        state = state.with(NW, canConnect(blockState7, blockState7.isFullCube(world, blockPos7), Direction.UP));
        state = state.with(SE, canConnect(blockState8, blockState8.isFullCube(world, blockPos8), Direction.UP));
        state = state.with(SW, canConnect(blockState6, blockState6.isFullCube(world, blockPos6), Direction.UP));

        state = state.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);

        updateNeighbours(blockPos, world);
        return state;
    }

    /**
     * @author
     * @reason There are fucking hell in return statement, what I'm going to do? Anyway I need check for horizontal connections, so...
     */
    @Overwrite
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        BlockPos blockPos2 = pos.north();
        BlockPos blockPos3 = pos.east();
        BlockPos blockPos4 = pos.south();
        BlockPos blockPos5 = pos.west();
        BlockPos blockPos6 = pos.west().south();
        BlockPos blockPos7 = pos.west().north();
        BlockPos blockPos8 = pos.east().south();
        BlockPos blockPos9 = pos.east().north();
        BlockState blockState2 = world.getBlockState(blockPos2);
        BlockState blockState3 = world.getBlockState(blockPos3);
        BlockState blockState4 = world.getBlockState(blockPos4);
        BlockState blockState5 = world.getBlockState(blockPos5);
        BlockState blockState6 = world.getBlockState(blockPos6);
        BlockState blockState7 = world.getBlockState(blockPos7);
        BlockState blockState8 = world.getBlockState(blockPos8);
        BlockState blockState9 = world.getBlockState(blockPos9);
        state = state.with(NORTH, canConnect(blockState2, blockState2.isFullCube(world, blockPos2), Direction.NORTH));
        state = state.with(EAST, canConnect(blockState3, blockState3.isFullCube(world, blockPos3), Direction.EAST));
        state = state.with(SOUTH, canConnect(blockState4, blockState4.isFullCube(world, blockPos4), Direction.SOUTH));
        state = state.with(WEST, canConnect(blockState5, blockState5.isFullCube(world, blockPos5), Direction.WEST));

        state = state.with(NE, canConnect(blockState9, blockState9.isFullCube(world, blockPos9), Direction.NORTH));
        state = state.with(NW, canConnect(blockState7, blockState7.isFullCube(world, blockPos7), Direction.EAST));
        state = state.with(SE, canConnect(blockState8, blockState8.isFullCube(world, blockPos8), Direction.SOUTH));
        state = state.with(SW, canConnect(blockState6, blockState6.isFullCube(world, blockPos6), Direction.WEST));
        return state;
    }

    private void updateNeighbours(BlockPos pos, World world){
        world.updateNeighbors(pos, this);
        for(Direction dir : DIRECTIONS){
            world.updateNeighborsAlways(pos.offset(dir), this);
        }
    }

}
