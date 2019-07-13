package com.williambl.prometheus.server.command

import net.minecraftforge.fml.common.event.FMLServerStartingEvent

object ModCommands {

    val locateAncientDevice = LocateAncientDeviceCommand()
    val generateAncientComplex = GenerateAncientComplexCommand()
    val breakFourthWall = BreakFourthWallCommand()

    fun registerCommands(e: FMLServerStartingEvent) {
        e.registerServerCommand(locateAncientDevice)
        e.registerServerCommand(generateAncientComplex)
        e.registerServerCommand(breakFourthWall)
    }
}