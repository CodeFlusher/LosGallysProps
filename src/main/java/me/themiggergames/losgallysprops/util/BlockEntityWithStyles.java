package me.themiggergames.losgallysprops.util;

public interface BlockEntityWithStyles {
    void setStyleNBT(int newStyle);
    void onChangedNbt();
    Integer getStyleNBT();

}
