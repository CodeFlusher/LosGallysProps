package me.themiggergames.losgallysprops.block.decorative.roof;

import me.themiggergames.losgallysprops.util.BlockConnactable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class RoofTopBlock extends Block implements BlockConnactable {

    public RoofTopBlock(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(this.getStateManager().getDefaultState().with(NORTH, false)
                .with(WEST, false)
                .with(SOUTH, false)
                .with(EAST, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockConnactable.appendConnectionProperties(builder, ConnectionTypes.HORISONTAL_ONLY);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState1 = blockView.getBlockState(blockPos.north());
        BlockState blockState2 = blockView.getBlockState(blockPos.east());
        BlockState blockState3 = blockView.getBlockState(blockPos.south());
        BlockState blockState4 = blockView.getBlockState(blockPos.west());
        boolean north_con = false;
        boolean east_con = false;
        boolean south_con = false;
        boolean west_con = false;
        if(blockState3.getBlock() instanceof RoofTopBlock){
            north_con = true;
        }
        if(blockState2.getBlock() instanceof RoofTopBlock){
            east_con = true;
        }
        if(blockState1.getBlock() instanceof RoofTopBlock){
            south_con = true;
        }
        if(blockState4.getBlock() instanceof RoofTopBlock){
            west_con = true;
        }

        return getDefaultState().with(NORTH, north_con)
                .with(EAST, east_con)
                .with(SOUTH,south_con)
                .with(WEST, west_con);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        BlockState blockState1 = world.getBlockState(pos.north());
        BlockState blockState2 = world.getBlockState(pos.east());
        BlockState blockState3 = world.getBlockState(pos.south());
        BlockState blockState4 = world.getBlockState(pos.west());
        boolean north_con = false;
        boolean east_con = false;
        boolean south_con = false;
        boolean west_con = false;
        if(blockState3.getBlock() instanceof RoofTopBlock){
            north_con = true;
        }
        if(blockState2.getBlock() instanceof RoofTopBlock){
            east_con = true;
        }
        if(blockState1.getBlock() instanceof RoofTopBlock){
            south_con = true;
        }
        if(blockState4.getBlock() instanceof RoofTopBlock){
            west_con = true;
        }

        return state.with(NORTH, north_con)
                .with(EAST, east_con)
                .with(SOUTH,south_con)
                .with(WEST, west_con);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0,0,0,1,0.5,1);
    }
}
