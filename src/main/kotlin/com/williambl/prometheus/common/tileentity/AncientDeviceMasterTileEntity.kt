package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock
import com.williambl.prometheus.common.multiblock.ModMultiBlocks
import com.williambl.prometheus.common.multiblock.MultiBlock
import com.williambl.prometheus.common.tileentity.base.BaseMultiBlockMasterTileEntity
import com.williambl.prometheus.common.world.AncientComplexGenerator
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.World

open class AncientDeviceMasterTileEntity : BaseMultiBlockMasterTileEntity(1000000, 0, 0) {

    override val multiBlock: MultiBlock by lazy {
        MultiBlock(ModMultiBlocks.ancientDevice.getAllOffsetBlockInfos(pos))
    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        val wasValidMultiBlock = isValidMultiBlock

        isValidMultiBlock = checkMultiBlock(world, pos)

        if (isValidMultiBlock && !wasValidMultiBlock) {
            maxEnergyStored = 500000000
            setCompleteBlockstate(world, pos, true)
            println("now valid multiblock!")
        }
        if (!isValidMultiBlock && wasValidMultiBlock) {
            maxEnergyStored = 0
            setCompleteBlockstate(world, pos, false)
            println("no longer valid multiblock!")
        }
    }

    override fun activateMultiBlock() {
        multiBlock.blocks.forEach { bi ->
            world.setBlockToAir(bi.pos)
        }
        AncientComplexGenerator.generateComplex(world, ChunkPos(pos))
    }

    fun setCompleteBlockstate(world: World, pos: BlockPos, value: Boolean) {
        val blockstate = world.getBlockState(pos)
        world.setBlockState(pos, blockstate.withProperty(BaseMultiBlockMasterBlock.complete, value))
    }
}

