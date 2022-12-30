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

    public SymmetricVoxelShapeController(float wide, float thick, float h, float dx,float dy, float dz){
        wideness = wide;
        thickness = thick;
        height = h;
        x = dx;
        y = dy;
        z = dz;
    }

    public VoxelShape getVoxelOutline(Direction direction){
        switch (direction){
            case NORTH:
                return VoxelShapes.cuboid(x, y, z, x+wideness, y+height, z+thickness);
            case WEST:
                return VoxelShapes.cuboid(z, y, x, z+thickness, y+height, x+wideness);
            case SOUTH:
                return VoxelShapes.cuboid(x,y,1-(thickness+z), x+wideness, y+height, 1-z);
            case EAST:
                return VoxelShapes.cuboid(1-(thickness+z), y, x, 1-z, y+height, x+wideness);
            default:
                return VoxelShapes.cuboid(x, y, z, x+wideness, y+height, z+thickness);
        }
    }

}
