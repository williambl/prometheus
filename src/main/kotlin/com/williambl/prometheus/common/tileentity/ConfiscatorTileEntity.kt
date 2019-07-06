package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.OrientableTileEntityProviderBlock
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.item.ItemChorusFruit
import net.minecraft.item.ItemEnderPearl
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.AxisAlignedBB
import net.minecraftforge.energy.CapabilityEnergy

open class ConfiscatorTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 50000

    private val range = 3
    private val direction: EnumFacing by lazy { world.getBlockState(pos).getValue(OrientableTileEntityProviderBlock.facing) }
    private val aabb: AxisAlignedBB by lazy { AxisAlignedBB(pos.offset(direction, range)).grow(range.toDouble(), range.toDouble(), range.toDouble()) }

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    override fun update() {
        if (!this.hasWorld())
            return

        if (energyStored < 10)
            return

        world.getEntitiesWithinAABB(EntityPlayer::class.java, aabb).forEach { player ->
            getDisallowedItems(player.inventory).forEach { it.count = 0 }
            extractEnergy(10, false)
        }

    }

    fun getDisallowedItems(inv: InventoryPlayer): List<ItemStack> {
        return inv.mainInventory.filter { isDisallowed(it) }
    }

    fun isDisallowed(stack: ItemStack): Boolean {
        return stack.item is ItemEnderPearl ||
                stack.item is ItemChorusFruit ||
                stack.hasCapability(CapabilityEnergy.ENERGY, null)
    }
}
