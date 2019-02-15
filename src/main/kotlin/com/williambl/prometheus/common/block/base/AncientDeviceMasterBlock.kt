package com.williambl.prometheus.common.block.base

import com.williambl.prometheus.common.block.base.BaseEnergyBlock
import com.williambl.prometheus.common.item.ModItems
import com.williambl.prometheus.common.tileentity.AncientDeviceMasterTileEntity
import com.williambl.prometheus.common.tileentity.base.BaseMultiBlockMasterTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class AncientDeviceMasterBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                    lightLevel: Float, material: Material) : BaseEnergyBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material) {

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return AncientDeviceMasterTileEntity()
    }

}