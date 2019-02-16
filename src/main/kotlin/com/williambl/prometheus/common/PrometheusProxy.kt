package com.williambl.prometheus.common

import com.williambl.prometheus.common.tileentity.ModTileEntities
import com.williambl.prometheus.common.world.ModWorld
import com.williambl.prometheus.server.command.ModCommands
import net.minecraftforge.fml.common.event.FMLServerAboutToStartEvent
import net.minecraftforge.fml.common.event.FMLServerStartingEvent

open class PrometheusProxy {

    open fun preInit() {}

    open fun init() {
        ModTileEntities.registerTileEntities()
        ModWorld.registerWorldGenerators()
    }

    open fun postInit() {}

    open fun serverStart(e: FMLServerStartingEvent) {
        ModCommands.registerCommands(e)
    }
}