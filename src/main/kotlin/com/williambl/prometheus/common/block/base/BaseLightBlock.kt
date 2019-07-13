package com.williambl.prometheus.common.block.base

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import java.util.*


class BaseLightBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                     val maxLightLevel: Int, material: Material) : BaseBlock(registryName, tab, soundType, hardness, resistance, 0f, material) {

    companion object {
        val powered: PropertyBool = PropertyBool.create("powered")
    }

    init {
        this.defaultState = this.blockState.baseState.withProperty(powered, false)
    }

    override fun getLightValue(state: IBlockState, world: IBlockAccess, pos: BlockPos): Int {
        return if (state.getValue(powered)) maxLightLevel else 0
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

    override fun onBlockAdded(worldIn: World, pos: BlockPos, state: IBlockState) {
        if (!worldIn.isRemote) {
            if (state.getValue(powered) && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(powered, false), 2)
            } else if (!state.getValue(powered) && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(powered, true), 2)
            }
        }
    }

    override fun neighborChanged(state: IBlockState, worldIn: World, pos: BlockPos, blockIn: Block, fromPos: BlockPos) {
        if (!worldIn.isRemote) {
            if (state.getValue(powered) && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(powered, false), 2)
            } else if (!state.getValue(powered) && worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(powered, true), 2)
            }
        }
    }

    override fun updateTick(worldIn: World, pos: BlockPos, state: IBlockState, rand: Random) {
        if (!worldIn.isRemote) {
            if (state.getValue(powered) && !worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(powered, false), 2)
            }
        }
    }
}