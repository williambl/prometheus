package com.williambl.prometheus.common.item

import com.williambl.prometheus.common.item.base.BaseItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object ModItems {

    val multiBlockStarter = BaseItem("multiblockstarter", CreativeTabs.REDSTONE)

    val items = listOf<Item>(multiBlockStarter)

    @SubscribeEvent
    @JvmStatic
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.registerAll(*items.toTypedArray())
    }
}