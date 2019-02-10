package com.williambl.prometheus.common.multiblock

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos

class MultiBlock(val blocks: Array<MultiBlockBlockInfo>) {

    fun getOffsetBlockInfo(originalIndex: Int, offset: BlockPos): MultiBlockBlockInfo {
        val original = blocks[originalIndex]
        return original.copy(pos = original.pos.add(offset))
    }

    fun getBlockInfo(index: Int): MultiBlockBlockInfo {
        return blocks[index]
    }

    fun getAllOffsetBlockInfos(offset: BlockPos): Array<MultiBlockBlockInfo> {
        val newBlocks = arrayOfNulls<MultiBlockBlockInfo>(blocks.size)
        var i = 0
        blocks.forEach { blockInfo ->
            newBlocks[i] = blockInfo.copy(pos = blockInfo.pos.add(offset))
            i++
        }

        return newBlocks as Array<MultiBlockBlockInfo>
    }

    fun getAllBlockInfos(): Array<MultiBlockBlockInfo> {
        return blocks
    }

    data class MultiBlockBlockInfo(val pos: BlockPos, val blockState: IBlockState)
}