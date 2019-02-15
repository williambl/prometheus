package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.AncientDeviceMasterBlock
import com.williambl.prometheus.common.block.base.BaseBlock
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

    val energyStore = EnergyStoreBlock("energy_store", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.0f, Material.IRON)
    val multiBlockMaster = AncientDeviceMasterBlock("ancient_core", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.0f, Material.IRON)
    val ancientCircuitry = BaseBlock("ancient_circuitry", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON)
    val ancientCasing = BaseBlock("ancient_casing", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON)

    val blocks = listOf<Block>(energyStore, multiBlockMaster, ancientCircuitry, ancientCasing)

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block> ) {
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