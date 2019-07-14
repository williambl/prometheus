package com.williambl.prometheus.common.fourthwall

import com.williambl.prometheus.client.gui.GuiFakeIngameMenu
import net.minecraft.client.Minecraft

object FourthWallHelper {

    fun getCurrentUser(): String {
        return System.getProperty("user.name")
    }

    fun createErrorMessage(message: String, options: Array<String>) {
    }

    fun showFakeMenu() {
        val mc = Minecraft.getMinecraft()
        if (mc.currentScreen == null) {
            mc.displayGuiScreen(GuiFakeIngameMenu())

            if (mc.isSingleplayer && !mc.integratedServer?.public!!) {
                mc.soundHandler.pauseSounds()
            }
        }
    }

}

