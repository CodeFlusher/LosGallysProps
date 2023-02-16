package me.themiggergames.losgallysprops.gui.stylegui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import me.themiggergames.losgallysprops.util.StyledBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

public class StyleGUIButton extends WPlainPanel {
    WButton button = new WButton();
    WLabel label = new WLabel(Text.literal("123"));

    public StyleGUIButton(){
        this.setSize(18*10,18);
        button.setSize(18*10, 18);
        this.add(button, 1, 1, 18*10, 18);
        this.add(label, 9, 2);
        label.setVerticalAlignment(VerticalAlignment.CENTER);
        label.setColor(Color.WHITE.getRGB());
    }
}
