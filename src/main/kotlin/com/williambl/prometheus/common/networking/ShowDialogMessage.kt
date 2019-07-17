package com.williambl.prometheus.common.networking

import com.williambl.prometheus.common.fourthwall.FourthWallHelper
import io.netty.buffer.ByteBuf
import net.minecraft.network.PacketBuffer
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.relauncher.Side

class ShowDialogMessage(var message: String, var options: Array<String> = arrayOf(), var timeToClose: Int = Int.MAX_VALUE, var finalAction: Int = 0) : IMessage {

    constructor() : this("", arrayOf())

    override fun toBytes(buf: ByteBuf?) {
        val packetBuffer = PacketBuffer(buf!!)


        packetBuffer.writeString(message)
        packetBuffer.writeInt(options.size)
        options.forEach {
            packetBuffer.writeString(it)
        }
        packetBuffer.writeInt(timeToClose)
        packetBuffer.writeInt(finalAction)
    }

    override fun fromBytes(buf: ByteBuf?) {
        val packetBuffer = PacketBuffer(buf!!)

        message = packetBuffer.readString(64)

        val messageCount = packetBuffer.readInt()
        options = Array(messageCount) { "" }
        for (i in 0 until messageCount) {
            options[i] = packetBuffer.readString(64)
        }

        timeToClose = packetBuffer.readInt()

        finalAction = packetBuffer.readInt()
    }

    class ShowDialogMessageHandler : IMessageHandler<ShowDialogMessage, IMessage> {
        override fun onMessage(message: ShowDialogMessage?, ctx: MessageContext?): IMessage? {
            if (message == null)
                return null

            if (ctx?.side == Side.CLIENT) {
                FourthWallHelper.showDialog(message.message, message.options, message.timeToClose, FourthWallHelper.finalActions[message.finalAction])
            }

            return null
        }

    }
}