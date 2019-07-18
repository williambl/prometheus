package com.williambl.prometheus.common.networking

import com.williambl.prometheus.Prometheus
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.relauncher.Side

object ModPackets {

    val instance = NetworkRegistry.INSTANCE.newSimpleChannel(Prometheus.MODID)

    fun registerPackets() {
        var discriminator = 0
        instance.registerMessage(ShowDialogMessage.ShowDialogMessageHandler::class.java, ShowDialogMessage::class.java, discriminator++, Side.CLIENT)
        instance.registerMessage(ShowFakeMenuMessage.ShowFakeMenuMessageHandler::class.java, ShowFakeMenuMessage::class.java, discriminator++, Side.CLIENT)
    }
}