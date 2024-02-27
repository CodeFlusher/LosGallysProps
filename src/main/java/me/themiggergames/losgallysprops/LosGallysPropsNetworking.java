package me.themiggergames.losgallysprops;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LosGallysPropsNetworking {
    public static final Identifier STYLE_CHANGE_PACKET_ID = new Identifier(LosGallysProps.MOD_ID, "style_change_packet");
    public static final Identifier SIT_ON_THE_BENCH_PACKET_ID = new Identifier(LosGallysProps.MOD_ID, "style_change_packet");
    public static final Identifier RECIEVE_NBT_DATA_FROM_SERVER = new Identifier(LosGallysProps.MOD_ID, "style_change_packet");

    public static void initializeClientNetworking(){
        ClientPlayNetworking.registerGlobalReceiver(RECIEVE_NBT_DATA_FROM_SERVER, (client, handler, buf, responseSender) -> {
            World world;
            BlockEntity blockEntity;
            BlockPos position;
            try {
                world = client.world;
                InformativeLogger.debugMessage("Client Networking", world);
                position = buf.readBlockPos();
                if (world == null){
                    InformativeLogger.debugMessage("Client Networking", "Failed to get block world");
                    return;
                }
                blockEntity = world.getBlockEntity(position);
                InformativeLogger.debugMessage("Client Networking", blockEntity);
                if (blockEntity == null){
                    InformativeLogger.debugMessage("Client Networking", "Failed to get block entity");
                    return;
                }
            }catch (Exception e){
                InformativeLogger.error("Client Networking", "Failed to get block entity!");
                InformativeLogger.error("Client Networking", e);
                return;
            }
           client.execute(()->{
               String stringData;
               try {
                   stringData = buf.readString();
                   if (stringData == null){
                       return;
                   }
               } catch (Exception e) {
                   InformativeLogger.error("Client Networking", "Failed to get string data from server!");
                   InformativeLogger.error("Client Networking", e);
                   return;
               }
               try {
                   InformativeLogger.debugMessage("Client Networking", stringData);
                   var parsedNbt = StringNbtReader.parse(stringData);
                   InformativeLogger.debugMessage("Client Networking", parsedNbt);
                   if (parsedNbt == null){
                       InformativeLogger.debugMessage("Client Networking", "Failed to write data");
                       return;
                   }
                   blockEntity.readNbt(parsedNbt);
               } catch (Exception e) {
                   InformativeLogger.error("Client Networking", "Failed to parse NBT Data from server!");
                   InformativeLogger.error("Client Networking", e);
               }
           });
        });
    }

    public static void initializeServerNetworking() {
        ServerPlayNetworking.registerGlobalReceiver(SIT_ON_THE_BENCH_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            Entity entity;
            try {
                entity = player.getWorld().getEntityById(buf.readInt());
            } catch (Exception exception) {
                InformativeLogger.error("LG Server Packet", "Cannot read entity ID from a buffer!");
                InformativeLogger.error("LG Server Packet", exception);
                return;
            }
            server.execute(() -> player.startRiding(entity));
        });
        ServerPlayNetworking.registerGlobalReceiver(LosGallysPropsNetworking.STYLE_CHANGE_PACKET_ID, (server, player, handler, buf, responseSender) -> {
            BlockPos pos;
            int style;
            try {
                InformativeLogger.debugMessage("LG Server Packet", "Reading Block Pos");
                pos = buf.readBlockPos();
                InformativeLogger.debugMessage("LG Server Packet", "Reading Style");
                style = buf.readInt();
            } catch (Exception exception) {
                InformativeLogger.error("LG Server Packet", "Cannot read BlockPos or Style from buffer!");
                InformativeLogger.error("LG Server Packet", exception);
                return;
            }

            server.execute(() -> {
                InformativeLogger.debugMessage("LG Server Packet", "Executing server-side style change");
                ServerWorld world = (ServerWorld) player.getWorld();
                BlockState state = world.getBlockState(pos);
                if (state.getBlock() instanceof StyledBlock) {
//                    world.setBlockState(pos, state.with(((StyledBlock) state.getBlock()).getIntProperty(), style));
                    ((StyledBlock) world.getBlockState(pos).getBlock()).changeStyle(style, world, pos);
                } else
                    InformativeLogger.informativeLog(InformativeLogger.WARN, "LosGallysProps Networking", "Block, given to packet isn't a styled block.",
                            """
                                    Something went wrong, and if you see this message as a user, please contact the developer.\s
                                    Sending logs might help dev to understand what is gone wrong.\s
                                    Additional info:\s""",
                            new InformativeLogger.LogItem("Player:", player),
                            new InformativeLogger.LogItem("BlockPos:", pos),
                            new InformativeLogger.LogItem("Block:", state.getBlock()),
                            new InformativeLogger.LogItem("Targeted Style:", style)
                    );
            });
        });
    }
}
