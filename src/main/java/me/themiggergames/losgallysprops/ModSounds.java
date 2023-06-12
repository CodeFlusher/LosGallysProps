package me.themiggergames.losgallysprops;


import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static final Identifier PHONE_SOUND = new Identifier(LosGallysProps.MOD_ID, "phone_sound");
    public static final Identifier ELECTRICITY_SOUND = new Identifier(LosGallysProps.MOD_ID, "electricity_sound");
    public static final Identifier METAKOM_SOUND = new Identifier(LosGallysProps.MOD_ID, "metakom_sound");
    public static final Identifier LORE_SOUND = new Identifier(LosGallysProps.MOD_ID, "lore");
    public static final Identifier FUSE_BOX_DOOR_OPEN = new Identifier(LosGallysProps.MOD_ID, "fuse_box_door_open");
    public static final Identifier FUSE_BOX_DOOR_CLOSE = new Identifier(LosGallysProps.MOD_ID, "fuse_box_door_close");
    public static final Identifier FUSE_BOX_CLICK_ON = new Identifier(LosGallysProps.MOD_ID, "fuse_box_click_on");
    public static final Identifier FUSE_BOX_CLICK_OFF = new Identifier(LosGallysProps.MOD_ID, "fuse_box_click_off");
    public static final Identifier WTF = new Identifier(LosGallysProps.MOD_ID, "wtf");
    public static final Identifier LIGHT_BLINKING = new Identifier(LosGallysProps.MOD_ID, "lantern_blinking");
    public static SoundEvent LORE_SOUND_EVENT = new SoundEvent(LORE_SOUND);
    public static SoundEvent METAKOM_SOUND_EVENT = new SoundEvent(METAKOM_SOUND);
    public static SoundEvent PHONE_SOUND_EVENT = new SoundEvent(PHONE_SOUND);
    public static SoundEvent ELECTRICITY_EVENT = new SoundEvent(ELECTRICITY_SOUND);
    public static SoundEvent FUSE_BOX_DOOR_OPEN_EVENT = new SoundEvent(FUSE_BOX_DOOR_OPEN);
    public static SoundEvent FUSE_BOX_DOOR_CLOSE_EVENT = new SoundEvent(FUSE_BOX_DOOR_CLOSE);
    public static SoundEvent FUSE_BOX_CLICK_ON_EVENT = new SoundEvent(FUSE_BOX_CLICK_ON);
    public static SoundEvent FUSE_BOX_CLICK_OFF_EVENT = new SoundEvent(FUSE_BOX_CLICK_OFF);
    public static SoundEvent LIGHT_BLINK_EVENT = new SoundEvent(LIGHT_BLINKING);
    public static SoundEvent WTF_EVENT = new SoundEvent(WTF);

    public static void registerSounds() {
        InformativeLogger.info("Registering sounds for " + LosGallysProps.MOD_ID);
        Registry.register(Registry.SOUND_EVENT, PHONE_SOUND, PHONE_SOUND_EVENT);
        Registry.register(Registry.SOUND_EVENT, ELECTRICITY_SOUND, ELECTRICITY_EVENT);
        Registry.register(Registry.SOUND_EVENT, METAKOM_SOUND, METAKOM_SOUND_EVENT);
        Registry.register(Registry.SOUND_EVENT, LORE_SOUND, LORE_SOUND_EVENT);
        Registry.register(Registry.SOUND_EVENT, FUSE_BOX_DOOR_OPEN, FUSE_BOX_DOOR_OPEN_EVENT);
        Registry.register(Registry.SOUND_EVENT, FUSE_BOX_DOOR_CLOSE, FUSE_BOX_DOOR_CLOSE_EVENT);
        Registry.register(Registry.SOUND_EVENT, FUSE_BOX_CLICK_ON, FUSE_BOX_CLICK_ON_EVENT);
        Registry.register(Registry.SOUND_EVENT, FUSE_BOX_CLICK_OFF, FUSE_BOX_CLICK_OFF_EVENT);
        Registry.register(Registry.SOUND_EVENT, LIGHT_BLINKING, LIGHT_BLINK_EVENT);
        Registry.register(Registry.SOUND_EVENT, WTF, WTF_EVENT);
    }

}
