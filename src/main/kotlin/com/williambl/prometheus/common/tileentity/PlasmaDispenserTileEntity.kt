package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.OrientableTileEntityProviderBlock
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.projectile.EntitySmallFireball
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

open class PlasmaDispenserTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 50000

    private var counter = 0
    private val cooldown = 100

    private val strength: Double = 0.5
    private val direction: EnumFacing by lazy { world.getBlockState(pos).getValue(OrientableTileEntityProviderBlock.facing) }
    private val offsetPos: BlockPos by lazy { pos.offset(direction) }
    private val accel: Vec3d by lazy { Vec3d(direction.directionVec.x.toDouble(), direction.directionVec.y.toDouble(), direction.directionVec.z.toDouble()).scale(strength) }

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    override fun update() {
        if (!this.hasWorld())
            return

        counter++
        if (counter < cooldown)
            return
        counter = 0

        if (energyStored < 10)
            return

        val entity = EntitySmallFireball(world, offsetPos.x.toDouble(), offsetPos.y.toDouble(), offsetPos.z.toDouble(), accel.x, accel.y, accel.z)
        world.spawnEntity(entity)
        extractEnergy(10, false)

    }

}
