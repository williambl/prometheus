package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseTileEntityProviderBlock
import com.williambl.prometheus.common.tileentity.GravityWellTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class GravityWellBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                       lightLevel: Float, material: Material) : BaseTileEntityProviderBlock<GravityWellTileEntity>(registryName, tab, soundType, hardness, resistance, lightLevel, material, ::GravityWellTileEntity) {

    companion object {
        val direction: PropertyDirection = PropertyDirection.create("direction", { true })
    }

    init {
        this.defaultState = this.blockState.baseState.withProperty(direction, EnumFacing.DOWN)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, direction)
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return this.defaultState.withProperty(direction, EnumFacing.getFront(meta))
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(direction).index
    }

    override fun getStateForPlacement(worldIn: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase): IBlockState {
        return this.defaultState.withProperty(direction, EnumFacing.getDirectionFromEntityLiving(pos, placer))
    }
}