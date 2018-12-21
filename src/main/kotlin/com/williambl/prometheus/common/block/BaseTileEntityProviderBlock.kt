package com.williambl.prometheus.common.block

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.IEnergyStorage

abstract class BaseTileEntityProviderBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                                           lightLevel: Float, material: Material) : BaseBlock(registryName, tab, soundType, hardness,
        resistance, lightLevel, material), ITileEntityProvider {

    abstract override fun createNewTileEntity(worldIn: World, meta: Int): TileEntity

    abstract override fun breakBlock(worldIn: World, pos: BlockPos, state: IBlockState)

}