package com.williambl.prometheus.common.world

import net.minecraft.block.Block
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.gen.feature.WorldGenerator
import net.minecraft.world.gen.structure.template.Template
import java.util.*
import net.minecraft.world.gen.structure.template.TemplateManager
import net.minecraft.block.state.IBlockState
import net.minecraft.util.Mirror
import net.minecraft.util.Rotation
import net.minecraft.util.math.MathHelper
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.IChunkGenerator
import net.minecraftforge.fml.common.IWorldGenerator
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.gen.structure.template.PlacementSettings



class AncientDeviceGenerator : IWorldGenerator {

    val resourceLocation = ResourceLocation("prometheus", "ancient_device")

    lateinit var chunkCoords: Array<Int>

    override fun generate(random: Random?, chunkX: Int, chunkZ: Int, world: World?, chunkGenerator: IChunkGenerator?, chunkProvider: IChunkProvider?) {
        if (checkIfChunkIsCorrect(world!!, chunkX, chunkZ)) {
            generateStructure(world, chunkX, chunkZ, getTemplate(world))
        }
    }

    private fun generateStructure(world: World, chunkX: Int, chunkZ: Int, template: Template) {
        val position = BlockPos(chunkX*16+8, world.rand.nextInt(64), chunkZ*16+8)

        val placementSettings = PlacementSettings().setMirror(Mirror.NONE)
                .setRotation(Rotation.NONE).setIgnoreEntities(false).setChunk((null as ChunkPos?)!!)
                .setReplacedBlock((null as Block?)!!).setIgnoreStructureBlock(false)

        template.addBlocksToWorld(world, position, placementSettings)
    }

    private fun checkIfChunkIsCorrect(world: World, chunkX: Int, chunkZ: Int): Boolean {
        val correctChunkCoords = findChunkCoords(world)
        return chunkX == correctChunkCoords[0] && chunkZ == correctChunkCoords[1]
    }

    fun findChunkCoords(world: World): Array<Int> {
        if (!::chunkCoords.isInitialized) {
            val seed = world.seed
            val chunkX: Int = (MathHelper.sin((seed / 30).toFloat()) * 30).toInt()
            val chunkY: Int = (MathHelper.cos((seed / 15).toFloat()) * 50).toInt()
            chunkCoords = arrayOf(chunkX, chunkY)
        }
        return chunkCoords
    }

    private fun getTemplate(world: World): Template {
        val templateManager = (world as WorldServer).structureTemplateManager
        return templateManager.getTemplate(world.minecraftServer, resourceLocation)
    }
}