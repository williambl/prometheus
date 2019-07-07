package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.block.OrientableTileEntityProviderBlock
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumParticleTypes
import net.minecraft.util.SoundCategory
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.world.WorldServer
import net.minecraftforge.energy.CapabilityEnergy
import java.util.function.Predicate

open class ConfiscatorTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 2500
    var capacity = 50000

    private var delay = 5
    private var counter = 0

    private val range = 3
    private val direction: EnumFacing by lazy { world.getBlockState(pos).getValue(OrientableTileEntityProviderBlock.facing) }
    private val aabb: AxisAlignedBB by lazy { AxisAlignedBB(pos.offset(direction, if (direction in EnumFacing.HORIZONTALS) range else 1)).grow(range.toDouble(), 0.0, range.toDouble()) }

    var disallowedConditions: MutableList<Predicate<ItemStack>> = mutableListOf(
            Predicate { stack: ItemStack -> stack.hasCapability(CapabilityEnergy.ENERGY, null) }
    )

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    override fun update() {
        if (!this.hasWorld() || world.isRemote)
            return

        if (energyStored < 10)
            return

        counter++
        if (counter < delay)
            return
        counter = 0

        world.getEntitiesWithinAABB(EntityPlayer::class.java, aabb).forEach { player ->
            getDisallowedItems(player.inventory).forEach {
                world.spawnEntity(createEntityItem(it, player))
                createParticles(player)
                world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.BLOCKS, 1.0f, 1.0f)
                it.count = 0
                extractEnergy(1000, false)
            }
        }
    }

    fun getDisallowedItems(inv: InventoryPlayer): List<ItemStack> {
        return inv.mainInventory.filter { isDisallowed(it) }
    }

    fun isDisallowed(stack: ItemStack): Boolean {
        return disallowedConditions.any { it.test(stack) }
    }

    fun createEntityItem(stack: ItemStack, player: EntityPlayer): EntityItem {
        val entity = EntityItem(world, player.posX, player.posY + 1, player.posZ, stack.copy())
        entity.lifespan = 80
        entity.setInfinitePickupDelay()
        entity.setNoGravity(true)
        entity.motionY = world.rand.nextDouble() * 0.05
        entity.velocityChanged = true
        return entity
    }

    fun createParticles(player: EntityPlayer) {
        (world as WorldServer).spawnParticle(EnumParticleTypes.FLAME, player.posX, player.posY + 1, player.posZ, 6, 0.0, 0.0, 0.0, 0.05)
    }
}
