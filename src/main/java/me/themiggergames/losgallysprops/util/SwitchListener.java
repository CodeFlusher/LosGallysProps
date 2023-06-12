package me.themiggergames.losgallysprops.util;

public class SwitchListener {

    OnSwitchListener runnable;
    Object switchable;

    public SwitchListener(OnSwitchListener onSwitch) {
        this.runnable = onSwitch;
    }

    public void setSwitchable(Object object) {
        switchable = object;
        runnable.onSwitch(switchable);
    }

    public abstract static class OnSwitchListener {
        public abstract void onSwitch(Object input);
    }

}
