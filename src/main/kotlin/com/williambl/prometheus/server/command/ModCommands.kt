package com.williambl.prometheus.server.command

import net.minecraftforge.fml.common.event.FMLServerStartingEvent

object ModCommands {

    val locateAncientDevice = LocateAncientDeviceCommand()
    val generateAncientComplex = GenerateAncientComplexCommand()

    fun registerCommands(e: FMLServerStartingEvent) {
        e.registerServerCommand(locateAncientDevice)
        e.registerServerCommand(generateAncientComplex)
    }
}