package me.themiggergames.losgallysprops.gui.stylegui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StyleGUIDDescription extends LightweightGuiDescription {
    WGridPanel root = new WGridPanel();
    World world;
    BlockState state;
    BlockPos pos;

    Block block;

    public StyleGUIDDescription(World world, BlockState state, BlockPos pos, int maxStyle){
        if(maxStyle>9){
            MinecraftClient.getInstance().player.sendMessage(Text.translatable("ui.losgallysprops.style.toomanystyles"), true);
            return;
        }
        this.world = world;
        this.state = state;
        this.pos = pos;
        this.block = world.getBlockState(pos).getBlock();
        root.setSize(216, 36+18*(maxStyle+1));

        this.setRootPanel(root);
        for(int i = 0; i<=maxStyle; i++){
            addStyleButton(i);
        }
    }

    public void addStyleButton(int n){
        WButton button = new WButton();
        button.setOnClick(() -> {
            if(block instanceof StyledBlock) {
                world.setBlockState(pos, world.getBlockState(pos).with(((StyledBlock) block).getIntProperty(), n));
            }
        });
        button.setLabel(Text.translatable("ui.losgallysprops.style.style"+n));
        root.add(button, 1, 1+n, 10, 1);
    }

}
