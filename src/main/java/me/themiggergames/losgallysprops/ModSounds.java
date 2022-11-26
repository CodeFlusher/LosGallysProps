package me.themiggergames.losgallysprops;


import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
public class ModSounds {

    public static final Identifier PHONE_SOUND = new Identifier("losgallysprops:phone_sound");
    public static final Identifier ELECTRICITY_SOUND = new Identifier("losgallysprops:electricity_sound");
    public static final Identifier METAKOM_SOUND = new Identifier("losgallysprops:metakom_sound");
    public static final Identifier LORE_SOUND = new Identifier("losgallysprops:lore");
    public static SoundEvent LORE_SOUND_EVENT = new SoundEvent(LORE_SOUND);
    public static SoundEvent METAKOM_SOUND_EVENT = new SoundEvent(METAKOM_SOUND);
    public static SoundEvent PHONE_SOUND_EVENT = new SoundEvent(PHONE_SOUND);
    public static SoundEvent ELECTRICITY_EVENT = new SoundEvent(ELECTRICITY_SOUND);

    public static void registerSounds () {
        LosGallysProps.LOGGER.info("Registering sounds for " + LosGallysProps.MOD_ID);
        Registry.register(Registry.SOUND_EVENT, PHONE_SOUND, PHONE_SOUND_EVENT);
        Registry.register(Registry.SOUND_EVENT, ELECTRICITY_SOUND, ELECTRICITY_EVENT);
        Registry.register(Registry.SOUND_EVENT, METAKOM_SOUND, METAKOM_SOUND_EVENT);
        Registry.register(Registry.SOUND_EVENT, LORE_SOUND, LORE_SOUND_EVENT);

    }

}
