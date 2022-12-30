package me.themiggergames.losgallysprops.gui.trafficlight;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrafficLightPhaseSprite extends WPlainPanel {

    WLabel label;
    WButton button;
    WGridPanel root = new WGridPanel();
    TextureIcon icon;

    public TrafficLightPhaseSprite(World world, BlockState state, BlockPos pos, int n){
        root.add(button, 1, 1);
        button.setSize(1,1);
        button.setIcon(icon);

        root.add(label, 2, 1);

        root.add(new WLabel(Text.literal(String.valueOf(n))), 1, 3);


    }

}
