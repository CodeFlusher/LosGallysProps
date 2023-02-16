package me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class OnWallTrafficLight extends TrafficLightBlock {
    SymmetricVoxelShapeController voxelShapeController;

    public OnWallTrafficLight(Settings settings, SymmetricVoxelShapeController shapeController) {
        super(settings);
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
        voxelShapeController = shapeController;
        setDefaultState(this.getDefaultState().with(NORTH, false).with(SOUTH, false).with(UP, false).with(DOWN,false).with(EAST, false).with(WEST,false));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        DebugLogger.sendMessage(this.getClass().getName()+" Get Placement State");
        return getDefaultState().with(FACING, ctx.getPlayerFacing()).with(ROTATION,0);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        return voxelShapeController.getVoxelOutline(dir);
    }

}