package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess


class RedstoneSourceBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                          lightLevel: Float, material: Material) : BaseBlock(registryName, tab, soundType, hardness, resistance, lightLevel, material) {

    companion object {
        val powered: PropertyBool = PropertyBool.create("powered")
    }

    init {
        this.defaultState = this.blockState.baseState.withProperty(powered, false)
    }

    override fun getWeakPower(blockState: IBlockState, blockAccess: IBlockAccess, pos: BlockPos, side: EnumFacing): Int {
        return if (blockState.getValue(powered)) 15 else 0
    }

    override fun getStrongPower(blockState: IBlockState, blockAccess: IBlockAccess, pos: BlockPos, side: EnumFacing): Int {
        return if (blockState.getValue(powered)) 15 else 0
    }

    override fun canProvidePower(state: IBlockState): Boolean {
        return true
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, powered)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(powered, meta > 0)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return if (state.getValue(powered)) 1 else 0
    }

}