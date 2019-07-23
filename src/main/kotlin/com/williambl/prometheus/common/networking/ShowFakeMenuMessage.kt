package com.williambl.prometheus.common.networking

import com.williambl.prometheus.client.fourthwall.FourthWallHelper
import io.netty.buffer.ByteBuf
import net.minecraftforge.fml.common.network.simpleimpl.IMessage
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext
import net.minecraftforge.fml.relauncher.Side

class ShowFakeMenuMessage : IMessage {

    override fun toBytes(buf: ByteBuf?) {}

    override fun fromBytes(buf: ByteBuf?) {}

    class ShowFakeMenuMessageHandler : IMessageHandler<ShowFakeMenuMessage, IMessage> {
        override fun onMessage(message: ShowFakeMenuMessage?, ctx: MessageContext?): IMessage? {
            if (message == null)
                return null

            if (ctx?.side == Side.CLIENT) {
                FourthWallHelper.showFakeMenu()
            }

            return null
        }

    }
}