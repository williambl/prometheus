package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseBlock
import com.williambl.prometheus.common.block.base.BaseLightBlock
import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock
import com.williambl.prometheus.common.block.base.BaseTileEntityProviderBlock
import com.williambl.prometheus.common.tileentity.*
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object ModBlocks {

    val blocks = listOf<Block>(
            BaseMultiBlockMasterBlock("ancient_core", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.0f, Material.IRON, ::AncientDeviceMasterTileEntity),
            BaseBlock("ancient_circuitry", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.5f, Material.IRON),
            BaseBlock("ancient_casing", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON),
            BaseTileEntityProviderBlock("ancient_drone_spawner", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON, ::AncientDroneSpawnerTileEntity),
            BaseLightBlock("ancient_light", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 15, Material.IRON),
            OrientableTileEntityProviderBlock("gravity_well", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0f, Material.IRON, ::GravityWellTileEntity),
            BaseBlock("ancient_concrete", CreativeTabs.BUILDING_BLOCKS, SoundType.STONE, 5.0f, 5.0f, 0.0f, Material.ROCK),
            OrientableTileEntityProviderBlock("confiscator", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0f, Material.IRON, ::ConfiscatorTileEntity),
            OrientableTileEntityProviderBlock("plasma_dispenser", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0f, Material.IRON, ::PlasmaDispenserTileEntity)
    )

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.registerAll(*blocks.toTypedArray())
        println("registering blocks...")
    }

    @SubscribeEvent
    @JvmStatic
    fun registerItemBlocks(event: RegistryEvent.Register<Item>) {
        blocks.forEach { block ->
            val itemBlock = ItemBlock(block).setRegistryName(block.registryName)
            event.registry.register(itemBlock)
        }
        println("registering itemblocks...")
    }

}