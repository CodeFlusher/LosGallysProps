package me.themiggergames.losgallysprops.items.customitems;

import com.eliotlash.mclib.math.functions.limit.Min;
import me.themiggergames.losgallysprops.gui.stylegui.StyleGUIDDescription;
import me.themiggergames.losgallysprops.gui.stylegui.StyleGUIScreen;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StyleEditor extends Item {
    public StyleEditor(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if(!context.getPlayer().isCreative()){
            return ActionResult.SUCCESS;
        }
        if(block instanceof StyledBlock){
            MinecraftClient.getInstance().setScreen(new StyleGUIScreen(new StyleGUIDDescription(context.getWorld(), context.getWorld().getBlockState(context.getBlockPos()), context.getBlockPos(), ((StyledBlock) block).getMaxStyle())));
        }else{
            context.getPlayer().sendMessage(Text.translatable("ui.losgallysprops.style.invalidblock"),true);
        }
        return ActionResult.SUCCESS;
    }


}
