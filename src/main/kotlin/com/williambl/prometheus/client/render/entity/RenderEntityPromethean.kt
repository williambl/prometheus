package com.williambl.prometheus.client.render.entity

import com.williambl.prometheus.Prometheus
import com.williambl.prometheus.common.entity.EntityPromethean
import net.minecraft.client.model.ModelZombie
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.client.registry.IRenderFactory


class RenderEntityPromethean(renderManager: RenderManager) : RenderLiving<EntityPromethean>(renderManager, ModelZombie(), 0.5f) {

    companion object Factory : IRenderFactory<EntityPromethean> {
        override fun createRenderFor(manager: RenderManager): Render<in EntityPromethean> {
            return RenderEntityPromethean(manager)
        }
    }

    private val mobTexture = ResourceLocation(Prometheus.MODID, "textures/entity/promethean.png")

    override fun getEntityTexture(entity: EntityPromethean): ResourceLocation {
        return mobTexture
    }

}
