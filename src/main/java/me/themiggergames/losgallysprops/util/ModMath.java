package me.themiggergames.losgallysprops.util;

import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class ModMath {

    public boolean isInRange(double x, float range1, float range2, boolean including){
       if(including){
        if (range1<range2){
            return x >= range1 && x <= range2;
        }else{
            return x >= range1 || x <= range2;
        }
       }else{
           if (range1<range2){
               return x > range1 && x < range2;
           }else{
               return x > range1 || x < range2;
           }
       }
    }
}
