package me.themiggergames.losgallysprops.util;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class SymmetricVoxelShapeController {
    protected float thickness;
    protected float wideness;
    protected float height;
    protected float x;
    protected float y;
    protected float z;

    //When you create controller, you give him wideness,height and thickness of model.
    //Also takes distance from number axis(dx, dy, dz)
    //Given setting are for north facing of block for north.
    //  ^y
    //  |   *********************
    //  |   *                   *
    //  |   *                   *height
    //  |dy *       width       *
    //  |---*********************
    //  |   |dx
    //------------------------------------>x
    //Same with Z axis.
    public SymmetricVoxelShapeController(float wide, float thick, float h, float dx, float dy, float dz) {
        wideness = wide;
        thickness = thick;
        height = h;
        x = dx;
        y = dy;
        z = dz;
    }

    //This function returns voxel shape of model.
    //Voxel shape can be changed(rotated), but still have form of north outline.
    public VoxelShape getVoxelOutline(Direction direction) {
        return switch (direction) {
            case WEST -> VoxelShapes.cuboid(z, y, x, z + thickness, y + height, x + wideness);
            case SOUTH -> VoxelShapes.cuboid(x, y, 1 - (thickness + z), x + wideness, y + height, 1 - z);
            case EAST -> VoxelShapes.cuboid(1 - (thickness + z), y, x, 1 - z, y + height, x + wideness);
            case DOWN -> VoxelShapes.cuboid(x, z, y, x + wideness, z + thickness, y + height);
            case UP -> VoxelShapes.cuboid(x, 1 - (thickness + z), y, x + wideness, 1 - z, y + height);
            default -> VoxelShapes.cuboid(x, y, z, x + wideness, y + height, z + thickness);
        };
    }

}
