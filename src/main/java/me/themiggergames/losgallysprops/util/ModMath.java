package me.themiggergames.losgallysprops.util;

import net.minecraft.util.math.Direction;

public class ModMath {
    //Checks if num placed in range of two floats.
    //-----range1*******THIS********range2----
    //Or when range1>range2 check if num placed outside of
    //****THIS*****range2--------------range1******OR THIS****
    //When including is true, range1 and range2 can return true, when n equals them.
    public static boolean isInRange(double x, float range1, float range2, boolean including) {
        if (including) {
            if (range1 < range2) {
                return x >= range1 && x <= range2;
            } else {
                return x >= range1 || x <= range2;
            }
        } else {
            if (range1 < range2) {
                return x > range1 && x < range2;
            } else {
                return x > range1 || x < range2;
            }
        }
    }

    //Function wants to limit number line from one position to other.\
    //Also, function takes number of sectors, that can be used.
    //For example 3 sectors
    //All Sectors are equal.
    //Targeted number n should be inside numeric line
    //Based on given n, function return integer, equals number of sector.
    //For example n placed somewhere in first sector.
    //  !!Possible answers are !!
    //  !![0, nSectors-1]      !!
    //Function returns 0
    //                     n
    //                     |
    //nMin------sector0---------|-----------sector1--------------|-------sector2---------nMax
    public static int getSector(int nMin, int nMax, int nSectors, double n){
        if(nMin>nMax){
            return -1;
        }
        if(!isInRange(n, nMin, nMax, false))
            return -1;
        if(n==nMax){
            return nSectors-1;
        }
        if (n == nMin){
            return 0;
        }
        return (int)(n/((nMax-nMin)/nSectors))/1;
    }

    //Rotates Direction by 90 or -90 Degrees(if inverse == true).
    public static Direction rotateDirection(Direction dir, boolean inverse){
        switch (dir){
            case NORTH:
                dir = Direction.EAST;
                break;
            case EAST:
                dir = Direction.SOUTH;
                break;
            case SOUTH:
                dir = Direction.WEST;
                break;
            case WEST:
                dir = Direction.NORTH;
                break;
        }
        if(inverse)
            return dir.getOpposite();
        return dir;
    }
}
