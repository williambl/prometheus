package com.williambl.prometheus.common.item

import com.williambl.prometheus.common.item.base.BaseItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraft.world.gen.structure.template.PlacementSettings

class DebugItem(registryName: String, creativeTab: CreativeTabs) : BaseItem(registryName, creativeTab) {

    override fun onItemUse(player: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult {
        if (worldIn.isRemote)
            return EnumActionResult.PASS
        val templateManager = (worldIn as WorldServer).structureTemplateManager
        val template = templateManager.getTemplate(worldIn.minecraftServer, ResourceLocation("prometheus", "ancient_device"))

        val placementSettings = PlacementSettings().setMirror(Mirror.NONE)
                .setRotation(Rotation.NONE).setIgnoreEntities(false)

        template.addBlocksToWorld(worldIn, pos, placementSettings)
        return EnumActionResult.SUCCESS
    }
}