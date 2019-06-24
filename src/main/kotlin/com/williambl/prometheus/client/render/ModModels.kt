package com.williambl.prometheus.client.render

import com.williambl.prometheus.client.render.entity.RenderEntityAncientDrone
import com.williambl.prometheus.common.block.ModBlocks
import com.williambl.prometheus.common.entity.EntityAncientDrone
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.client.registry.RenderingRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side


@Mod.EventBusSubscriber(Side.CLIENT)
object ModModels {

    @SubscribeEvent
    @JvmStatic
    fun registerModels(event: ModelRegistryEvent) {
        ModBlocks.blocks.forEach { block ->
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, ModelResourceLocation(block.registryName!!, "inventory"))
        }
        RenderingRegistry.registerEntityRenderingHandler(EntityAncientDrone::class.java, RenderEntityAncientDrone.Factory)
    }
}