package me.themiggergames.losgallysprops.block.decorative;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RightHandRailEnd extends HandRail{
    public RightHandRailEnd(Settings settings) {
        super(settings, true);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        switch (dir){
            case NORTH:
                return VoxelShapes.cuboid(0f, -0.5f,0.8f, 0.6f, 1.5f,1f);
            case EAST:
                return VoxelShapes.cuboid(0f, -0.5f,0f, 0.2f, 1.5f,0.6f);
            case SOUTH:
                return VoxelShapes.cuboid(0.4f, -0.5f,0f, 1f, 1.5f,0.2f);
            case WEST:
                return VoxelShapes.cuboid(0.8f, -0.5f,0.4f, 1f, 1.5f,1f);
            default:
                return VoxelShapes.cuboid(0f, -0.5f,0f, 1f, 1.5f,0.5f);
        }
    }
}
