package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseEnergyBlock
import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock
import com.williambl.prometheus.common.tileentity.AncientDeviceMasterTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

open class AncientDeviceMasterBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                    lightLevel: Float, material: Material) : BaseMultiBlockMasterBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material) {

    companion object {
        val complete: PropertyBool = BaseMultiBlockMasterBlock.complete
    }

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return AncientDeviceMasterTileEntity()
    }

}