package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.init.Blocks
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class MultiBlockMasterTileEntity: BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 1000000
    private var multiBlockPositions: Array<BlockPos>? = null

    var isValidMultiBlock: Boolean = false

    init {
        setMaxInput(input)
        setMaxOutput(output)
        setMaxEnergyStored(capacity)

    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        val wasValidMultiBlock = isValidMultiBlock

        isValidMultiBlock = checkMultiBlock(world, pos)

        if (isValidMultiBlock && !wasValidMultiBlock) {
            for (i in 0..50) {
                getMultiBlockPositions().forEach { blockPos ->
                    world.spawnParticle(EnumParticleTypes.REDSTONE, blockPos.x.toDouble(), blockPos.y.toDouble(), blockPos.z.toDouble(), 0.0, 0.3, 0.0)
                }
            }
            println("now valid multiblock!")
        }
        if (!isValidMultiBlock && wasValidMultiBlock) {
            for (i in 0..50) {
                getMultiBlockPositions().forEach { blockPos ->
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, blockPos.x.toDouble(), blockPos.y.toDouble(), blockPos.z.toDouble(), 0.0, 0.3, 0.0)
                }
            }
            println("no longer valid multiblock!")
        }
    }

    private fun checkMultiBlock(world: World, pos: BlockPos) : Boolean {
        getMultiBlockPositions().forEach { checkingPos ->
            if (world.getBlockState(checkingPos).block != Blocks.BEDROCK && checkingPos != pos)
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
