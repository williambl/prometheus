package com.williambl.prometheus.common.world

import com.williambl.prometheus.Prometheus
import net.minecraft.util.ResourceLocation
import net.minecraft.util.Rotation
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.gen.structure.template.PlacementSettings
import net.minecraft.world.gen.structure.template.Template
import net.minecraftforge.fml.common.Loader

object AncientComplexGenerator {

    val maxRooms = 5
    val roomHeight = 32

    val entranceResourceLocation = ResourceLocation(Prometheus.MODID, "entrance")
    val exitResourceLocation = ResourceLocation(Prometheus.MODID, "exit")
    val rooms: List<Room> = listOf(
            Room("room1"),
            Room("room2")
    )

    fun generateComplex(world: World, chunk: ChunkPos) {
        generateRoom(world, chunk, 0, false, getTemplate(world, entranceResourceLocation))
        for (i in 1..maxRooms) {
            generateRoom(world, chunk, roomHeight * i, i % 2 == 1, getTemplate(world, rooms.randomValidRoom().location))
        }
        generateRoom(world, chunk, roomHeight * (maxRooms + 1), false, getTemplate(world, exitResourceLocation))
    }

    private fun generateRoom(world: World, chunk: ChunkPos, y: Int, flip: Boolean, template: Template) {
        var position = chunk.getBlock(8, y, 8)

        if (flip)
            position = position.add(31, 0, 31)
        print(position)

        val placementSettings = PlacementSettings()
                .setRotation(if (flip) Rotation.CLOCKWISE_180 else Rotation.NONE)
                .setIgnoreEntities(false)

        template.addBlocksToWorld(world, position, placementSettings)
    }

    private fun getTemplate(world: World, resourceLocation: ResourceLocation): Template {
        val templateManager = (world as WorldServer).structureTemplateManager
        return templateManager.getTemplate(world.minecraftServer, resourceLocation)
    }


    class Room(val location: ResourceLocation, val requiredMods: Array<String>) {
        constructor(resourceLocation: ResourceLocation) : this(resourceLocation, arrayOf())
        constructor(location: String, requiredMods: Array<String>) : this(ResourceLocation(Prometheus.MODID, location), requiredMods)
        constructor(location: String) : this(ResourceLocation(Prometheus.MODID, location), arrayOf())

        fun modsPresent(): Boolean {
            return requiredMods.all { Loader.isModLoaded(it) }
        }

    }

    private fun List<Room>.randomValidRoom(): Room {
        while (true) {
            return this.filter { it.modsPresent() }.random()
        }
    }
}