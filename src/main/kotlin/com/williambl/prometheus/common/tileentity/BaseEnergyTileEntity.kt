package com.williambl.prometheus.common.tileentity

import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ITickable
import net.minecraftforge.energy.IEnergyStorage
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.CapabilityEnergy

open class BaseEnergyTileEntity() : TileEntity(), ITickable, ICapabilityProvider, IEnergyStorage, INBTSerializable<NBTTagCompound> {

    private var stored: Int = 0
    private var capacity: Int = 0
    private var maxInput: Int = 0
    private var maxOutput: Int = 0

    constructor(dataTag: NBTTagCompound): this() {
        this.deserializeNBT(dataTag)
    }

    override fun update() {}

    protected fun executeForEachSide(functionToExecute: (position: BlockPos, facing: EnumFacing) -> Unit) {
        for(facing: EnumFacing in EnumFacing.VALUES) {
            val position: BlockPos = getPos().offset(facing)
            functionToExecute(position, facing)
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

    override fun serializeNBT(): NBTTagCompound {
        val dataTag = NBTTagCompound()

        dataTag.setInteger("EnergyStored", this.stored)
        dataTag.setInteger("EnergyCapacity", this.capacity)
        dataTag.setInteger("EnergyMaxInput", this.maxInput)
        dataTag.setInteger("EnergyMaxOutput", this.maxOutput)

        return dataTag
    }

    override fun deserializeNBT(nbt: NBTTagCompound) {
        if (nbt.hasKey("EnergyStored"))
            this.stored = nbt.getInteger("EnergyStored")
        if (nbt.hasKey("EnergyCapacity"))
            this.capacity = nbt.getInteger("EnergyCapacity")
        if (nbt.hasKey("EnergyMaxInput"))
            this.maxInput = nbt.getInteger("EnergyMaxInput")
        if (nbt.hasKey("EnergyMaxOutput"))
            this.maxOutput = nbt.getInteger("EnergyMaxOutput")

        if (this.stored > this.getMaxEnergyStored())
            this.stored = this.getMaxEnergyStored()
    }

    override fun receiveEnergy(maxReceive: Int, simulate: Boolean): Int {
        val acceptedPower = Math.min(this.getMaxEnergyStored() - this.getEnergyStored(), Math.min(this.getMaxInput(), maxReceive))

        if (!simulate)
            this.stored += acceptedPower

        return if (this.canReceive()) acceptedPower else 0
    }

    override fun extractEnergy(maxExtract: Int, simulate: Boolean): Int {
        val removedPower = Math.min(this.getEnergyStored(), Math.min(this.getMaxOutput(), maxExtract))

        if (!simulate)
            this.stored -= removedPower
        return if (this.canExtract()) removedPower else 0
    }

    override fun getEnergyStored(): Int {
        return this.stored
    }

    override fun getMaxEnergyStored(): Int {
        return this.capacity
    }

    fun setMaxEnergyStored(capacity: Int) {
        this.capacity = capacity

        if (this.stored > capacity)
            this.stored = capacity
    }

    fun getMaxInput(): Int {
        return this.maxInput
    }

    fun setMaxInput(input: Int) {
        this.maxInput = input
    }

    fun getMaxOutput(): Int {
        return this.maxOutput
    }

    fun setMaxOutput(output: Int) {
        this.maxOutput = output
    }

    override fun canExtract(): Boolean {
        return this.getMaxOutput() > 0 && this.stored > 0
    }

    override fun canReceive(): Boolean {
        return this.getMaxInput() > 0
    }
}
