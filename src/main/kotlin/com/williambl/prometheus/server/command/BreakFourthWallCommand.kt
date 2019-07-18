package com.williambl.prometheus.server.command

import com.williambl.prometheus.common.networking.ModPackets
import com.williambl.prometheus.common.networking.ShowFakeMenuMessage
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.server.MinecraftServer

class BreakFourthWallCommand : BaseCommand() {

    override val commandName = "breakfourthwall"
    override val commandAliases = mutableListOf("b4w")

    override val helpText = "b4w"

    override val permissionLevel = 4

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        //ModPackets.instance.sendTo(ShowDialogMessage("Hello" + FourthWallHelper.username, arrayOf("Yes", "No"), finalAction = 0), sender as EntityPlayerMP)
        ModPackets.instance.sendTo(ShowFakeMenuMessage(), sender as EntityPlayerMP)
    }
}