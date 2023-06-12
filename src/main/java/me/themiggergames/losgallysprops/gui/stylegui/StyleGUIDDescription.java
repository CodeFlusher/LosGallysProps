package me.themiggergames.losgallysprops.gui.stylegui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.SpecialText;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SwitchListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class StyleGUIDDescription extends LightweightGuiDescription {
    WGridPanel root = new WGridPanel();
    WorldAccess world;
    BlockState state;
    BlockPos pos;
    Block block;
    Boolean unstandardTitle;
    ArrayList<Text> titles;
    SwitchListener listener;

    BiConsumer<SpecialText, StyleGUIButton> configurator = (SpecialText text, StyleGUIButton destination) -> {
        InformativeLogger.debugMessage(text.getText().asOrderedText());
        destination.button.setLabel(text.getText());
        destination.button.setOnClick(() -> {
            listener.setSwitchable(text.getNumber());
            //world.setBlockState(pos, world.getBlockState(pos).with(((StyledBlock)block).getIntProperty(), text.getNumber()), Block.NOTIFY_ALL);
        });
        if (!this.unstandardTitle) {
            destination.label.setText(Text.literal(String.valueOf(text.getNumber())));
        } else {
            destination.label.setText(Text.literal(""));
        }
    };

    public StyleGUIDDescription(WorldAccess world, BlockState state, BlockPos pos, int maxStyle, SwitchListener listener) {
        this.listener = listener;
        this.unstandardTitle = ((StyledBlock) state.getBlock()).usuesUnstandartTiteling();
        if (unstandardTitle)
            this.titles = ((StyledBlock) state.getBlock()).getTitlesList();
        this.world = world;
        this.state = state;
        this.pos = pos;
        this.block = world.getBlockState(pos).getBlock();
        root.setSize(36 + 18 * 11, 36 + 18 * 6);

        root.add(new WLabel(Text.translatable("item.losgallysprops.style_editor")), 1, 1);

        this.setRootPanel(root);
        WListPanel<SpecialText, StyleGUIButton> listPanel;
        ArrayList<SpecialText> list = new ArrayList<SpecialText>();
        if (this.unstandardTitle) {
            for (int i = 0; i < maxStyle; i++) {
                list.add(new SpecialText(titles.get(i), i));
            }
        } else {
            for (int i = 0; i < maxStyle; i++) {
                list.add(new SpecialText(Text.translatable("ui.losgallysprops.style.style"), i));
            }
        }
        listPanel = new WListPanel<>(list, StyleGUIButton::new, configurator);
        listPanel.setListItemHeight(18);
        root.add(new WLabel(Text.translatable("ui.losgallysprops.style.available_styles")), 1, 2);
        root.add(listPanel, 1, 3, 11, 6);
    }

    public void addStyleButton(int n) {
        WButton button = new WButton();
        button.setOnClick(() -> {
            if (block instanceof StyledBlock) {
                world.setBlockState(pos, world.getBlockState(pos).with(((StyledBlock) block).getIntProperty(), n), Block.NOTIFY_ALL);
            }
        });
        if (!this.unstandardTitle)
            button.setLabel(Text.translatable("ui.losgallysprops.style.style" + n));
        else
            button.setLabel(this.titles.get(n));
        root.add(button, 1, 1 + n, 10, 1);
    }

}
