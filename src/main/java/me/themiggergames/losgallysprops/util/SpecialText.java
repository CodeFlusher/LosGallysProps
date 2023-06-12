package me.themiggergames.losgallysprops.util;

import net.minecraft.text.Text;

public class SpecialText {
    Text text;
    Integer i;

    public SpecialText(Text text, Integer i) {
        this.i = i;
        this.text = text;
    }

    public Text getText() {
        return text;
    }

    public Integer getNumber() {
        return i;
    }
}
