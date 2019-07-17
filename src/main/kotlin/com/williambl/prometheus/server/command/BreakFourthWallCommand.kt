package com.williambl.prometheus.server.command

import com.williambl.prometheus.common.fourthwall.FourthWallHelper
import com.williambl.prometheus.common.networking.ModPackets
import com.williambl.prometheus.common.networking.ShowDialogMessage
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.server.MinecraftServer
import org.lwjgl.input.Mouse

class BreakFourthWallCommand : BaseCommand() {

    override val commandName = "breakfourthwall"
    override val commandAliases = mutableListOf("b4w")

    override val helpText = "b4w"

    override val permissionLevel = 4

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        Mouse.setGrabbed(false)
        ModPackets.instance.sendTo(ShowDialogMessage("Hello" + FourthWallHelper.username, arrayOf("Yes", "No")), sender as EntityPlayerMP)
        //FourthWallHelper.showFakeMenu()
        /*FourthWallHelper.showDialog(
                "Hello, " + FourthWallHelper.username,
                arrayOf("Yes", "No"),
                Int.MAX_VALUE
        ) { FourthWallHelper.showDialog("One button?", arrayOf("No"), Int.MAX_VALUE) { FourthWallHelper.showDialog("Zero?", arrayOf(), 30) { FourthWallHelper.shouldMessUpTooltips = true } } }
        */
    }
}