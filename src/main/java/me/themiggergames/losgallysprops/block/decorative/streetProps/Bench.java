package me.themiggergames.losgallysprops.block.decorative.streetProps;

import me.themiggergames.losgallysprops.LosGallysPropsNetworking;
import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.block.decorative.streetProps.entities.BenchEntity;
import me.themiggergames.losgallysprops.util.*;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Bench extends HorizontalFacingBlock{

    public static final BooleanProperty leftConnection = BooleanProperty.of("left");
    public static final BooleanProperty rightConnection = BooleanProperty.of("right");
    public static final DirectionalVoxelShapeCombiner combiner = new DirectionalVoxelShapeCombiner(
            new SymmetricVoxelShapeController(0.6f, 0.2f, 0.5f, 0.2f, 0, 0f),
            BlockConnactable.ConnectionTypes.HORISONTAL_ONLY,
            VoxelShapes.cuboid(0.2f, 0f, 0.2f, 0.8f, 0.5f, 0.8f)
    );

    public Bench(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(leftConnection, false).with(rightConnection, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        ArrayList<Direction> directions = new ArrayList<>() {
            {
                if (state.get(leftConnection))
                    add(ModMath.rotateDirection(state.get(FACING), true));
                if (state.get(rightConnection))
                    add(ModMath.rotateDirection(state.get(FACING), false));
            }
        };
        return combiner.getShape(directions);
        //return VoxelShapes.cuboid(0.3f,0,0.3f,0.7f,0.7f,0.7f);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        var entity = new BenchEntity(ModBlockEntities.BENCH_ENTITY, world);
        entity.setPos(pos.getX() + 0.5, pos.getY()+ 0.8,pos.getZ()+ 0.5);
        world.spawnEntity(entity);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(rightConnection, canConnect(ctx.getBlockPos().offset(ModMath.rotateDirection(ctx.getHorizontalPlayerFacing().getOpposite(), false)), ctx.getWorld(), getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())))
                .with(leftConnection, canConnect(ctx.getBlockPos().offset(ModMath.rotateDirection(ctx.getHorizontalPlayerFacing().getOpposite(), true)), ctx.getWorld(), getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite())));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return state.with(rightConnection, canConnect(pos.offset(ModMath.rotateDirection(state.get(FACING), false)), (World) world, state))
                .with(leftConnection, canConnect(pos.offset(ModMath.rotateDirection(state.get(FACING), true)), (World) world, state));
    }

    private Boolean canConnect(BlockPos pos, World world, BlockState selfState) {
        if (!(world.getBlockState(pos).getBlock() instanceof Bench)) {
            return false;
        }
        return world.getBlockState(pos).get(FACING) == selfState.get(FACING);
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        var entities = world.getEntitiesByClass(BenchEntity.class, new Box(pos.getX() + 0.5 - 0.25, pos.getY() + 0.8 - 0.25, pos.getZ() + 0.5 - 0.25, pos.getX()+ 0.5 + 0.25, pos.getY() + 0.8 + 0.25, pos.getZ() + 0.5 + 0.25), entity1 -> entity1 instanceof BenchEntity);

        if(entities.isEmpty()){
            InformativeLogger.warn("Bench", "Failed to find any suitable entities within range to remove");
            return;
        }
        entities.forEach(entity -> {
            entity.removeAllPassengers();
            entity.remove(Entity.RemovalReason.DISCARDED);
        });
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient)
            return super.onUse(state,world,pos,player,hand,hit);
        var entities = world.getEntitiesByClass(BenchEntity.class, new Box(pos.getX() + 0.5 - 0.25, pos.getY() + 0.8 - 0.25, pos.getZ() + 0.5 - 0.25, pos.getX()+ 0.5 + 0.25, pos.getY() + 0.8 + 0.25, pos.getZ() + 0.5 + 0.25), entity1 -> entity1 instanceof BenchEntity);
        if(entities.isEmpty()){
            InformativeLogger.warn("Bench", "Failed to find any suitable entities for sitting");
            return ActionResult.CONSUME;
        }
        var entity = entities.get(0);
        if(!entity.getPassengerList().isEmpty()){
            return ActionResult.CONSUME;
        }
        var buffer = PacketByteBufs.create();
        buffer.writeInt(entity.getId());
        ClientPlayNetworking.send(LosGallysPropsNetworking.SIT_ON_THE_BENCH_PACKET_ID, buffer);
//        player.startRiding(entities.get(0));
//        player.stopRiding();
        return ActionResult.SUCCESS;

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(leftConnection);
        builder.add(rightConnection);
        builder.add(FACING);
    }

}
