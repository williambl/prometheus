package com.williambl.prometheus.common.block

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

    val BASE_BLOCK = BaseBlock("BaseBlock", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.0f, Material.IRON)

    val blocks = listOf<Block>(BASE_BLOCK)

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