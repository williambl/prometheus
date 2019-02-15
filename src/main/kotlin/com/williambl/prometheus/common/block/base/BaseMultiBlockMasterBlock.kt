package com.williambl.prometheus.common.block.base

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

open class BaseMultiBlockMasterBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                     lightLevel: Float, material: Material) : BaseEnergyBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material) {


    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return BaseMultiBlockMasterTileEntity(0, 0, 0)
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        super.breakBlock(worldIn, pos, state)
        worldIn.removeTileEntity(pos)
    }

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (playerIn.getHeldItem(hand).item != ModItems.multiBlockStarter)
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ)

        val tileEntity = worldIn.getTileEntity(pos) as AncientDeviceMasterTileEntity

        if (tileEntity.isValidMultiBlock && tileEntity.energyStored == tileEntity.maxEnergyStored)
            tileEntity.activateMultiBlock()

        return true
    }

}