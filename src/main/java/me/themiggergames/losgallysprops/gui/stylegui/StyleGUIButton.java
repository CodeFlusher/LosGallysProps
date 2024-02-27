package me.themiggergames.losgallysprops.gui.stylegui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.minecraft.text.Text;

import java.awt.*;

public class StyleGUIButton extends WPlainPanel {
    WButton button = new WButton();
    WLabel label = new WLabel(Text.literal("123"));

    public StyleGUIButton() {
        this.setSize(18 * 10, 18);
        button.setSize(18 * 10, 18);
        this.add(button, 1, 1, 18 * 10, 18);
        this.add(label, 9, 2);
        label.setVerticalAlignment(VerticalAlignment.CENTER);
        label.setColor(Color.WHITE.getRGB());
    }
}
