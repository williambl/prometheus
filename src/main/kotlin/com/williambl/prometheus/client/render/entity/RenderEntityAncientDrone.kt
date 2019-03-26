package com.williambl.prometheus.client.render.entity

import com.williambl.prometheus.Prometheus
import com.williambl.prometheus.common.entity.EntityAncientDrone
import net.minecraft.client.model.ModelZombie
import net.minecraft.client.renderer.entity.Render
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.util.ResourceLocation
import net.minecraftforge.fml.client.registry.IRenderFactory


class RenderEntityAncientDrone(renderManager: RenderManager) : RenderLiving<EntityAncientDrone>(renderManager, ModelZombie(), 0.5f) {

    companion object Factory : IRenderFactory<EntityAncientDrone> {
        override fun createRenderFor(manager: RenderManager): Render<in EntityAncientDrone> {
            return RenderEntityAncientDrone(manager)
        }
    }

    private val mobTexture = ResourceLocation(Prometheus.MODID, "textures/entity/ancient_drone.png")

    override fun getEntityTexture(entity: EntityAncientDrone): ResourceLocation {
        return mobTexture
    }

}
