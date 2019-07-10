package com.williambl.prometheus.common.entity

import net.minecraft.entity.EntityLiving
import net.minecraft.entity.projectile.EntityFireball
import net.minecraft.init.Blocks
import net.minecraft.util.DamageSource
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World


class EntityPlasmaBall : EntityFireball {

    val maxAge = 100

    constructor(worldIn: World) : super(worldIn) {
        this.setSize(0.3125F, 0.3125F)
    }

    constructor(worldIn: World, x: Double, y: Double, z: Double, accelX: Double, accelY: Double, accelZ: Double) : super(worldIn, x, y, z, accelX, accelY, accelZ) {
        this.setSize(0.3125F, 0.3125F)
    }

    override fun onUpdate() {
        super.onUpdate()
        if (this.ticksExisted > maxAge)
            this.setDead()
    }

    override fun onImpact(result: RayTraceResult) {
        if (this.world.isRemote)
            return

        if (result.entityHit != null) {
            if (!result.entityHit.isImmuneToFire) {
                val flag = result.entityHit.attackEntityFrom(DamageSource.causeFireballDamage(this, this.shootingEntity), 5.0f)

                if (flag) {
                    this.applyEnchantments(this.shootingEntity, result.entityHit)
                    result.entityHit.setFire(5)
                }
            }
        } else {
            var flag1 = true

            if (this.shootingEntity != null && this.shootingEntity is EntityLiving) {
                flag1 = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this.shootingEntity)
            }

            if (flag1) {
                val blockpos = result.blockPos.offset(result.sideHit)

                if (this.world.isAirBlock(blockpos)) {
                    this.world.setBlockState(blockpos, Blocks.FIRE.defaultState)
                }
            }
        }

        this.setDead()
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    override fun canBeCollidedWith(): Boolean {
        return false
    }

    /**
     * Called when the entity is attacked.
     */
    override fun attackEntityFrom(source: DamageSource, amount: Float): Boolean {
        return false
    }
}