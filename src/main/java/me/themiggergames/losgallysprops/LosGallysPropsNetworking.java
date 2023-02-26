package me.themiggergames.losgallysprops;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;

public class LosGallysPropsNetworking {
    public static final Identifier STYLE_CHANGE_PACKED_ID = new Identifier(LosGallysProps.MOD_ID, "style_change_packet");

    public static void initializeServerNetworking(){
        ServerPlayNetworking.registerGlobalReceiver(LosGallysPropsNetworking.STYLE_CHANGE_PACKED_ID, (server, player, handler, buf, responseSender) ->{

            BlockPos pos;
            int style;
            try {
                DebugLogger.sendMessage("Reading Block Pos");
                pos = buf.readBlockPos();
                DebugLogger.sendMessage("Reading Style");
                style = buf.readInt();
            } catch (Exception exception){
                InformativeLogger.info("Cannot read BlockPos or Style from buffer! If you see this message as a user, please contact the developer.");
                exception.printStackTrace();
                return;
            }

            server.execute(() -> {
                DebugLogger.sendMessage("Packet Executed");
                ServerWorld world = player.getWorld();
                BlockState state = world.getBlockState(pos);
                if(state.getBlock() instanceof StyledBlock) {
                    world.setBlockState(pos, state.with(((StyledBlock) state.getBlock()).getIntProperty(), style));
                }else
                    InformativeLogger.informativeLog("Block, given to packet isn't a styled block.",
                            new ArrayList<>(){
                                {
                                    add("Something went wrong, and if you see this message as a user, please contact the developer. ");
                                    add("Sending logs might help dev to understand what is gone wrong. ");
                                    add("Additional info: ");
                                }
                            },
                            new ArrayList<>() {
                                {
                                    add(new InformativeLogger.LogItem("Player:", player));
                                    add(new InformativeLogger.LogItem("BlockPos:", pos));
                                    add(new InformativeLogger.LogItem("Block:", state.getBlock()));
                                    add(new InformativeLogger.LogItem("Targeted Style:", style));
                                }
                            }
                    );
            });
        });
    }
}
