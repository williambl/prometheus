package com.williambl.prometheus.common.tileentity.base

import com.williambl.prometheus.common.multiblock.ModMultiBlocks
import com.williambl.prometheus.common.multiblock.MultiBlock
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

open class BaseMultiBlockMasterTileEntity(maxInput: Int, maxOutput: Int, maxStored: Int) : BaseEnergyTileEntity() {

    protected open val multiBlock: MultiBlock by lazy {
        MultiBlock(arrayOf(ModMultiBlocks.createBlockInfo(0, 0, 0, Blocks.AIR)))
    }

    var isValidMultiBlock: Boolean = false

    init {
        setMaxInput(maxInput)
        setMaxOutput(maxOutput)
        maxEnergyStored = maxStored
    }

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        isValidMultiBlock = checkMultiBlock(world, pos)
    }

    open fun activateMultiBlock() {}

    protected open fun checkMultiBlock(world: World, pos: BlockPos): Boolean {
        multiBlock.getAllBlockInfos().forEach { checkingBI ->
            if (world.getBlockState(checkingBI.pos) != checkingBI.blockState)
                return false
        }
        return true
    }

}

