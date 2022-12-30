package me.themiggergames.losgallysprops.util;

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

    public float getMax(float a, float b){
        if(a>=b){
            return a;
        }else{
            return b;
        }
    }
    public float getMin(float a, float b){
        if(a<=b){
            return a;
        }else{
            return b;
        }
    }
}
