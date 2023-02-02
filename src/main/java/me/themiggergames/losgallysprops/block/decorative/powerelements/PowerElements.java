package me.themiggergames.losgallysprops.block.decorative.powerelements;

import me.themiggergames.losgallysprops.ModSounds;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PowerElements extends HorizontalFacingBlock implements BlockEntityProvider {

    public static final BooleanProperty ISOPEN = BooleanProperty.of("open");
    public static final BooleanProperty ISPOWERED = BooleanProperty.of("powered");
    public static final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.8f, 0.35f, 1, 0.1f, 0, 0.65f);

    public PowerElements(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(ISOPEN, false).with(ISPOWERED, true));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(ISOPEN).add(ISPOWERED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        ctx.getWorld().createAndScheduleBlockTick(ctx.getBlockPos(), this, 20);
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite()).with(ISOPEN, false);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!world.isClient && state.get(ISPOWERED))
            world.playSound(null, pos, ModSounds.ELECTRICITY_EVENT, SoundCategory.BLOCKS, 1f, 1f);
        world.createAndScheduleBlockTick(pos, this, 20);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient ){
            if(player.getStackInHand(hand).getItem() == ModItems.CONFIGURATIOR && state.get(ISOPEN)){
                if(state.get(ISPOWERED)){
                    world.playSound(null, pos, ModSounds.FUSE_BOX_CLICK_OFF_EVENT, SoundCategory.BLOCKS, 1f,1f);
                }else{
                    world.playSound(null, pos, ModSounds.FUSE_BOX_CLICK_ON_EVENT, SoundCategory.BLOCKS, 1f,1f);
                }
                world.setBlockState(pos, state.with(ISPOWERED, !state.get(ISPOWERED)));
            }else{
                if(state.get(ISOPEN)){
                    world.playSound(null, pos, ModSounds.FUSE_BOX_DOOR_CLOSE_EVENT, SoundCategory.BLOCKS, 1f,1f);
                }else{
                    world.playSound(null, pos, ModSounds.FUSE_BOX_DOOR_OPEN_EVENT, SoundCategory.BLOCKS, 1f,1f);
                }
                boolean temp = !state.get(ISOPEN);
                world.setBlockState(pos, state.with(ISOPEN, temp));
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return controller.getVoxelOutline(state.get(FACING));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PowerElementsEntity(pos,state);
    }
}
