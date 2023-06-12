package me.themiggergames.losgallysprops.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.ArrayList;

public class DirectionalVoxelShapeCombiner {

    private final SymmetricVoxelShapeController controller;
    private final BlockConnactable.ConnectionTypes types;
    private final VoxelShape base;

    public DirectionalVoxelShapeCombiner(SymmetricVoxelShapeController controller, BlockConnactable.ConnectionTypes types, VoxelShape base) {
        this.controller = controller;
        this.types = types;
        this.base = base;
    }

    public VoxelShape getShape(ArrayList<Direction> directions) {
        ArrayList<VoxelShape> shapes = new ArrayList<>();
        for (Direction dir : directions) {
            shapes.add(controller.getVoxelOutline(dir));
        }
        shapes.add(base);
        return shapes.stream().reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    }

    public VoxelShape getShape(BlockState state) {
        ArrayList<VoxelShape> shapes = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if ((dir == Direction.UP || dir == Direction.DOWN)
                    && types == BlockConnactable.ConnectionTypes.HORISONTAL_ONLY)
                continue;
            if (state.get(BlockConnactable.getProperty(dir))) {
                shapes.add(controller.getVoxelOutline(dir));
            }
        }
        shapes.add(base);
        return shapes.stream().reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    }

}
