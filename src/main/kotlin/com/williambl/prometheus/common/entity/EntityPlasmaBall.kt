package com.williambl.prometheus.common.entity

import com.williambl.prometheus.common.block.RedstoneSourceBlock
import com.williambl.prometheus.objectholder.ModBlockHolder
import net.minecraft.entity.projectile.EntityFireball
import net.minecraft.init.Blocks
import net.minecraft.util.DamageSource
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.World


class EntityPlasmaBall : EntityFireball {

    val maxAge = 100

    constructor(worldIn: World) : super(worldIn) {
        this.setSize(1.0F, 1.0F)
    }

    constructor(worldIn: World, x: Double, y: Double, z: Double, accelX: Double, accelY: Double, accelZ: Double) : super(worldIn, x, y, z, accelX, accelY, accelZ) {
        this.setSize(1.0F, 1.0F)
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
            if (world.getBlockState(result.blockPos).block == ModBlockHolder.plasmaBallAcceptor) {
                world.setBlockState(result.blockPos, world.getBlockState(result.blockPos).withProperty(RedstoneSourceBlock.powered, true))
            }
                val blockpos = result.blockPos.offset(result.sideHit)

                if (this.world.isAirBlock(blockpos)) {
                    this.world.setBlockState(blockpos, Blocks.FIRE.defaultState)
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