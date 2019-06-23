package com.williambl.prometheus.common.entity

import com.williambl.prometheus.Prometheus
import com.williambl.prometheus.common.entity.ai.EntityAIPrometheanAttack
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.ai.attributes.AttributeModifier
import net.minecraft.entity.effect.EntityLightningBolt
import net.minecraft.entity.monster.EntityIronGolem
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.pathfinding.PathNavigateGround
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.DamageSource
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.energy.CapabilityEnergy
import java.util.*

class EntityAncientDrone(worldIn: World) : EntityMob(worldIn) {

    companion object {
        val loot: ResourceLocation = ResourceLocation(Prometheus.MODID, "entities/ancient_drone")
        val maxRFRange: Int = 8
        val speedModifierUUID = UUID.fromString("d570087e-4cfb-11e9-8646-d663bd873d93")
        val speedModifier = AttributeModifier(speedModifierUUID, "health speed boost", 0.3, 0)
    }

    init {
        this.health = this.maxHealth
        this.setSize(0.9F, 3.5F)
        this.isImmuneToFire = true
        (this.getNavigator() as PathNavigateGround).canSwim = true
        this.experienceValue = 128
    }

    override fun onLivingUpdate() {
        super.onLivingUpdate()
        if (world.isRemote)
            return

        if (health < maxHealth)
            implodeRF()
        updateEntityAttributes()
    }

    private fun implodeRF() {
        BlockPos.getAllInBox(position.add(-maxRFRange, -maxRFRange, -maxRFRange), position.add(maxRFRange, maxRFRange, maxRFRange)).forEach { blockPos ->
            if (!world.getBlockState(blockPos).block.hasTileEntity(world.getBlockState(blockPos)))
                return@forEach

            val te: TileEntity? = world.getTileEntity(blockPos)

            val facing = EnumFacing.random(rand)
            if (te != null && te.hasCapability(CapabilityEnergy.ENERGY, facing)) {
                println(te.displayName?.plus("valid 1"))
                val giver = te.getCapability(CapabilityEnergy.ENERGY, facing)
                println(te.displayName?.plus(giver?.energyStored))

                if (giver != null && giver.energyStored >= 800000) {
                    println(te.displayName?.plus("valid 2, extracting..."))
                    world.setBlockToAir(blockPos)
                    world.newExplosion(this, blockPos.x.toDouble(), blockPos.y.toDouble(), blockPos.z.toDouble(), (Math.log(giver.energyStored.toDouble())/4).toFloat(), true, true)
                    println(health)
                    health += (giver.energyStored / 200000)
                    println(health)
                    return@implodeRF
                }
            }
        }
    }

    override fun onStruckByLightning(lightningBolt: EntityLightningBolt) {
        super.onStruckByLightning(lightningBolt)
        if (health > 32)
            world.newExplosion(this, posX, posY, posZ, 4F, true, true)
        health -= 32
    }

    override fun isEntityInvulnerable(source: DamageSource): Boolean {
        return super.isEntityInvulnerable(source)
                || source.isFireDamage
                || source.isExplosion
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun isPushedByWater(): Boolean {
        return false
    }

    private fun updateEntityAttributes() {
        if (health > 32) {
            if (!this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(speedModifier))
                this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(speedModifier)
        } else
            this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(speedModifier)

    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).baseValue = 128.0
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).baseValue = 64.0
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.13
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 3.0
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).baseValue = 32.0
    }

    override fun initEntityAI() {
        this.tasks.addTask(0, EntityAISwimming(this))
        this.tasks.addTask(2, EntityAIPrometheanAttack(this, 1.0, true))
        this.tasks.addTask(5, EntityAIMoveTowardsRestriction(this, 1.0))
        this.tasks.addTask(7, EntityAIWander(this, 1.0))
        this.tasks.addTask(8, EntityAIWatchClosest(this, EntityPlayer::class.java, 8.0f))
        this.tasks.addTask(8, EntityAILookIdle(this))
        this.applyEntityAI()
    }

    private fun applyEntityAI() {
        this.tasks.addTask(6, EntityAIMoveThroughVillage(this, 1.0, false))
        this.targetTasks.addTask(1, EntityAIHurtByTarget(this, false))
        this.targetTasks.addTask(2, EntityAINearestAttackableTarget(this, EntityPlayer::class.java, true))
        this.targetTasks.addTask(3, EntityAINearestAttackableTarget(this, EntityVillager::class.java, false))
        this.targetTasks.addTask(3, EntityAINearestAttackableTarget(this, EntityIronGolem::class.java, true))
    }

    override fun getLootTable(): ResourceLocation? {
        return loot
    }
}