package com.williambl.prometheus.common

import com.williambl.prometheus.common.tileentity.ModTileEntities
import com.williambl.prometheus.common.world.ModWorld

open class PrometheusProxy {

    open fun preInit() {}

    open fun init() {
        ModTileEntities.registerTileEntities()
        ModWorld.registerWorldGenerators()
    }

    open fun postInit() {}
}