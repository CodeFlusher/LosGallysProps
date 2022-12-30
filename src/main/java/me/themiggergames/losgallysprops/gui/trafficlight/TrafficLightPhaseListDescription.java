package me.themiggergames.losgallysprops.gui.trafficlight;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public class TrafficLightPhaseListDescription extends LightweightGuiDescription {

    WGridPanel root = new WGridPanel();

    ArrayList<String> list = new ArrayList<>();


    BiConsumer<String, TrafficLightPhaseSprite> configurator = (String s, TrafficLightPhaseSprite sprite)->{
        sprite.button.setOnClick(() -> {

        });
    };


    public TrafficLightPhaseListDescription(World world, BlockState state, BlockPos pos){
        root.setSize(400, 400);
        setRootPanel(root);

        //WListPanel<String, TrafficLightPhaseSprite> wListPanel = new WListPanel<>();

    }



}
