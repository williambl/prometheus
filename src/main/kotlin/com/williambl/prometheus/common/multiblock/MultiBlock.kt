package com.williambl.prometheus.common.multiblock

import net.minecraft.block.state.IBlockState
import net.minecraft.util.math.BlockPos

class MultiBlock(val blocks: Array<MultiBlockBlockInfo>) {

    fun getOffsetBlockInfo(originalIndex: Int, offset: BlockPos): MultiBlockBlockInfo {
        val original = blocks[originalIndex]
        return MultiBlockBlockInfo(original.pos.add(offset), original.blockState)
    }

    fun getBlockInfo(index: Int): MultiBlockBlockInfo {
        return blocks[index]
    }

    data class MultiBlockBlockInfo(val pos: BlockPos, val blockState: IBlockState)
}