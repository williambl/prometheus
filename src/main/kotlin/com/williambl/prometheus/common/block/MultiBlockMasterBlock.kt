package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseEnergyBlock
import com.williambl.prometheus.common.item.ModItems
import com.williambl.prometheus.common.tileentity.EnergyStoreTileEntity
import com.williambl.prometheus.common.tileentity.MultiBlockMasterTileEntity
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.monster.EntityGiantZombie
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
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

    override fun onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        if (playerIn.getHeldItem(hand).item != ModItems.multiBlockStarter)
            return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ)

        val tileEntity = worldIn.getTileEntity(pos) as MultiBlockMasterTileEntity

        if (tileEntity.isValidMultiBlock && tileEntity.energyStored == tileEntity.maxEnergyStored) {
            val entity = EntityGiantZombie(worldIn)
            entity.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ)
            worldIn.spawnEntity(entity)
        }
        return true
    }

}