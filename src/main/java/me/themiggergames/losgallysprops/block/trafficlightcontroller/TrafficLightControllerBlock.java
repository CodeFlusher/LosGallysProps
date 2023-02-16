package me.themiggergames.losgallysprops.block.trafficlightcontroller;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class TrafficLightControllerBlock extends HorizontalFacingBlock implements BlockRotatable, NamedScreenHandlerFactory {

    public TrafficLightControllerBlock(Settings settings) {
        super(settings);
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, BlockRotatable.getHeadDirection(ctx.getPlayerYaw())).with(ROTATION, BlockRotatable.getRotation(ctx.getPlayerYaw()));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        BlockRotatable.appendRotationProperties(builder);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){
        return VoxelShapes.cuboid(0.2f,0f,0.2f,0.8f,1f,0.8f);
    }

    @Override
    public Text getDisplayName() {
            return Text.translatable(getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }
}
