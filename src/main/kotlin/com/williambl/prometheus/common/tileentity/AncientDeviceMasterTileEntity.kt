package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.multiblock.ModMultiBlocks
import com.williambl.prometheus.common.multiblock.MultiBlock
import com.williambl.prometheus.common.tileentity.base.BaseMultiBlockMasterTileEntity
import net.minecraft.entity.monster.EntityGiantZombie

open class AncientDeviceMasterTileEntity : BaseMultiBlockMasterTileEntity(1000000, 0, 0) {

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

    override fun activateMultiBlock() {
        getMultiBlockRepresentation().blocks.forEach { bi ->
            if (bi.pos != pos)
                world.setBlockToAir(bi.pos)
        }

        val giant = EntityGiantZombie(world)
        giant.setPosition(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        world.spawnEntity(giant)
        world.setBlockToAir(pos)
    }

    override fun getMultiBlockRepresentation(): MultiBlock {
        if (!isMultiBlockVarInitialised())
            multiBlock = MultiBlock(ModMultiBlocks.ancientDevice.getAllOffsetBlockInfos(pos))
        return multiBlock
    }
}
