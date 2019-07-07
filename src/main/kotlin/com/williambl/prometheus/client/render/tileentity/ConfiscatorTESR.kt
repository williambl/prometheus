package com.williambl.prometheus.client.render.tileentity

import com.williambl.prometheus.common.tileentity.ConfiscatorTileEntity
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import org.lwjgl.opengl.GL11
import org.lwjgl.util.Color
import kotlin.math.roundToInt
import kotlin.math.sin

class ConfiscatorTESR : TileEntitySpecialRenderer<ConfiscatorTileEntity>() {

    val gradient = arrayOf(
            Color(0, 178, 200, 130),
            Color(0, 230, 127, 130)
    )

    override fun render(te: ConfiscatorTileEntity, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha)
        GlStateManager.pushMatrix()

        GlStateManager.translate(x, y + 0.5, z)
        GlStateManager.enableAlpha()
        GlStateManager.enableBlend()
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE)
        GlStateManager.disableLighting()
        GlStateManager.color(1f, 1f, 1f, 1f)
        GlStateManager.disableTexture2D()
        this.setLightmapDisabled(true)

        draw(te, x, y, z, partialTicks, destroyStage, alpha)

        this.setLightmapDisabled(false)
        GlStateManager.enableTexture2D()
        GlStateManager.enableLighting()
        GlStateManager.disableBlend()
        GlStateManager.disableAlpha()

        GlStateManager.popMatrix()
    }

    fun draw(te: ConfiscatorTileEntity, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        val tess = Tessellator.getInstance()
        val buf = tess.buffer

        val aabb = /*AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0) */te.aabb.offset(BlockPos.ORIGIN.subtract(te.pos))

        GlStateManager.pushMatrix()

        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)

        println(te.world.totalWorldTime + partialTicks)
        println(sin(te.world.totalWorldTime + partialTicks))
        drawHorizontalQuad(tess, buf, aabb.minX, aabb.maxX, aabb.minZ, aabb.maxZ, aabb.minY, getColourFromGradient(sin((te.world.totalWorldTime + partialTicks) / 20.0)))

        GlStateManager.popMatrix()
    }

    fun drawHorizontalQuad(tess: Tessellator, buf: BufferBuilder, minX: Double, maxX: Double, minZ: Double, maxZ: Double, y: Double, colour: Color) {
        GlStateManager.pushMatrix()
        GlStateManager.disableCull()
        buf.pos(minX, y, minZ).color(colour.red, colour.green, colour.blue, colour.alpha).endVertex()
        buf.pos(minX, y, maxZ).color(colour.red, colour.green, colour.blue, colour.alpha).endVertex()
        buf.pos(maxX, y, maxZ).color(colour.red, colour.green, colour.blue, colour.alpha).endVertex()
        buf.pos(maxX, y, minZ).color(colour.red, colour.green, colour.blue, colour.alpha).endVertex()
        tess.draw()
        GlStateManager.enableCull()
        GlStateManager.popMatrix()
    }

    override fun isGlobalRenderer(te: ConfiscatorTileEntity): Boolean {
        return true
    }

    fun getColourFromGradient(lerpAmount: Double): Color {
        val red = MathHelper.clampedLerp(gradient[0].red.toDouble(), gradient[1].red.toDouble(), lerpAmount).roundToInt()
        val green = MathHelper.clampedLerp(gradient[0].green.toDouble(), gradient[1].green.toDouble(), lerpAmount).roundToInt()
        val blue = MathHelper.clampedLerp(gradient[0].blue.toDouble(), gradient[1].blue.toDouble(), lerpAmount).roundToInt()
        val alpha = MathHelper.clampedLerp(gradient[0].alpha.toDouble(), gradient[1].alpha.toDouble(), lerpAmount).roundToInt()
        return Color(red, green, blue, alpha)
    }
}