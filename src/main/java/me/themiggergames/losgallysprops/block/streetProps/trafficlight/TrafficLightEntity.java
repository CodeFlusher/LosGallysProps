package me.themiggergames.losgallysprops.block.streetProps.trafficlight;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TrafficLightEntity extends BlockEntity {
    public TrafficLightEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRAFFIC_LIGHT_ENTITY, pos, state);
    }
}
