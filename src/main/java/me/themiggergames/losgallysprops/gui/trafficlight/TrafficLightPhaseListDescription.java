package me.themiggergames.losgallysprops.gui.trafficlight;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrafficLightPhaseListDescription extends LightweightGuiDescription {

    WGridPanel root = new WGridPanel();

    public TrafficLightPhaseListDescription(World world, BlockState state, BlockPos pos){
        root.setSize(360, 400);
        setRootPanel(root);

        for(int i = 0; i<10; i++){
            DebugLogger.sendMessage(String.valueOf(world));
            DebugLogger.sendMessage(String.valueOf(state));
            DebugLogger.sendMessage(String.valueOf(pos));
            DebugLogger.sendMessage(String.valueOf(i));
            root.add(new TrafficLightPhaseSprite(world, state, pos, i), 1, i+1);
        }

        //WListPanel<String, TrafficLightPhaseSprite> wListPanel = new WListPanel<>();

    }


}
