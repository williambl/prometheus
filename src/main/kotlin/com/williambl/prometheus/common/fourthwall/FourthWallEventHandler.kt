package com.williambl.prometheus.common.fourthwall

import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object FourthWallEventHandler {

    @SubscribeEvent
    @JvmStatic
    fun tooltipEvent(e: ItemTooltipEvent) {
        if (FourthWallHelper.shouldMessUpTooltips)
            e.toolTip.replaceAll { "§k$it" }
    }

}