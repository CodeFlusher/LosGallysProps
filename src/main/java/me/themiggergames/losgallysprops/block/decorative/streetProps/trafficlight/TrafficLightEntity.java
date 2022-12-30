package me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtIntArray;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class TrafficLightEntity extends BlockEntity {

    NbtIntArray phaseMod = new NbtIntArray(new ArrayList<>());
    public TrafficLightEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRAFFIC_LIGHT_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
    }

}
