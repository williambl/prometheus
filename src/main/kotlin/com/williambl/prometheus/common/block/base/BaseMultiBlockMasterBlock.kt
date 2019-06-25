package com.williambl.prometheus.common.block.base

import com.williambl.prometheus.common.item.ModItems
import com.williambl.prometheus.common.tileentity.base.BaseMultiBlockMasterTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class BaseMultiBlockMasterBlock<T : BaseMultiBlockMasterTileEntity>(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                                                         lightLevel: Float, material: Material, factory: () -> T) : BaseTileEntityProviderBlock<T>(registryName, tab, soundType, hardness,
        resistance, lightLevel, material, factory) {

    companion object {
        val complete: PropertyBool = PropertyBool.create("complete")
    }

    init {
        this.defaultState = this.blockState.baseState.withProperty(complete, false)
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (playerIn.getHeldItem(hand).item != ModItems.multiBlockStarter)
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ)

        val tileEntity = worldIn.getTileEntity(pos) as T

        if (tileEntity.isValidMultiBlock && tileEntity.energyStored == tileEntity.maxEnergyStored)
            tileEntity.activateMultiBlock()

        return true
    }

    override fun createBlockState(): BlockStateContainer { return BlockStateContainer(this, complete) }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(complete, meta > 0)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return if (state.getValue(complete)) 1 else 0
    }

}