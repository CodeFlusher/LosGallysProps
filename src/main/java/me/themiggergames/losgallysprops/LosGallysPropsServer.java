package me.themiggergames.losgallysprops;

import io.netty.util.IllegalReferenceCountException;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class LosGallysPropsServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        DebugLogger.sendMessage("Server Initialized");


    }
}
