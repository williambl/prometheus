package com.williambl.prometheus.common.fourthwall

import com.williambl.prometheus.client.gui.GuiDialog
import com.williambl.prometheus.client.gui.GuiFakeIngameMenu
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiButton

object FourthWallHelper {

    var shouldMessUpTooltips = false

    val username: String by lazy { System.getProperty("user.name") }

    val finalActions: Array<(GuiButton?) -> Unit> = arrayOf(
            { it: GuiButton? -> print(it?.id) },
            { shouldMessUpTooltips = true }
    )

    fun showFakeMenu() {
        val mc = Minecraft.getMinecraft()
        if (mc.currentScreen == null) {
            mc.displayGuiScreen(GuiFakeIngameMenu())

            if (mc.isSingleplayer && !mc.integratedServer?.public!!) {
                mc.soundHandler.pauseSounds()
            }
        }
    }

    fun showDialog(message: String, options: Array<String> = arrayOf(), timeToClose: Int = Int.MAX_VALUE, finalAction: (GuiButton?) -> Unit = {}) {
        val mc = Minecraft.getMinecraft()
        if (mc.currentScreen == null) {
            mc.displayGuiScreen(GuiDialog(message, options, timeToClose, finalAction))

            if (mc.isSingleplayer && !mc.integratedServer?.public!!) {
                mc.soundHandler.pauseSounds()
            }
        }
    }

}

