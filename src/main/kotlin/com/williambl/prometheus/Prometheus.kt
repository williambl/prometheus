package com.williambl.prometheus

import com.williambl.prometheus.common.PrometheusProxy
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent

@Mod(modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter", modid = Prometheus.MODID,
        name = Prometheus.NAME, version = Prometheus.VERSION, dependencies = "required-after:forgelin")
object Prometheus {

    const val MODID = "prometheus"
    const val NAME = "Prometheus"
    const val VERSION = "1.0.0"

    @SidedProxy(
            clientSide = "com.williambl.prometheus.client.clientProxy",
            serverSide = "com.williambl.prometheus.server.serverProxy")
    lateinit var proxy: PrometheusProxy

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        proxy.preInit()
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        proxy.init()
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        proxy.postInit()
    }
}