package me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtIntArray;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class TrafficLightEntity extends BlockEntity {

    static Integer[] phases = new Integer[10];

    public TrafficLightEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRAFFIC_LIGHT_ENTITY, pos, state);
        for(int i = 0; i<10; i++){
            phases[i]= 0;
        }
    }

    public static void commitPhaseChanges(int nPhase, int statement){
        phases[nPhase] = statement;
    }


    @Override
    public void writeNbt(NbtCompound nbt) {

        for(int i =1; i<=10; i++){
            nbt.putInt(("phase"+i), phases[i-1]);
            DebugLogger.sendMessage("Set Phase " + String.valueOf(i) + " With " + phases[i-1]);
        }

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        for(int i = 1; i<=10; i++){
            phases[i-1] = nbt.getInt("phase"+String.valueOf(i));
        }
    }

}
