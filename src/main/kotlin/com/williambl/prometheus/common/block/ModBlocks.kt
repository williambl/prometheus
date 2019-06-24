package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseBlock
import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock
import com.williambl.prometheus.common.tileentity.AncientDeviceMasterTileEntity
import com.williambl.prometheus.common.tileentity.AncientDroneSpawnerTileEntity
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

    val ancientDeviceMaster = BaseMultiBlockMasterBlock("ancient_core", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.0f, Material.IRON, ::AncientDeviceMasterTileEntity)
    val ancientCircuitry = BaseBlock("ancient_circuitry", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.5f, Material.IRON)
    val ancientCasing = BaseBlock("ancient_casing", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON)
    val ancientDroneSpawner = BaseMultiBlockMasterBlock("ancient_drone_spawner", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON, ::AncientDroneSpawnerTileEntity)

    val blocks = listOf<Block>(ancientDeviceMaster, ancientCircuitry, ancientCasing, ancientDroneSpawner)

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