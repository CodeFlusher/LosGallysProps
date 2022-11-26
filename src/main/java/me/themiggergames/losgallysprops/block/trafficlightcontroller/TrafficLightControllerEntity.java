package me.themiggergames.losgallysprops.block.trafficlightcontroller;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TrafficLightControllerEntity extends BlockEntity {

    public TrafficLightControllerEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRAFFIC_LIGHT_CONTROLLER_ENTITY, pos, state);
    }
}
