package me.themiggergames.losgallysprops.block.decorative.streetProps.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class BenchEntity extends Entity {

    public BenchEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    public boolean shouldRender(double distance) {
        return false;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
