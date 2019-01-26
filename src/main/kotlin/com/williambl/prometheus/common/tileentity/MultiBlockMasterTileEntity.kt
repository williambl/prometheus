package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.ModBlocks
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.effect.EntityLightningBolt
import net.minecraft.entity.monster.EntityGiantZombie
import net.minecraft.init.Blocks
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class MultiBlockMasterTileEntity: BaseEnergyTileEntity() {

    private var multiBlockPositions: Array<BlockPos>? = null

    var isValidMultiBlock: Boolean = false

    init {
        setMaxInput(1000000)
        setMaxOutput(0)
        setMaxEnergyStored(0)

    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        val wasValidMultiBlock = isValidMultiBlock

        isValidMultiBlock = checkMultiBlock(world, pos)

        if (isValidMultiBlock && !wasValidMultiBlock) {
            maxEnergyStored = 1000000
            println("now valid multiblock!")
        }
        if (!isValidMultiBlock && wasValidMultiBlock) {
            maxEnergyStored = 0
            println("no longer valid multiblock!")
        }
    }

    fun activateMultiBlock() {
        getMultiBlockPositions().forEach { position ->
            if (position != pos)
                world.setBlockToAir(position)
        }

        val giant = EntityGiantZombie(world)
        giant.setPosition(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        world.spawnEntity(giant)
        world.setBlockToAir(pos)
    }

    private fun checkMultiBlock(world: World, pos: BlockPos) : Boolean {
        getMultiBlockPositions().forEach { checkingPos ->
            if (world.getBlockState(checkingPos).block != ModBlocks.ancientCircuitry && checkingPos != pos)
                return false
        }
        return true
    }

    private fun getMultiBlockPositions(): Array<BlockPos> {
        if (multiBlockPositions != null)
            return multiBlockPositions as Array<BlockPos>

        val posList = emptyList<BlockPos>().toMutableList()

        for (y in -2..0) {
            for (x in -1..1) {
                for (z in -1..1) {
                    posList.add(pos.add(x, y, z))
                }
            }
        }

        return posList.toTypedArray()
    }

}
