package me.themiggergames.losgallysprops.util;

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
        thickness = thick;
        wideness = wide;
        height = h;
        x = dx;
        y = dy;
        z = dz;
    }

    public VoxelShape getNorthShape(){
        return VoxelShapes.cuboid(x, y, z, x+wideness, y+height, z+thickness);
    }
    public VoxelShape getWestShape(){
        return VoxelShapes.cuboid(z, y, x, z+thickness, y+height, x+wideness);
    }
    public VoxelShape getSouthShape(){
        return VoxelShapes.cuboid(x, y, 1-z-thickness, x+wideness, y+height, 1-z);
    }
    public VoxelShape getEastShape(){
        return VoxelShapes.cuboid(1-thickness, y, x, 1-z, y+height, x+wideness);
    }
}
