package me.themiggergames.losgallysprops.gui.trafficlight;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import me.themiggergames.losgallysprops.LosGallysProps;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;

public class TrafficLightPhaseSprite extends WPlainPanel {

    public TrafficLightPhaseSprite(World world, BlockState state, BlockPos currentpos,  Integer number){
        ItemIcon icon = new ItemIcon(ModItems.CONFIGURATIOR);
        WButton button = new WButton();
        button = button.setLabel(Text.translatable("ui.losgallysprops.trafficlight.statementmanager"));
        this.add(button, 6, 1, 18*18, 18);
        WLabel label = new WLabel(Text.literal(String.valueOf(number+1)));
        label.setColor(0x000000).setDarkmodeColor(0xFFFFFF);
        label.setSize(4,4);
        label.setHorizontalAlignment(HorizontalAlignment.CENTER);
        label.setVerticalAlignment(VerticalAlignment.CENTER);
        this.add(label, 18*2,1);
        button.setIcon(icon);
        button.setOnClick(() -> {
            DebugLogger.sendMessage("Click Spotted, trying to open screen");
            DebugLogger.sendMessage(String.valueOf(world));
            DebugLogger.sendMessage(String.valueOf(state));
            DebugLogger.sendMessage(String.valueOf(currentpos));
            DebugLogger.sendMessage(String.valueOf(number));
            MinecraftClient.getInstance().setScreen(new TrafficLightScreen(new TrafficLightPhaseEditorDescription(world, state, currentpos, number)));
        });
    }

}
