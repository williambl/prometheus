package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.energy.CapabilityEnergy

open class EnergyStoreTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 1000000

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        executeForEachSide({ position: BlockPos, facing: EnumFacing ->

            val tileEntity = this.getWorld().getTileEntity(position)
            if (tileEntity == null || tileEntity.isInvalid || !tileEntity.hasCapability(CapabilityEnergy.ENERGY, facing.opposite))
                return@executeForEachSide

            val consumer = tileEntity.getCapability(CapabilityEnergy.ENERGY, facing.opposite)

            if (consumer != null && canExtract()) {
                val energyToGive = Math.min(output, energyStored)
                extractEnergy(consumer.receiveEnergy(energyToGive, false), false)
            }
        })
    }

}
