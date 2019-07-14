package com.williambl.prometheus.server.command

import com.williambl.prometheus.common.fourthwall.FourthWallHelper
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer

class BreakFourthWallCommand : BaseCommand() {

    override val commandName = "breakfourthwall"
    override val commandAliases = mutableListOf("b4w")

    override val helpText = "b4w"

    override val permissionLevel = 4

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        //FourthWallHelper.showFakeMenu()
        FourthWallHelper.showDialog("Hello, " + FourthWallHelper.getCurrentUser(), "Yes", "No")
    }
}