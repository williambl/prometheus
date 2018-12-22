package com.williambl.prometheus.common.item.base

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

class BaseItem(registryName: String, creativeTab: CreativeTabs): Item() {

    init {
        this.creativeTab = creativeTab
        this.setRegistryName(registryName)
        this.unlocalizedName = this.registryName.toString()
    }

}