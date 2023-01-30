package me.themiggergames.losgallysprops.block.decorative.cctv;

import me.themiggergames.losgallysprops.block.ModBlockEntities;
import me.themiggergames.losgallysprops.debugtools.DebugLogger;
import me.themiggergames.losgallysprops.items.ModItems;
import me.themiggergames.losgallysprops.util.PlacingTypes;
import me.themiggergames.losgallysprops.util.SymmetricVoxelShapeController;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Random;

public class CCTVBlock extends HorizontalFacingBlock implements BlockEntityProvider {

    public static final Integer MAX_STYLE_COUNT = 1;
    public static final EnumProperty<PlacingTypes> TYPES = EnumProperty.of("placedon",PlacingTypes.class);
    public static final IntProperty STYLE = IntProperty.of("style", 0, 1);
    public final SymmetricVoxelShapeController controller = new SymmetricVoxelShapeController(0.4f,0.6f, 0.4f,0.3f, 0.3f, 0f);
    private Integer styleType;

    public CCTVBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(STYLE,0));
        this.styleType = 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STYLE);
        builder.add(TYPES);
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        if(ctx.getSide() != Direction.UP && ctx.getSide() != Direction.DOWN) {
            return getDefaultState().with(STYLE, styleType).with(FACING, ctx.getSide().getOpposite()).with(TYPES, PlacingTypes.getPlacement(ctx.getPlayerLookDirection()));
        }else{
            return getDefaultState().with(STYLE, styleType).with(FACING, ctx.getPlayerFacing()).with(TYPES,PlacingTypes.getPlacement(ctx.getPlayerLookDirection()));
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!world.isClient) {
            if (player.getStackInHand(hand).getItem() == ModItems.CONFIGURATIOR) {
                world.setBlockState(pos, state.with(STYLE, Objects.equals(state.get(STYLE), MAX_STYLE_COUNT) ? 0 : state.get(STYLE)+1));
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(TYPES) == PlacingTypes.WALL)
            return controller.getVoxelOutline(state.get(FACING));
        else if (state.get(TYPES) == PlacingTypes.TOP) {
            return controller.getVoxelOutline(Direction.UP);
        }else{
            return controller.getVoxelOutline(Direction.DOWN);
        }
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CCTVEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

}
