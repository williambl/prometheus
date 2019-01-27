package com.williambl.prometheus.common.world

import net.minecraftforge.fml.common.registry.GameRegistry

object ModWorld {

    val ancientDeviceGenerator = AncientDeviceGenerator()

    fun registerWorldGenerators() {
        GameRegistry.registerWorldGenerator(ancientDeviceGenerator, 0)
    }
}