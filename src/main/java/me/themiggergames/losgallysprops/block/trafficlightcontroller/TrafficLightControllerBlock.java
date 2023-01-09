package me.themiggergames.losgallysprops.block.trafficlightcontroller;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.BlockRotatable;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TrafficLightControllerBlock extends HorizontalFacingBlock implements BlockRotatable, BlockEntityProvider, NamedScreenHandlerFactory {

    public TrafficLightControllerBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TrafficLightControllerEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        DebugLogger.sendMessage("Click Spotted");
        if(placedBy.getInventory().getMainHandStack().getItem() == ModItems.CONFIGURATIOR){
            DebugLogger.sendMessage("Click Spotted");
            //placedBy.openHandledScreen(blockState.createScreenHandlerFactory(world, blockPos));
            placedBy.sendMessage(Text.translatable("block.losgallysprops.future_update_feature"), true);
        } else {
            placedBy.sendMessage(Text.translatable("block.losgallysprops.future_update_feature"), true);
        }

        return ActionResult.SUCCESS;
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
