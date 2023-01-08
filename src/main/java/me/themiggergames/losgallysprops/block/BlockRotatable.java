package me.themiggergames.losgallysprops.block;


import me.themiggergames.losgallysprops.LosGallysProps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;


public class BlockRotatable extends HorizontalFacingBlock{

    public static final IntProperty rotation = IntProperty.of("rotation",0,3);
    boolean addStatements;

    protected BlockRotatable(Settings settings, boolean useAdditionalStates) {
        super(settings);
        addStatements = useAdditionalStates;
//        setDefaultState(getStateManager().getDefaultState().with(rotation,0).with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(rotation);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        double yaw1 = ctx.getPlayer().getHeadYaw();
        double yaw2 = Math.abs( ctx.getPlayer().getHeadYaw())%90;
        if(addStatements){
            if(LosGallysProps.unifiedMath.isInRange(yaw1, 0, 90,true)){
                if(LosGallysProps.unifiedMath.isInRange(yaw2, 15,35,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(rotation, 1);
                }else if(LosGallysProps.unifiedMath.isInRange(yaw2, 35,55,true)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(rotation, 2);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 55,75,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(rotation, 3);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 0, 15, true)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(rotation, 0);
                } else if (LosGallysProps.unifiedMath.isInRange(yaw2, 75, 0, false)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(rotation, 0);
                }
            }
            else if(LosGallysProps.unifiedMath.isInRange(yaw1, 90, 180,true)){
                if(LosGallysProps.unifiedMath.isInRange(yaw2, 15,35,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(rotation, 1);
                }else if(LosGallysProps.unifiedMath.isInRange(yaw2, 35,55,true)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(rotation, 2);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 55,75,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(rotation, 3);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 0, 15, true)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST).with(rotation, 0);
                } else if (LosGallysProps.unifiedMath.isInRange(yaw2, 75, 0, false)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH).with(rotation, 0);
                }
            }
            else if(LosGallysProps.unifiedMath.isInRange(yaw1, -180, -90,true)){
                if(LosGallysProps.unifiedMath.isInRange(yaw2, 15,35,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH).with(rotation, 3);
                }else if(LosGallysProps.unifiedMath.isInRange(yaw2, 35,55,true)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH).with(rotation, 2);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 55,75,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH).with(rotation, 1);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 0, 15, true)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(rotation, 0);
                } else if (LosGallysProps.unifiedMath.isInRange(yaw2, 75, 90, true)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH).with(rotation, 0);
                }
            }
            else if(LosGallysProps.unifiedMath.isInRange(yaw1, -90, -0,true)){
                if(LosGallysProps.unifiedMath.isInRange(yaw2, 15,35,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(rotation, 3);
                }else if(LosGallysProps.unifiedMath.isInRange(yaw2, 35,55,true)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(rotation, 2);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 55,75,false)){
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(rotation, 1);
                } else if(LosGallysProps.unifiedMath.isInRange(yaw2, 0, 15, true)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(rotation, 0);
                } else if (LosGallysProps.unifiedMath.isInRange(yaw2, 75, 90, true)) {
                    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST).with(rotation, 0);
                }
            }

        }else {
            return this.getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing()).with(rotation,0);
        }
            return this.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(rotation, 0);
    }
}
