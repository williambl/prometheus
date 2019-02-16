package com.williambl.prometheus.server.command

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLServerStartingEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side

object ModCommands {

    val locateAncientDevice = LocateAncientDeviceCommand()

    fun registerCommands(e: FMLServerStartingEvent) {
        e.registerServerCommand(locateAncientDevice)
    }
}