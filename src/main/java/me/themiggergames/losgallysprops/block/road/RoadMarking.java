package me.themiggergames.losgallysprops.block.road;

import me.themiggergames.losgallysprops.block.BlockRotatable;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RoadMarking extends BlockRotatable {
    public RoadMarking(Settings settings) {
        super(settings, true);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 0.1f, 1f);
    }


}
