package com.williambl.prometheus.server.command

import com.williambl.prometheus.common.fourthwall.FourthWallHelper
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.text.TextComponentString

class BreakFourthWallCommand : BaseCommand() {

    override val commandName = "breakfourthwall"
    override val commandAliases = mutableListOf("b4w")

    override val helpText = "b4w"

    override val permissionLevel = 4

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        sender.sendMessage(TextComponentString("Hi, " + FourthWallHelper.getCurrentUser()))
        FourthWallHelper.showFakeMenu()
    }
}