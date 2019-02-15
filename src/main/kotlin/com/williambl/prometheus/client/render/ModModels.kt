package com.williambl.prometheus.client.render

import com.williambl.prometheus.common.block.ModBlocks
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

@Mod.EventBusSubscriber(Side.CLIENT)
object ModModels {

    @SubscribeEvent
    @JvmStatic
    fun registerItemBlockModels(event: ModelRegistryEvent) {
        ModBlocks.blocks.forEach { block ->
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, ModelResourceLocation(block.registryName!!, "inventory"))
        }
    }
}