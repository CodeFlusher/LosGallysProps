package me.themiggergames.losgallysprops;


import me.themiggergames.losgallysprops.util.InformativeLogger;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

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
    public static SoundEvent LORE_SOUND_EVENT = SoundEvent.of(LORE_SOUND);
    public static SoundEvent METAKOM_SOUND_EVENT = SoundEvent.of(METAKOM_SOUND);
    public static SoundEvent PHONE_SOUND_EVENT = SoundEvent.of(PHONE_SOUND);
    public static SoundEvent ELECTRICITY_EVENT = SoundEvent.of(ELECTRICITY_SOUND);
    public static SoundEvent FUSE_BOX_DOOR_OPEN_EVENT = SoundEvent.of(FUSE_BOX_DOOR_OPEN);
    public static SoundEvent FUSE_BOX_DOOR_CLOSE_EVENT = SoundEvent.of(FUSE_BOX_DOOR_CLOSE);
    public static SoundEvent FUSE_BOX_CLICK_ON_EVENT = SoundEvent.of(FUSE_BOX_CLICK_ON);
    public static SoundEvent FUSE_BOX_CLICK_OFF_EVENT = SoundEvent.of(FUSE_BOX_CLICK_OFF);
    public static SoundEvent LIGHT_BLINK_EVENT = SoundEvent.of(LIGHT_BLINKING);
    public static SoundEvent WTF_EVENT = SoundEvent.of(WTF);

    public static void registerSounds() {
        InformativeLogger.info("Registering sounds for " + LosGallysProps.MOD_ID);
        Registry.register(Registries.SOUND_EVENT, PHONE_SOUND, PHONE_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, ELECTRICITY_SOUND, ELECTRICITY_EVENT);
        Registry.register(Registries.SOUND_EVENT, METAKOM_SOUND, METAKOM_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, LORE_SOUND, LORE_SOUND_EVENT);
        Registry.register(Registries.SOUND_EVENT, FUSE_BOX_DOOR_OPEN, FUSE_BOX_DOOR_OPEN_EVENT);
        Registry.register(Registries.SOUND_EVENT, FUSE_BOX_DOOR_CLOSE, FUSE_BOX_DOOR_CLOSE_EVENT);
        Registry.register(Registries.SOUND_EVENT, FUSE_BOX_CLICK_ON, FUSE_BOX_CLICK_ON_EVENT);
        Registry.register(Registries.SOUND_EVENT, FUSE_BOX_CLICK_OFF, FUSE_BOX_CLICK_OFF_EVENT);
        Registry.register(Registries.SOUND_EVENT, LIGHT_BLINKING, LIGHT_BLINK_EVENT);
        Registry.register(Registries.SOUND_EVENT, WTF, WTF_EVENT);
    }

}
