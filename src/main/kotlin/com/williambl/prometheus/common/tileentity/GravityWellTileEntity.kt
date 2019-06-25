package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.Entity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB

open class GravityWellTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 50000
    var maxRange = 32
    var direction = EnumFacing.UP
    var strength = 0.1

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        world.getEntitiesWithinAABB(Entity::class.java, AxisAlignedBB(pos, pos.offset(direction, maxRange)).expand(1.0, 0.0, 1.0)).forEach { entity ->
            entity.addVelocity(direction.frontOffsetX * strength, direction.frontOffsetY * strength, direction.frontOffsetZ * strength)
            entity.velocityChanged = true
        }
    }

}
