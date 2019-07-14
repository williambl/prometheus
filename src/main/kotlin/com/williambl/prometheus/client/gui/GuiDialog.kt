package com.williambl.prometheus.client.gui

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import java.io.IOException

class GuiDialog(var message: String, var option1: String, var option2: String) : GuiScreen() {
    private var saveStep: Int = 0
    private var visibleTime: Int = 0

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    override fun initGui() {
        this.saveStep = 0
        this.buttonList.clear()

        this.buttonList.add(GuiButton(0, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, option1))
        this.buttonList.add(GuiButton(1, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, option2))
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Throws(IOException::class)
    override fun actionPerformed(button: GuiButton) {
        this.mc.displayGuiScreen(null as GuiScreen?)
        this.mc.setIngameFocus()
    }

    /**
     * Called from the main game loop to update the screen.
     */
    override fun updateScreen() {
        super.updateScreen()
        ++this.visibleTime

        if (this.visibleTime % 40 == 0) {
            val button = this.buttonList.random()
            button.displayString = "§k" + button.displayString
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.drawDefaultBackground()
        this.drawCenteredString(this.fontRenderer, message, this.width / 2, 40, 16777215)
        super.drawScreen(mouseX, mouseY, partialTicks)
    }
}
