package com.williambl.prometheus.client

import com.williambl.prometheus.client.render.ModModels
import com.williambl.prometheus.common.PrometheusProxy

class ClientProxy : PrometheusProxy() {

    override fun preInit() {
        super.preInit()
        ModModels.registerEntityModels()
    }

}