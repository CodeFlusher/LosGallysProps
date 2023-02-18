package me.themiggergames.losgallysprops.block.decorative;

import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class TriggerBlock extends Block {

    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public TriggerBlock(Settings settings) {
        super(settings.noCollision());
        DebugLogger.sendMessage(this.getClass().getName()+" Init");
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWERED, false);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        DebugLogger.sendMessage(String.valueOf(world.isPlayerInRange(pos.getX(), pos.getY(), pos.getZ(), 2)));
        if(world.isPlayerInRange(pos.getX(), pos.getY(), pos.getZ(), 2)){
            world.setBlockState(pos, state.with(POWERED, true));
            world.emitGameEvent(world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(),2, false), GameEvent.BLOCK_ACTIVATE, pos);
            this.updateNeighbors(state,world,pos);
        }else{
            world.setBlockState(pos, state.with(POWERED, false));
            world.emitGameEvent(null, GameEvent.BLOCK_DEACTIVATE, pos);
            this.updateNeighbors(state,world,pos);
        }
        world.createAndScheduleBlockTick(pos, state.getBlock(), 4);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //return context.isHolding(this.asBlock().asItem()) ? VoxelShapes.fullCube() : VoxelShapes.empty();
        return VoxelShapes.fullCube();
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }
    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        for(Direction dir : DIRECTIONS){
            world.updateNeighborsAlways(pos.offset(dir), this);
        }
    }

}