package com.elysiasilly.calvariae.client.particle;


import com.elysiasilly.babel.util.Conversions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RottingLanternGoopParticle extends TextureSheetParticle {

    public RottingLanternGoopParticle(ClientLevel level, Vec3 pos, Vec3 speed, SpriteSet sprite) {
        super(level, pos.x, pos.y, pos.z);

        this.xd = speed.x;
        this.yd = speed.y;
        this.zd = speed.z;

        this.quadSize = .1f;
        this.sprite = sprite.get(12, 12);
        this.gravity = 1;
        this.lifetime = 1000;
        this.friction = .05f;
    }

    @Override
    protected int getLightColor(float partialTick) {
        return LightTexture.FULL_BRIGHT;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public void tick() {
        if(this.age > this.lifetime) remove();

        if(this.xd == 0 && this.yd == 0 && this.zd == 0) remove();

        BlockState state = level.getBlockState(Conversions.vector.blockPos(this.getPos()));

        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if(!state.isSolid()) {



            this.x += this.xd;
            this.y += this.yd;
            this.z += this.zd;

            this.oRoll = this.roll;
            this.roll += .01f;

            this.xd = this.xd * 1;
            this.yd = this.yd * 1;
            this.zd = this.zd * 1;

        }



        /*
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.yd -= 0.04 * (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if(this.speedUpWhenYMotionIsBlocked && this.y == this.yo) {
                this.xd *= 1.1;
                this.zd *= 1.1;
            }

            if(!this.stoppedByCollision) this.yd = .1f;

            //this.xd *= this.friction;
            //this.yd *= this.friction;
            //this.zd *= this.friction;
            if(this.onGround) {
                this.xd *= 0.699999988079071;
                this.zd *= 0.699999988079071;
            }
        }
         */
    }

    static final double MAXIMUM_COLLISION_VELOCITY_SQUARED = Mth.square(100.0);

    boolean stoppedByCollision;

    @Override
    public void move(double x, double y, double z) {
        if (!this.stoppedByCollision) {
            double d0 = x;
            double d1 = y;
            double d2 = z;
            if (this.hasPhysics && (x != 0.0 || y != 0.0 || z != 0.0) && x * x + y * y + z * z < MAXIMUM_COLLISION_VELOCITY_SQUARED) {
                Vec3 vec3 = Entity.collideBoundingBox((Entity)null, new Vec3(x, y, z), this.getBoundingBox(), this.level, List.of());
                x = vec3.x;
                y = vec3.y;
                z = vec3.z;
            }

            if (x != 0.0 || y != 0.0 || z != 0.0) {
                this.setBoundingBox(this.getBoundingBox().move(x, y, z));
                this.setLocationFromBoundingbox();
            }

            if (Math.abs(d1) >= 9.999999747378752E-6 && Math.abs(y) < 9.999999747378752E-6) {
                this.stoppedByCollision = true;
            }

            this.onGround = d1 != y && d1 < 0.0;
            if (d0 != x) {
                this.xd = 0.0;
            }

            if (d2 != z) {
                this.zd = 0.0;
            }
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        final SpriteSet SPRITE;

        public Provider(SpriteSet sprite) {
            SPRITE = sprite;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            RottingLanternGoopParticle particle = new RottingLanternGoopParticle(level, new Vec3(x, y, z), new Vec3(xSpeed, ySpeed, zSpeed), this.SPRITE);
            particle.pickSprite(this.SPRITE);
            return particle;
        }
    }
}
