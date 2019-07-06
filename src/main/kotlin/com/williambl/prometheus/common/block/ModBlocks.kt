package com.williambl.prometheus.common.block

import com.williambl.prometheus.common.block.base.BaseBlock
import com.williambl.prometheus.common.block.base.BaseLightBlock
import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock
import com.williambl.prometheus.common.block.base.BaseTileEntityProviderBlock
import com.williambl.prometheus.common.tileentity.AncientDeviceMasterTileEntity
import com.williambl.prometheus.common.tileentity.AncientDroneSpawnerTileEntity
import com.williambl.prometheus.common.tileentity.GravityWellTileEntity
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@Mod.EventBusSubscriber
object ModBlocks {

    val ancientDeviceMaster = BaseMultiBlockMasterBlock("ancient_core", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.0f, Material.IRON, ::AncientDeviceMasterTileEntity)
    val ancientCircuitry = BaseBlock("ancient_circuitry", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.5f, Material.IRON)
    val ancientCasing = BaseBlock("ancient_casing", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON)
    val ancientDroneSpawner = BaseTileEntityProviderBlock("ancient_drone_spawner", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0.3f, Material.IRON, ::AncientDroneSpawnerTileEntity)
    val ancientLight = BaseLightBlock("ancient_light", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 15, Material.IRON)
    val gravityWell = OrientableTileEntityProviderBlock("gravity_well", CreativeTabs.REDSTONE, SoundType.METAL, 3.0f, 4.0f, 0f, Material.IRON, ::GravityWellTileEntity)
    val ancientConcrete = BaseBlock("ancient_concrete", CreativeTabs.BUILDING_BLOCKS, SoundType.STONE, 5.0f, 5.0f, 0.0f, Material.ROCK)

    val blocks = listOf<Block>(ancientDeviceMaster, ancientCircuitry, ancientCasing, ancientDroneSpawner, ancientLight, gravityWell, ancientConcrete)

    @SubscribeEvent
    @JvmStatic
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.registerAll(*blocks.toTypedArray())
        println("registering blocks...")
    }

    @SubscribeEvent
    @JvmStatic
    fun registerItemBlocks(event: RegistryEvent.Register<Item>) {
        blocks.forEach { block ->
            val itemBlock = ItemBlock(block).setRegistryName(block.registryName)
            event.registry.register(itemBlock)
        }
        println("registering itemblocks...")
    }

}