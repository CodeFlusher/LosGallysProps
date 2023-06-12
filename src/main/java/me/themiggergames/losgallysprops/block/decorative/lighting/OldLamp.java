package me.themiggergames.losgallysprops.block.decorative.lighting;

import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.util.InformativeLogger;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;

public class OldLamp extends WallLightBlock {


    public static final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.8f, 0.2f, 0.4f, 0.1f,0.3f, 0.8f);
    public OldLamp(AbstractBlock.Settings settings) {
        super(settings.noCollision());
    }

    @Override
    public void onEntityLand(BlockView blockView, Entity entity) {
        InformativeLogger.debugMessage("Old Lamp", "onEntityLand");
        if (canThisEntityBreakLight(entity)){
            BlockPos pos = entity.getBlockPos();
            BlockState state = blockView.getBlockState(pos);
            World world = entity.getWorld();
            world.playSound(null, pos, ModSounds.LIGHT_BLINK_EVENT, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(STYLES, 1));
            world.createAndScheduleBlockTick(pos, this, 60, TickPriority.LOW);
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        InformativeLogger.debugMessage("Old Lamp", "onEntityCollision");
        if (canThisEntityBreakLight(entity)){
            world.playSound(null, pos, ModSounds.LIGHT_BLINK_EVENT, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(pos, state.with(STYLES, 1));
            world.createAndScheduleBlockTick(pos, this, 60, TickPriority.LOW);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return controller.getVoxelOutline(state.get(FACING));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(STYLES);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(STYLES) == 1){
            world.setBlockState(pos, state.with(STYLES, 0));
        }
    }

}
