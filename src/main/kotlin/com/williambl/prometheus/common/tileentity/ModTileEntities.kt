package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.Prometheus
import net.minecraft.tileentity.TileEntity
import net.minecraftforge.fml.common.registry.GameRegistry

object ModTileEntities {

    fun registerTileEntities() {
        registerTileEntity(EnergyStoreTileEntity::class.java)
        registerTileEntity(AncientDeviceMasterTileEntity::class.java)
        registerTileEntity(AncientDroneSpawnerTileEntity::class.java)
    }

    private fun registerTileEntity(tileEntityClass: Class<out TileEntity>) {
        GameRegistry.registerTileEntity(tileEntityClass, Prometheus.MODID + ":" + tileEntityClass.simpleName.replaceFirst("TileEntity", ""))
    }
}