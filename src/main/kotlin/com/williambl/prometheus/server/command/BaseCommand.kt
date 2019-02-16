package com.williambl.prometheus.server.command

import net.minecraft.command.ICommand
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.BlockPos

open class BaseCommand : ICommand {

    open val commandName = ""
    open val commandAliases = mutableListOf<String>()

    open val helpText = ""

    open val permissionLevel = 0

    override fun getUsage(sender: ICommandSender): String {
        return helpText
    }

    override fun getName(): String {
        return commandName
    }

    override fun getTabCompletions(server: MinecraftServer, sender: ICommandSender, args: Array<String>, targetPos: BlockPos?): MutableList<String> {
        return mutableListOf()
    }

    override fun compareTo(other: ICommand?): Int {
        if (other != null)
            return if (this.name == other.name) 1 else 0
        return 0
    }

    override fun checkPermission(server: MinecraftServer, sender: ICommandSender): Boolean {
        return sender.canUseCommand(this.permissionLevel, this.name)
    }

    override fun isUsernameIndex(args: Array<String>, index: Int): Boolean {
        return false
    }

    override fun getAliases(): MutableList<String> {
        return commandAliases
    }

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {}

}