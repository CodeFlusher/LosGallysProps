package me.themiggergames.losgallysprops.items.customitems;

import me.themiggergames.losgallysprops.LosGallysPropsNetworking;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.gui.stylegui.StyleGUIDDescription;
import me.themiggergames.losgallysprops.gui.stylegui.StyleGUIScreen;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SwitchListener;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class StyleEditor extends Item {
    public StyleEditor(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient())
            return super.useOnBlock(context);
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if(context.getPlayer() == null){
            DebugLogger.sendMessage("Player is null.");
            return ActionResult.FAIL;
        }
        if(!context.getPlayer().isCreative()){
            DebugLogger.sendMessage("Player must be in creative!");
            return ActionResult.SUCCESS;
        }
        if(block instanceof StyledBlock){
            DebugLogger.sendMessage("Block has styles.");
            SwitchListener listener = new SwitchListener(new SwitchListener.OnSwitchListener() {
                {
                    DebugLogger.sendMessage("Initializing Switch");
                }
                @Override
                public void onSwitch(Object input) {
                    DebugLogger.sendMessage("Switching to " + input);
                    DebugLogger.sendMessage("world.isClient() = " + world.isClient());
                    //world.setBlockState(pos, world.getBlockState(pos).with(((StyledBlock) block).getIntProperty(), (int) input), Block.NOTIFY_ALL);
                    PacketByteBuf buf = PacketByteBufs.create();
                    PacketByteBuf buf1 = PacketByteBufs.empty();
                    buf.writeBlockPos(pos);
                    buf.writeInt((int)input);
                    ClientPlayNetworking.send(LosGallysPropsNetworking.STYLE_CHANGE_PACKED_ID, buf);
                    //world.setBlockState(pos, state.with(((StyledBlock) block).getIntProperty(),(int)input));
                }
            });

            setCustomScreen(world, state, pos, listener);
        }else{
            context.getPlayer().sendMessage(Text.translatable("ui.losgallysprops.style.invalidblock"),true);
        }
        return ActionResult.SUCCESS;
    }

    @Environment(EnvType.CLIENT)
    public void setCustomScreen(WorldAccess world, BlockState state, BlockPos pos, SwitchListener listener){
        DebugLogger.sendMessage("Screen opened.");
            MinecraftClient.getInstance().setScreen(new StyleGUIScreen(new StyleGUIDDescription(world,
                        state, pos,
                        ((StyledBlock) state.getBlock()).getMaxStyle(),
                        listener
                    )
                )
            );
    }
}
