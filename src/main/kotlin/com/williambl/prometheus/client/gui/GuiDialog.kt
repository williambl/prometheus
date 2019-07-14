package com.williambl.prometheus.client.gui

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import java.io.IOException

class GuiDialog(var message: String, var options: Array<String>, val timeToClose: Int = Int.MAX_VALUE, val finalAction: (GuiButton) -> Unit = {}) : GuiScreen() {
    private var saveStep: Int = 0
    private var visibleTime: Int = 0

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    override fun initGui() {
        this.saveStep = 0
        this.buttonList.clear()

        when (options.size) {
            1 -> this.buttonList.add(GuiButton(4, this.width / 2 - 100, this.height / 4 + 48 + -16, options[0]))
            2 -> {
                this.buttonList.add(GuiButton(0, this.width / 2 - 100, this.height / 4 + 48 + -16, 98, 20, options[0]))
                this
                        .buttonList.add(GuiButton(1, this.width / 2 + 2, this.height / 4 + 48 + -16, 98, 20, options[1]))
            }
            else -> {
            }
        }
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    @Throws(IOException::class)
    override fun actionPerformed(button: GuiButton) {
        closeMenu()
        finalAction(button)
    }

    fun closeMenu() {
        this.mc.displayGuiScreen(null as GuiScreen?)
        this.mc.setIngameFocus()
    }

    /**
     * Called from the main game loop to update the screen.
     */
    override fun updateScreen() {
        super.updateScreen()
        ++this.visibleTime

        if (this.visibleTime >= timeToClose) {
            this.mc.displayGuiScreen(null as GuiScreen?)
            this.mc.setIngameFocus()
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
