package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock
import com.williambl.prometheus.common.entity.EntityAncientDrone
import com.williambl.prometheus.common.multiblock.ModMultiBlocks
import com.williambl.prometheus.common.multiblock.MultiBlock
import com.williambl.prometheus.common.tileentity.base.BaseMultiBlockMasterTileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class AncientDroneSpawnerTileEntity : BaseMultiBlockMasterTileEntity(1000000, 0, 0) {

    override val multiBlock: MultiBlock by lazy {
        MultiBlock(ModMultiBlocks.ancientDroneSpawner.getAllOffsetBlockInfos(pos))
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
        val drone = EntityAncientDrone(world)
        drone.setPosition(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        world.spawnEntity(drone)
    }

    fun setCompleteBlockstate(world: World, pos: BlockPos, value: Boolean) {
        val blockstate = world.getBlockState(pos)
        world.setBlockState(pos, blockstate.withProperty(BaseMultiBlockMasterBlock.complete, value))
    }
}

