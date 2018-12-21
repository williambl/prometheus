package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.energy.BaseEnergyStorage
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ITickable
import net.minecraftforge.energy.IEnergyStorage
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.energy.CapabilityEnergy

open class BaseEnergyTileEntity: TileEntity(), ITickable, ICapabilityProvider {

    var energyStorage: IEnergyStorage = BaseEnergyStorage()

    override fun update() {}

    protected fun executeForEachSide(functionToExecute: (position: BlockPos) -> Unit) {
        for(facing: EnumFacing in EnumFacing.VALUES) {
            val position: BlockPos = getPos().offset(facing)
            functionToExecute(position)
        }
    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === CapabilityEnergy.ENERGY)
            this as T
        else
            super.getCapability(capability, facing)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return if (capability === CapabilityEnergy.ENERGY)
            true
        else
            super.hasCapability(capability, facing)
    }

}
