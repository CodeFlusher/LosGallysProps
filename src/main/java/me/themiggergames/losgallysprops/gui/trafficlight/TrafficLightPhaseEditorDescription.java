package me.themiggergames.losgallysprops.gui.trafficlight;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import me.themiggergames.losgallysprops.block.ModBlocks;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightBlock;
import me.themiggergames.losgallysprops.block.decorative.streetProps.trafficlight.TrafficLightEntity;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TrafficLightPhaseEditorDescription extends LightweightGuiDescription {

    private static Integer nPhase;
    private final int sharedStatementButtonWidth = 200;
    private final int sharedStatementButtonHeight = 20;
    private WGridPanel root = new WGridPanel();
    public TrafficLightPhaseEditorDescription(World world, BlockState state, BlockPos pos, int nPhase){

        this.nPhase = nPhase;

        setRootPanel(root);
        root.setSize(256, 162);

        for(int i = 0; i<6; i++){
            DebugLogger.sendMessage(String.valueOf(world));
            DebugLogger.sendMessage(String.valueOf(state));
            DebugLogger.sendMessage(String.valueOf(pos));
            DebugLogger.sendMessage(String.valueOf(i));
            DebugLogger.sendMessage(String.valueOf(nPhase));
            addChangerLine(world, state, pos, i);
        }

    }

    protected void addChangerLine(World world, BlockState state, BlockPos pos, int n){

        WLabel label = new WLabel(Text.translatable("ui.losgallysprops.trafficlight.setstatement"+n));

        WButton button = new WButton();

        root.add(label, 2,1+n);

        button.setIcon(new ItemIcon(new ItemStack(ModBlocks.MODERN_TRAFFIC_LIGHT)));
        button.setSize(sharedStatementButtonWidth,sharedStatementButtonHeight);
        root.add(button, 1,1+n);

        button.setOnClick(() -> {
            setPhasePreset(n, world, pos);
            //world.setBlockState(pos, state.with(TrafficLightBlock.MODE, n));
        });
    }
    public static void setPhasePreset(int n, World world, BlockPos pos){

        DebugLogger.sendMessage("Phase Preset Change Triggered");

        if(world.getBlockEntity(pos) instanceof TrafficLightEntity){
            DebugLogger.sendMessage("Changing Statements");
            ((TrafficLightEntity) world.getBlockEntity(pos)).commitPhaseChanges(nPhase,n);
            NbtCompound nbt = new NbtCompound();
            ((TrafficLightEntity) world.getBlockEntity(pos)).writeNbt(nbt);
        }

    }
}
