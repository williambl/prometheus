package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseEnergyBlock
import com.williambl.prometheus.common.tileentity.EnergyStoreTileEntity
import com.williambl.prometheus.common.tileentity.MultiBlockMasterTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class MultiBlockMasterBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                 lightLevel: Float, material: Material) : BaseEnergyBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material) {


    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return MultiBlockMasterTileEntity()
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        super.breakBlock(worldIn, pos, state)
        worldIn.removeTileEntity(pos)
    }

}