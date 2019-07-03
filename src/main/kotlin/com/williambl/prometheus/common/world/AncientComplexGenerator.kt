package com.williambl.prometheus.common.world

import com.williambl.prometheus.Prometheus
import net.minecraft.util.Mirror
import net.minecraft.util.ResourceLocation
import net.minecraft.util.Rotation
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.gen.structure.template.PlacementSettings
import net.minecraft.world.gen.structure.template.Template

object AncientComplexGenerator {

    val roomResourceLocations = listOf(ResourceLocation(Prometheus.MODID, "room1"), ResourceLocation(Prometheus.MODID, "room2"))
    val maxRooms = 5

    fun generateComplex(world: World, chunk: ChunkPos) {
        for (i in 1..maxRooms) {
            generateStructure(world, chunk, 30 * i, getTemplate(world, roomResourceLocations.random()))
        }
    }

    private fun generateStructure(world: World, chunk: ChunkPos, y: Int, template: Template) {
        val position = chunk.getBlock(8, y, 8)

        val placementSettings = PlacementSettings().setMirror(Mirror.NONE)
                .setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk(chunk)

        template.addBlocksToWorld(world, position, placementSettings)
    }

    private fun getTemplate(world: World, resourceLocation: ResourceLocation): Template {
        val templateManager = (world as WorldServer).structureTemplateManager
        return templateManager.getTemplate(world.minecraftServer, resourceLocation)
    }
}