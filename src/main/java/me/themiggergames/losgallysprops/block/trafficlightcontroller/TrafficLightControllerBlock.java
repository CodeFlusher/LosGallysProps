package me.themiggergames.losgallysprops.block.trafficlightcontroller;

import me.themiggergames.losgallysprops.block.BlockRotatable;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
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

public class TrafficLightControllerBlock extends BlockRotatable implements BlockEntityProvider, NamedScreenHandlerFactory {

    public TrafficLightControllerBlock(Settings settings) {
        super(settings, true);

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
