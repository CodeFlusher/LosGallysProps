package me.themiggergames.losgallysprops.commands;

import me.themiggergames.losgallysprops.block.decorative.streetProps.entities.BenchEntity;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.server.command.CommandManager.literal;

public class ModCommandManager {
    public static void registerCommands(){
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> dispatcher.register(literal("removeAllBenchEntities").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4)).executes(context -> {
                    context.getSource().getWorld().getEntitiesByType(new TypeFilter<>() {
                        @Nullable
                        @Override
                        public Entity downcast(Entity obj) {
                            return obj;
                        }

                        @Override
                        public Class<? extends Entity> getBaseClass() {
                            return BenchEntity.class;
                        }
                    }, entity -> entity instanceof BenchEntity).forEach(entity -> {
                        InformativeLogger.debugMessage("Removing Entities", entity);
                        entity.removeAllPassengers();
                        entity.remove(Entity.RemovalReason.DISCARDED);
                    });
                    return 0;
                }
        ))));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("readNBTDataIntoChat").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(1)).executes(context -> {
            var player = context.getSource().getPlayer();
            if (player == null){
                return 0;
            }
            var rayCastedBlock = context.getSource().getPlayer().raycast(100f, 1, false);
            var world = context.getSource().getWorld();
            var pos = BlockPos.ofFloored(rayCastedBlock.getPos());
            var block = world.getBlockState(pos).getBlock();
            if (!(block instanceof BlockEntityProvider)){
                player.sendMessage(Text.translatable("chat.losgallysprops.commands.read_nbt.not_a_provider"));
//                player.sendMessage(Text.("Filed to get NBT Data because block isn't a BlockEntityProvider \n" + block));
                return 0;
            }
            var blockEntity = world.getBlockEntity(pos);
            if (blockEntity == null){
                player.sendMessage(Text.translatable("chat.losgallysprops.commands.read_nbt.null"));
//                player.sendMessage(Text.of("Filed to get NBT Data because blockEntity is null \n" + block));
                return 0;
            }
            InformativeLogger.debugMessage("Read Data Command", blockEntity.toString());
            var nbt = blockEntity.createNbt();
            player.sendMessage(Text.of(nbt.asString()));
            return 0;
        })));
    }
}
