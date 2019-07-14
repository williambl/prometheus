package com.williambl.prometheus.client.gui

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.resources.I18n
import java.io.IOException

class GuiFakeIngameMenu : GuiScreen() {
    private var saveStep: Int = 0
    private var visibleTime: Int = 0

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    override fun initGui() {
        this.saveStep = 0
        this.buttonList.clear()
        val i = -16
        val j = 98
        this.buttonList.add(GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + -16, I18n.format("menu.returnToMenu")))

        if (!this.mc.isIntegratedServerRunning) {
            this.buttonList[0].displayString = I18n.format("menu.disconnect")
        }

        this.buttonList.add(GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + -16, "Back to Game"))
        this.buttonList.add(GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + -16, 98, 20, I18n.format("menu.options")))
        this.buttonList.add(GuiButton(12, this.width / 2 + 2, this.height / 4 + 96 + i, 98, 20, I18n.format("fml.menu.modoptions")))
        val guibutton = this.addButton(GuiButton(7, this.width / 2 - 100, this.height / 4 + 72 + -16, 200, 20, I18n.format("menu.shareToLan", *arrayOfNulls(0))))
        guibutton.enabled = this.mc.isSingleplayer && !this.mc.integratedServer!!.public
        this.buttonList.add(GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, I18n.format("gui.advancements")))
        this.buttonList.add(GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, I18n.format("gui.stats")))
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Throws(IOException::class)
    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            1 -> {
                this.buttonList.forEach { it.displayString = "You cannot leave." }
            }
            4 -> {
                this.buttonList.forEach { it.displayString = "You cannot leave." }
            }
            else -> {
            }
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    override fun updateScreen() {
        super.updateScreen()
        ++this.visibleTime

        if (this.visibleTime % 10 == 0) {
            val button = this.buttonList.random()
            button.displayString = "§k" + button.displayString
        }

        if (this.visibleTime >= 100) {
            this.mc.displayGuiScreen(null as GuiScreen?)
            this.mc.setIngameFocus()
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.drawDefaultBackground()
        this.drawCenteredString(this.fontRenderer, I18n.format("menu.game"), this.width / 2, 40, 16777215)
        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}