package com.williambl.prometheus.common.block

import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs

class BaseBlock(registryName: String, tab: CreativeTabs, soundType: SoundType, hardness: Float, resistance: Float,
                lightLevel: Float, material: Material) : Block(material) {
    init {
        this.setCreativeTab(tab)
        this.setHardness(hardness)
        this.setResistance(resistance)
        this.setRegistryName(registryName)
        this.unlocalizedName = this.registryName.toString()
        this.setLightLevel(lightLevel)
        this.soundType = soundType
    }

}