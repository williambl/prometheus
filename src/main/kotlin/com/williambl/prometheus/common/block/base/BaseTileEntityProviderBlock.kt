package com.williambl.prometheus.common.block.base

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class BaseTileEntityProviderBlock<T : TileEntity>(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                                       lightLevel: Float, material: Material, val factory: () -> T) : BaseBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material), ITileEntityProvider {

    override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity {
        return factory()
    }

    override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState) {
        super.breakBlock(worldIn, pos, state)
        worldIn.removeTileEntity(pos)
    }
}