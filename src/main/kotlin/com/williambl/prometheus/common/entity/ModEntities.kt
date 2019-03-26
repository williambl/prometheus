package com.williambl.prometheus.common.entity

import com.williambl.prometheus.Prometheus
import net.minecraft.util.ResourceLocation
import net.minecraft.world.storage.loot.LootTableList
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.EntityEntry
import net.minecraftforge.fml.common.registry.EntityEntryBuilder

@Mod.EventBusSubscriber
object ModEntities {

    @SubscribeEvent
    @JvmStatic
    fun registerEntities(e: RegistryEvent.Register<EntityEntry>) {
        var id = 0
        val entry: EntityEntry = EntityEntryBuilder.create<EntityAncientDrone>()
                .entity(EntityAncientDrone::class.java)
                .id(ResourceLocation(Prometheus.MODID, "ancient_drone"), id++)
                .name("ancient_drone")
                .egg(0xFFFFFF, 0xAAAAAA)
                .tracker(64, 2, true)
                .build()
        e.registry.register(entry)
        LootTableList.register(EntityAncientDrone.loot)
    }
}