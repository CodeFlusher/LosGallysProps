package me.themiggergames.losgallysprops.util;

import java.util.concurrent.TimeUnit;

public abstract class WaiterWithFunction extends Thread{
    private Integer time;

    public WaiterWithFunction(int time){
       this.time = time;
    }

    public abstract void beforeWait();
    public abstract void afterWait();

    public void run(){
        beforeWait();
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        afterWait();
    }
}
