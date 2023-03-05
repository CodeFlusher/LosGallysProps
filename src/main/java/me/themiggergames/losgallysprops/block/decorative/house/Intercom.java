package me.themiggergames.losgallysprops.block.decorative.house;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;


public class Intercom extends HorizontalFacingBlock {
        SoundEvent soundEvent;

        public static BooleanProperty ISPOWERED = BooleanProperty.of("ispowered");

        SymmetricVoxelShapeController controller;
        public Intercom(Settings settings, SoundEvent sound, SymmetricVoxelShapeController controller) {
            super(settings);
            DebugLogger.sendMessage(this.getClass().getName()+" Init");
            soundEvent = sound;
            this.controller = controller;
            setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(ISPOWERED,false));
        }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(ISPOWERED);
    }


    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
            Direction dir = state.get(FACING);
            return controller.getVoxelOutline(dir);
        }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity placedBy, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            if(!blockState.get(ISPOWERED))
                onPowered(blockState, world, blockPos);
            world.emitGameEvent(placedBy, GameEvent.BLOCK_DEACTIVATE, blockPos);

            world.playSound(null, blockPos, soundEvent, SoundCategory.BLOCKS, 1f, 1f);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
            return state.get(ISPOWERED) ? 15 : 0;
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
            DebugLogger.sendMessage("Current Block Facing " + state.get(FACING));
            DebugLogger.sendMessage("Direction "+direction.asString());
        return (Boolean)state.get(ISPOWERED) && state.get(FACING) == direction ? 15 : 0;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
            DebugLogger.sendMessage("Block Updated");
            if(state.get(ISPOWERED)){
                DebugLogger.sendMessage("Block is powered");
                this.updateNeighbors(state,world,pos);
            }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    public void onPowered(BlockState state, World world, BlockPos pos){
        world.setBlockState(pos, (BlockState)state.with(ISPOWERED, true));
        this.updateNeighbors(state,world,pos);
        world.createAndScheduleBlockTick(pos, this, 20);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(state.get(FACING).getOpposite()), this);
    }
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if ((Boolean)state.get(ISPOWERED)) {
            world.setBlockState(pos, (BlockState)state.with(ISPOWERED, false), 3);
            this.updateNeighbors(state,world,pos);
            world.emitGameEvent((Entity)null, GameEvent.BLOCK_DEACTIVATE, pos);
        }
    }
}
