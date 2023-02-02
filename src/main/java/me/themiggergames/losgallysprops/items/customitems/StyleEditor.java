package me.themiggergames.losgallysprops.items.customitems;

import me.themiggergames.losgallysprops.gui.stylegui.StyleGUIDDescription;
import me.themiggergames.losgallysprops.gui.stylegui.StyleGUIScreen;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class StyleEditor extends Item {
    public StyleEditor(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Block block = context.getWorld().getBlockState(context.getBlockPos()).getBlock();
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
