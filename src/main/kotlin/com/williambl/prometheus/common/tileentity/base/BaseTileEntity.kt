package com.williambl.prometheus.common.tileentity.base

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ITickable

abstract class BaseTileEntity : TileEntity(), ITickable {

    abstract override fun update()

}