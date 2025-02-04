package com.elysiasilly.calvariae.common.entity;

import com.elysiasilly.calvariae.core.registry.CEntities;
import com.google.common.base.Predicates;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.joml.Math;

public class LanternFlareEntity extends Projectile {

    public LanternFlareEntity(Level level) {
        super(CEntities.LANTERN_FLARE.get(), level);

    }

    public LanternFlareEntity(EntityType<LanternFlareEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    protected double getDefaultGravity() {
        return .05;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {

        Vec3 vel = getDeltaMovement();

        Vec3 normal = new Vec3(result.getDirection().getStepX(), result.getDirection().getStepY(), result.getDirection().getStepZ());

        double x = normal.x();
        double y = normal.y();
        double z = normal.z();
        double dot = org.joml.Math.fma(vel.x, x, Math.fma(vel.y, y, vel.z * z));
        vel = new Vec3(vel.x - (dot + dot) * x, vel.y - (dot + dot) * y, vel.z - (dot + dot) * z);


        if(vel.y < .3) vel = new Vec3(vel.x, 0, vel.z);

        setDeltaMovement(vel.scale(.8));
    }

    @Override
    public void tick() {

        //super.tick();
        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, Predicates.alwaysTrue());
        if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult)) {
            this.hitTargetOrDeflectSelf(hitresult);
        }

        checkInsideBlocks();
        //Vec3 vec3 = this.getDeltaMovement();
        //double d0 = this.getX() + vec3.x;
        //double d1 = this.getY() + vec3.y;
        //double d2 = this.getZ() + vec3.z;
        //this.updateRotation();
        //float f;
        //if (this.isInWater()) {
        //    for(int i = 0; i < 4; ++i) {
        //        float f1 = 0.25F;
        //        this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
        //    }
        //    f = 0.8F;
        //} else {
        //    f = 0.99F;
        //}

        //move(MoverType.SELF, getDeltaMovement().scale(.8));


        updateRotation();

        Vec3 delta = getDeltaMovement();

        Vec3 newPos = position().add(delta);

        setDeltaMovement(delta.scale(.99));
        applyGravity();
        setPos(newPos);

        //this.setDeltaMovement(vec3.scale(f));
        //this.applyGravity();
        //this.setPos(d0, d1, d2);
    }
}
