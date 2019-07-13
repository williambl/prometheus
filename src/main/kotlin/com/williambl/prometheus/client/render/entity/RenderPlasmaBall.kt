package com.williambl.prometheus.client.render.entity

import com.williambl.prometheus.common.entity.EntityPlasmaBall
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation

class RenderPlasmaBall(renderManagerIn: RenderManager) : Render<EntityPlasmaBall>(renderManagerIn) {

    private val PLASMA_BALL_TEXTURE = ResourceLocation("textures/entity/enderdragon/dragon_fireball.png")

    /**
     * Renders the desired `T` type Entity.
     */
    override fun doRender(entity: EntityPlasmaBall, x: Double, y: Double, z: Double, entityYaw: Float, partialTicks: Float) {
        GlStateManager.pushMatrix()
        this.bindEntityTexture(entity)
        GlStateManager.translate(x.toFloat(), y.toFloat(), z.toFloat())
        GlStateManager.enableRescaleNormal()
        GlStateManager.scale(2.0f, 2.0f, 2.0f)
        val tessellator = Tessellator.getInstance()
        val bufferbuilder = tessellator.buffer
        val f = 1.0f
        val f1 = 0.5f
        val f2 = 0.25f
        GlStateManager.rotate(180.0f - this.renderManager.playerViewY, 0.0f, 1.0f, 0.0f)
        GlStateManager.rotate((if (this.renderManager.options.thirdPersonView == 2) -1 else 1).toFloat() * -this.renderManager.playerViewX, 1.0f, 0.0f, 0.0f)

        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial()
            GlStateManager.enableOutlineMode(this.getTeamColor(entity))
        }

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL)
        bufferbuilder.pos(-0.5, -0.25, 0.0).tex(0.0, 1.0).normal(0.0f, 1.0f, 0.0f).endVertex()
        bufferbuilder.pos(0.5, -0.25, 0.0).tex(1.0, 1.0).normal(0.0f, 1.0f, 0.0f).endVertex()
        bufferbuilder.pos(0.5, 0.75, 0.0).tex(1.0, 0.0).normal(0.0f, 1.0f, 0.0f).endVertex()
        bufferbuilder.pos(-0.5, 0.75, 0.0).tex(0.0, 0.0).normal(0.0f, 1.0f, 0.0f).endVertex()
        tessellator.draw()

        if (this.renderOutlines) {
            GlStateManager.disableOutlineMode()
            GlStateManager.disableColorMaterial()
        }

        GlStateManager.disableRescaleNormal()
        GlStateManager.popMatrix()
        super.doRender(entity, x, y, z, entityYaw, partialTicks)
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    override fun getEntityTexture(entity: EntityPlasmaBall): ResourceLocation {
        return PLASMA_BALL_TEXTURE
    }
}