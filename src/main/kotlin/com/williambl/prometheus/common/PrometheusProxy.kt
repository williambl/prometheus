package com.williambl.prometheus.common

import com.williambl.prometheus.common.tileentity.ModTileEntities

open class PrometheusProxy {

    open fun preInit() {}

    open fun init() {
        ModTileEntities.registerTileEntities()
    }

    open fun postInit() {}
}