package me.themiggergames.losgallysprops.gui.stylegui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import me.themiggergames.losgallysprops.util.SpecialText;
import me.themiggergames.losgallysprops.util.StyledBlock;
import me.themiggergames.losgallysprops.util.SwitchListener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class StyleGUIDDescription extends LightweightGuiDescription {
    private final WGridPanel root = new WGridPanel();
    private final WorldAccess world;
    private final BlockPos pos;
    private final Block block;
    private Boolean substandardTitle;
    private ArrayList<Text> titles;
    private SwitchListener listener;

    BiConsumer<SpecialText, StyleGUIButton> configurator = (SpecialText text, StyleGUIButton destination) -> {
        destination.button.setLabel(text.text());
        destination.button.setOnClick(() -> listener.setSwitchable(text.number()));
        if (!this.substandardTitle) {
            destination.label.setText(Text.literal(String.valueOf(text.number())));
        } else {
            destination.label.setText(Text.literal(""));
        }
    };

    public StyleGUIDDescription(WorldAccess world, BlockState state, BlockPos pos, int maxStyle, SwitchListener listener) {
        this.listener = listener;
        this.substandardTitle = ((StyledBlock) state.getBlock()).substandardTitling();
        if (substandardTitle)
            this.titles = ((StyledBlock) state.getBlock()).getTitlesList();
        this.world = world;
        this.pos = pos;
        this.block = world.getBlockState(pos).getBlock();
        root.setSize(36 + 18 * 11, 36 + 18 * 6);

        root.add(new WLabel(Text.translatable("item.losgallysprops.style_editor")), 1, 1);

        this.setRootPanel(root);
        WListPanel<SpecialText, StyleGUIButton> listPanel = getSpecialTextStyleGUIButtonWListPanel(maxStyle);
        root.add(new WLabel(Text.translatable("ui.losgallysprops.style.available_styles")), 1, 2);
        root.add(listPanel, 1, 3, 11, 6);
    }

    @NotNull
    private WListPanel<SpecialText, StyleGUIButton> getSpecialTextStyleGUIButtonWListPanel(int maxStyle) {
        WListPanel<SpecialText, StyleGUIButton> listPanel;
        ArrayList<SpecialText> list = new ArrayList<>();
        if (this.substandardTitle) {
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
        return listPanel;
    }


}
