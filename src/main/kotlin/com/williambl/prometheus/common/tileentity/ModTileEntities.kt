package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.Prometheus
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.tileentity.TileEntity

object ModTileEntities {

    fun registerTileEntities() {
        registerTileEntity(EnergyStoreTileEntity::class.java)
    }

    private fun registerTileEntity(tileEntityClass: Class<out TileEntity>) {
        GameRegistry.registerTileEntity(tileEntityClass, Prometheus.MODID + ":" + tileEntityClass.simpleName.replaceFirst("TileEntity", ""))
    }
}