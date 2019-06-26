package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.GravityWellBlock
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.AxisAlignedBB

open class GravityWellTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 50000
    var maxRange = 32

    private val direction: EnumFacing by lazy { world.getBlockState(pos).getValue(GravityWellBlock.facing) }
    private val strength: Double by lazy { if (direction in EnumFacing.HORIZONTALS) 0.05 else 0.1 }

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    override fun update() {
        if (!this.hasWorld())
            return

        if (world.isRemote) {
            world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, true, pos.x + world.rand.nextDouble(), pos.y + world.rand.nextDouble(), pos.z + world.rand.nextDouble(), direction.frontOffsetX * (maxRange / 10.0), direction.frontOffsetY * (maxRange / 10.0), direction.frontOffsetZ * (maxRange / 10.0))
            return
        }

        if (energyStored < 10)
            return

        world.getEntitiesWithinAABB(Entity::class.java, AxisAlignedBB(pos, pos.offset(direction, maxRange)).expand(1.0, 1.0, 1.0)).forEach { entity ->
            if (direction in EnumFacing.HORIZONTALS)
                entity.motionY = 0.0
            entity.addVelocity(direction.frontOffsetX * strength, direction.frontOffsetY * strength, direction.frontOffsetZ * strength)
            entity.velocityChanged = true
            extractEnergy(10, false)
        }

    }

}
