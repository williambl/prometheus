package com.williambl.prometheus.client.render.tileentity

import com.williambl.prometheus.common.tileentity.ConfiscatorTileEntity
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.math.BlockPos
import org.lwjgl.opengl.GL11

class ConfiscatorTESR : TileEntitySpecialRenderer<ConfiscatorTileEntity>() {

    override fun render(te: ConfiscatorTileEntity, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        super.render(te, x, y, z, partialTicks, destroyStage, alpha)
        GlStateManager.pushMatrix()

        GlStateManager.translate(x, y + 0.5, z)
        GlStateManager.disableLighting()
        GlStateManager.color(1f, 1f, 1f, 1f)
        GlStateManager.disableTexture2D()
        this.setLightmapDisabled(true)

        draw(te, x, y, z, partialTicks, destroyStage, alpha)

        this.setLightmapDisabled(false)
        GlStateManager.enableLighting()
        GlStateManager.enableTexture2D()

        GlStateManager.popMatrix()
    }

    fun draw(te: ConfiscatorTileEntity, x: Double, y: Double, z: Double, partialTicks: Float, destroyStage: Int, alpha: Float) {
        val tess = Tessellator.getInstance()
        val buf = tess.buffer

        val aabb = /*AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0) */te.aabb.offset(BlockPos.ORIGIN.subtract(te.pos))

        GlStateManager.pushMatrix()

        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR)

        drawHorizontalQuad(tess, buf, aabb.minX, aabb.maxX, aabb.minZ, aabb.maxZ, aabb.minY)

        GlStateManager.popMatrix()
    }

    fun drawHorizontalQuad(tess: Tessellator, buf: BufferBuilder, minX: Double, maxX: Double, minZ: Double, maxZ: Double, y: Double) {
        GlStateManager.pushMatrix()
        GlStateManager.disableCull()
        buf.pos(minX, y, minZ).color(0, 255, 127, 255).endVertex()
        buf.pos(minX, y, maxZ).color(0, 255, 127, 255).endVertex()
        buf.pos(maxX, y, maxZ).color(0, 255, 127, 255).endVertex()
        buf.pos(maxX, y, minZ).color(0, 255, 127, 255).endVertex()
        tess.draw()
        GlStateManager.enableCull()
        GlStateManager.popMatrix()
    }

    override fun isGlobalRenderer(te: ConfiscatorTileEntity): Boolean {
        return true
    }
}