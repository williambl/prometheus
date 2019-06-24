package com.williambl.prometheus

import com.williambl.prometheus.common.tileentity.ModTileEntities
import com.williambl.prometheus.common.world.ModWorld
import com.williambl.prometheus.server.command.ModCommands
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent

@Mod(modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter", modid = Prometheus.MODID,
        name = Prometheus.NAME, version = Prometheus.VERSION, dependencies = "required-after:forgelin")
object Prometheus {

    const val MODID = "prometheus"
    const val NAME = "Prometheus"
    const val VERSION = "1.0.0"

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        ModTileEntities.registerTileEntities()
        ModWorld.registerWorldGenerators()
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
    }

    @Mod.EventHandler
    fun serverStart(event: FMLServerStartingEvent) {
        ModCommands.registerCommands(event)
    }
}