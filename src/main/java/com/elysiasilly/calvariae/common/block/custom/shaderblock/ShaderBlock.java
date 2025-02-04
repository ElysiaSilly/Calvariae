package com.elysiasilly.calvariae.common.block.custom.shaderblock;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShaderBlock extends BaseEntityBlock {

    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);

    public static final MapCodec<ShaderBlock> CODEC = simpleCodec(ShaderBlock::new);

    public ShaderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ShaderBlockEntity(pos, state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        /*
        if(!level.isClientSide) return InteractionResult.FAIL;
        if(hitResult.getDirection() != Direction.UP) return InteractionResult.FAIL;

        int textureResolution = 16;

        Vec3 hitPos = hitResult.getLocation();

        Vec3 localizedHitPos = hitPos.subtract(pos.getX(), pos.getY(), pos.getZ());



        Vec3 translatedHitPos = new Vec3((int)(localizedHitPos.x * textureResolution) + 1, 0, (int)(localizedHitPos.z * textureResolution) +1);

        //ShaderBlockBERenderer.updatePixel.add(translatedHitPos);

        RGBA colour = new RGBA(255, 255, 255, 0);

        if(player.getMainHandItem().is(Items.BLUE_DYE)) {
            colour = new RGBA(0, 0, 255, 0);
        }
        if(player.getMainHandItem().is(Items.RED_DYE)) {
            colour = new RGBA(255, 0, 0, 0);
        }
        if(player.getMainHandItem().is(Items.GREEN_DYE)) {
            colour = new RGBA(0, 255, 0, 0);
        }

        if(player.isShiftKeyDown()) {
            ShaderBlockBERenderer.pixels.remove(translatedHitPos);
        } else {
            ShaderBlockBERenderer.pixels.put(translatedHitPos, colour);
        }

        //ShaderBlockBERenderer.updatePixel.add(translatedHitPos);// = colour;

        //ShaderBlockBERenderer.drawPixel = translatedHitPos;

        ShaderBlockBERenderer.refreshTexture = true;

        //System.out.println(translatedHitPos);

         */



        //DecimalFormat clean = new DecimalFormat("#.#");
        //Vec3 exactHitPos = new Vec3(Double.parseDouble(clean.format(localizedHitPos.x)), Double.parseDouble(clean.format(localizedHitPos.y)), Double.parseDouble(clean.format(localizedHitPos.z)));

        return InteractionResult.SUCCESS;
    }




}
