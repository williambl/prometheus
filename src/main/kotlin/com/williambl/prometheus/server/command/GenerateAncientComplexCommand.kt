package com.williambl.prometheus.server.command

import com.williambl.prometheus.common.world.AncientComplexGenerator
import net.minecraft.command.ICommandSender
import net.minecraft.server.MinecraftServer
import net.minecraft.util.math.ChunkPos

class GenerateAncientComplexCommand : BaseCommand() {

    override val commandName = "generateancientcomplex"
    override val commandAliases = mutableListOf("gencomplex")

    override val helpText = "gencomplex"

    override val permissionLevel = 4

    override fun execute(server: MinecraftServer, sender: ICommandSender, args: Array<String>) {
        AncientComplexGenerator.generateComplex(sender.entityWorld, ChunkPos(sender.commandSenderEntity!!.chunkCoordX, sender.commandSenderEntity!!.chunkCoordZ))
    }
}