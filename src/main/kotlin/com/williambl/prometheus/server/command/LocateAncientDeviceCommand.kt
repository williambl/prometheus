package com.williambl.prometheus.server.command

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TextComponentString

class LocateAncientDeviceCommand : BaseCommand() {

    override val commandName = "locateancientdevice"
    override val commandAliases = mutableListOf("locancientdevice", "locatead", "locad")

    override val helpText = "locateancientdevice"

    override val permissionLevel = 4

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        val chunkX: Int = (Math.sin((Math.abs(server.getWorld(0).seed / 30)).toDouble()) * 300).toInt()
        val chunkZ: Int = (Math.cos((Math.abs(server.getWorld(0).seed / 15)).toDouble()) * 300).toInt()
        val blockX: Int = chunkX * 16
        val blockZ: Int = chunkZ * 16

        sender.sendMessage(TextComponentString("Ancient Device is at Chunk X: $chunkX, Chunk Z: $chunkZ"))
        sender.sendMessage(TextComponentString("Ancient Device is at roughly Block X: $blockX, Block Z: $blockZ"))
    }
}