package com.williambl.prometheus.common.tileentity

import com.sun.org.apache.xpath.internal.operations.Mult
import com.williambl.prometheus.common.block.ModBlocks
import com.williambl.prometheus.common.multiblock.ModMultiBlocks
import com.williambl.prometheus.common.multiblock.MultiBlock
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.effect.EntityLightningBolt
import net.minecraft.entity.monster.EntityGiantZombie
import net.minecraft.init.Blocks
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class MultiBlockMasterTileEntity: BaseEnergyTileEntity() {

    private lateinit var multiBlockPositions: Array<BlockPos>

    private lateinit var multiBlock: MultiBlock

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
        getMultiBlock().blocks.forEach { bi ->
            if (bi.pos != pos)
                world.setBlockToAir(bi.pos)
        }

        val giant = EntityGiantZombie(world)
        giant.setPosition(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
        world.spawnEntity(giant)
        world.setBlockToAir(pos)
    }

    private fun checkMultiBlock(world: World, pos: BlockPos) : Boolean {
        getMultiBlock().getAllBlockInfos().forEach { checkingBI->
            if (world.getBlockState(checkingBI.pos) != checkingBI.blockState)
                return false
        }
        return true
    }

    private fun getMultiBlock(): MultiBlock {
        if (!::multiBlock.isInitialized)
            multiBlock = MultiBlock(ModMultiBlocks.ancientDevice.getAllOffsetBlockInfos(pos))
        return multiBlock
    }

}

