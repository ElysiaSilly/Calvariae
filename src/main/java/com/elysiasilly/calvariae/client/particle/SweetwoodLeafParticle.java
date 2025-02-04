package com.elysiasilly.calvariae.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class SweetwoodLeafParticle extends TextureSheetParticle {

    final SpriteSet sprites;

    final int randomNumberForDirection = level.random.nextIntBetweenInclusive(1, 10);
    final float gravityModifier = level.random.nextIntBetweenInclusive(10, 15) * 0.1f;

    int direction;
    BlockPos logBlockPos;
    BlockState logBlock;
    BlockPos particleBlockPos;

    boolean logHasLeaves;
    int age;

    protected SweetwoodLeafParticle(ClientLevel level, double rX, double rY, double rZ, double x, double y, double z, SpriteSet spriteSet) {
        super(level, rX, rY, rZ);

        this.logBlockPos = new BlockPos((int) x, (int) y, (int) z);
        this.particleBlockPos = new BlockPos((int) this.getPos().x, (int) this.getPos().y, (int) this.getPos().z);
        this.sprites = spriteSet;

        switch (this.randomNumberForDirection) {
            case 1, 2, 3, 4, 5, 6, 7, 8 : this.direction = 1; break; // SIDE
            case 9                      : this.direction = 2; break; // DOWN
            case 10                     : this.direction = 3; break; // UP
        }

        this.gravity = 0.0F;
        this.lifetime = 1;
        this.hasPhysics = false;
        this.quadSize = 1.5F;

        /*
        int fruiting = level.getBlockState(this.pos).getValue(SweetwoodLogBlock.FRUITING) ? 2 : 4;

        switch (this.random) {
            case 1, 2, 3, 4, 5, 6, 7, 8 : this.setSprite(sprites.get(1, fruiting));; break; // SIDE
            case 9                      : this.setSprite(sprites.get(1, 1)); break; // DOWN
            case 10                     : this.setSprite(sprites.get(2, 3));; break; // UP
        }

         */
    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {

        this.logBlock = level.getBlockState(this.logBlockPos);

        int temp = 1;//this.logBlock.getValue(SweetwoodLogBlock.AGE);

        if(temp < this.age) {
            level.levelEvent(2001, this.particleBlockPos, 2047);

            ItemEntity itementity = new ItemEntity(level, this.particleBlockPos.getX(), this.particleBlockPos.getY(), this.particleBlockPos.getZ(), new ItemStack(Items.APPLE));

            //ParticleUtils.spawnParticleBelow(level, this.particleBlockPos, level.random, ParticleTypes.CHERRY_LEAVES);

            //level.addParticle();

            //level.addParticle(ParticleTypes.CHERRY_LEAVES, this.getPos().x, this.getPos().y, this.getPos().z, 1, 1, 1);

            //Bat bat = EntityType.BAT.create(level);
            //bat.moveTo(this.particleBlockPos.getX() + 0.5, this.particleBlockPos.getY(), this.particleBlockPos.getZ() + 0.5, 0.0F, 0.0F);
            //level.addFreshEntity(bat);
            //bat.spawnAnim();

            //level.addFreshEntity(itementity);

            //ServerLevel level2 = level.getServer().overworld();

            //ServerLevel level3 = ServerLifecycleHooks.getCurrentServer().overworld();

            //level.getServer().overworld();
            //if(level3 != null)
            //level3.addFreshEntity(itementity);
        }

        this.age = temp;
        this.logHasLeaves = false; //logBlock.is(CBlocks.SWEETWOOD_LOG.get()) ? this.logBlock.getValue(SweetwoodLogBlock.AGE) > 0 : false;
        this.particleBlockPos = new BlockPos((int) this.getPos().x, (int) this.getPos().y, (int) this.getPos().z);

        if(!this.logHasLeaves) {
            breakLeaves();
        }

        if(!removed) {
            int age = 3;

            switch (this.age) {
                case 2 -> age = 4;
                case 3 -> age = 5;
                case 4 -> age = 6;
            }

            switch (this.direction) {
                case 1 : this.setSprite(sprites.get(age, 6));   break; // SIDE
                case 2 : this.setSprite(sprites.get(2, 6)); break; // DOWN
                case 3 : this.setSprite(sprites.get(1, 6)); break; // UP
            }

            if(this.quadSize < 2) {
                this.quadSize = this.quadSize + .1f;
            }

            Quaternionf quaternionf = new Quaternionf();

            float magicNumber = (float) (Math.PI/2); // WHAT THE FUCK IS A QUATERNION :FIRE:

            switch (this.randomNumberForDirection) {
                case 1, 2 : quaternionf.rotateY(0); break; // SOUTH
                case 3, 4 : quaternionf.rotateY(magicNumber); break; // EAST
                case 5, 6 : quaternionf.rotateY(magicNumber * 2); break; // NORTH
                case 7, 8 : quaternionf.rotateY(magicNumber * 3); break; // WEST
                case 9    : quaternionf.rotateX(magicNumber); break; // DOWN
                case 10   : quaternionf.rotateX(magicNumber * 3); break; // UP
            }

            this.renderRotatedQuad(buffer, renderInfo, quaternionf, partialTicks);
        }
    }

    public void breakLeaves() {
        this.logHasLeaves = false;

        this.friction = 1.0F;
        this.hasPhysics = true;
        this.gravity = this.gravityModifier;

        if(this.onGround) {
            level.levelEvent(2001, this.particleBlockPos, 2047);
            remove();
        }
    }

    @Override
    protected void renderRotatedQuad(VertexConsumer buffer, Camera camera, Quaternionf quaternion, float partialTicks) {
        Vec3 vec3 = camera.getPosition();

        float f  = (float)(Mth.lerp(partialTicks, this.xo, this.x) - vec3.x());
        float f1 = (float)(Mth.lerp(partialTicks, this.yo, this.y) - vec3.y());
        float f2 = (float)(Mth.lerp(partialTicks, this.zo, this.z) - vec3.z());
        this.renderRotatedQuad(buffer, quaternion, f, f1, f2, partialTicks);
    }

    @Override
    protected void renderRotatedQuad(VertexConsumer buffer, Quaternionf quaternion, float x, float y, float z, float partialTicks) {
        float f = this.getQuadSize(partialTicks);

        float f1 = this.getU0();
        float f2 = this.getU1();
        float f3 = this.getV0();
        float f4 = this.getV1();

        int light2 = level.hasChunkAt(this.logBlockPos) ? LevelRenderer.getLightColor(this.level, this.logBlockPos) / 8 * 7 : 0;

        this.renderVertex(buffer, quaternion, x, y, z, 1.0F, -1.0F, f, f2, f4, light2);
        this.renderVertex(buffer, quaternion, x, y, z, 1.0F, 1.0F, f, f2, f3, light2);
        this.renderVertex(buffer, quaternion, x, y, z, -1.0F, 1.0F, f, f1, f3, light2);
        this.renderVertex(buffer, quaternion, x, y, z, -1.0F, -1.0F, f, f1, f4, light2);
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        this.yd -= 0.04 * (double)this.gravity;
        this.move(this.xd, this.yd, this.zd);
        if (this.speedUpWhenYMotionIsBlocked && this.y == this.yo) {
            this.xd *= 1.1;
            this.zd *= 1.1;
        }

        this.xd *= this.friction;
        this.yd *= this.friction;
        this.zd *= this.friction;
        if (this.onGround) {
            this.xd *= 0.699999988079071;
            this.zd *= 0.699999988079071;
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Factory(SpriteSet sprite) {
            this.sprites = sprite;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {

            BlockPos pos = new BlockPos((int) x, (int) y, (int) z);

            RandomSource randomsource = level.getRandom();

            double d3 = 0.5 - 1;
            double d4 = (double)pos.getX() + d3 + randomsource.nextDouble() * 1 * 2.0;
            double d5 = (double)pos.getY() + randomsource.nextDouble() * 1;
            double d6 = (double)pos.getZ() + d3 + randomsource.nextDouble() * 1 * 2.0;

            SweetwoodLeafParticle particle = new SweetwoodLeafParticle(level, d4, d5, d6, x, y, z, sprites);

            particle.setColor(1.0F, 1.0F, 1.0F);
            return particle;
        }
    }
}
