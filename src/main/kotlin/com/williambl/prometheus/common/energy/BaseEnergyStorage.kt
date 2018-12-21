package com.williambl.prometheus.common.energy

import net.minecraft.nbt.NBTTagCompound
import net.minecraftforge.common.util.INBTSerializable
import net.minecraftforge.energy.IEnergyStorage
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability


class BaseEnergyStorage : IEnergyStorage, INBTSerializable<NBTTagCompound> {

    private var stored: Int = 0
    private var capacity: Int = 0
    private var maxInput: Int = 0
    private var maxOutput: Int = 0

    constructor(): this(0, 0, 0)

    constructor(capacity: Int, input: Int, output: Int): this(0, capacity, input, output)

    constructor(power: Int, capacity: Int, input: Int, output: Int) {
        this.stored = power
        this.capacity = capacity
        this.maxInput = input
        this.maxOutput = output
    }

    constructor(dataTag: NBTTagCompound) {
        this.deserializeNBT(dataTag)
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

    fun setCapacity(capacity: Int) {
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