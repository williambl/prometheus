package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.init.Blocks
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.energy.CapabilityEnergy
import org.lwjgl.input.Mouse

open class MultiBlockMasterTileEntity: BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 1000000
    private val multiBlockPositions: Array<BlockPos>

    var isValidMultiBlock: Boolean = false

    init {
        setMaxInput(input)
        setMaxOutput(output)
        setMaxEnergyStored(capacity)

        multiBlockPositions = getAllPositionsInMultiBlock()
    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        val wasValidMultiBlock = isValidMultiBlock

        isValidMultiBlock = checkMultiBlock(world, pos)

        if (isValidMultiBlock && !wasValidMultiBlock) {
            for (i in 0..50) {
                multiBlockPositions.forEach { blockPos ->
                    val particlePos = blockPos.add(pos)
                    world.spawnParticle(EnumParticleTypes.REDSTONE, particlePos.x.toDouble(), blockPos.y.toDouble(), blockPos.z.toDouble(), 0.0, 0.3, 0.0)
                }
            }
            println("now valid multiblock!")
        }
        if (!isValidMultiBlock && wasValidMultiBlock) {
            for (i in 0..50) {
                multiBlockPositions.forEach { blockPos ->
                    val particlePos = blockPos.add(pos)
                    world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, particlePos.x.toDouble()+world.rand.nextDouble(), blockPos.y.toDouble()+world.rand.nextDouble(), blockPos.z.toDouble()+world.rand.nextDouble(), 0.0, 0.3, 0.0)
                }
            }
            println("no longer valid multiblock!")
        }
    }

    private fun checkMultiBlock(world: World, pos: BlockPos) : Boolean {
        multiBlockPositions.forEach { blockPos ->
            val checkingPos = blockPos.add(pos)
            if (world.getBlockState(checkingPos).block != Blocks.BEDROCK && checkingPos != pos)
                return false
        }
        return true
    }

    private fun getAllPositionsInMultiBlock(): Array<BlockPos> {
        val posArray =  arrayOfNulls<BlockPos>(27)

        var i = 0
        for (y in -2..0) {
            for (x in -1..1) {
                for (z in -1..1) {
                    posArray[i] = BlockPos(x, y, z)
                    i++
                }
            }
        }

        return posArray as Array<BlockPos>
    }

}
