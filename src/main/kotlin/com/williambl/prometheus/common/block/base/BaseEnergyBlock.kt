package com.williambl.prometheus.common.block.base

import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class BaseEnergyBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                      lightLevel: Float, material: Material) : BaseTileEntityProviderBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material) {


    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return BaseEnergyTileEntity()
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
    }

}