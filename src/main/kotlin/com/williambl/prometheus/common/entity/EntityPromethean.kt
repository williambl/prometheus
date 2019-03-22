package com.williambl.prometheus.common.entity

import com.williambl.prometheus.Prometheus
import com.williambl.prometheus.common.entity.ai.EntityAIPrometheanAttack
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.SharedMonsterAttributes
import net.minecraft.entity.ai.*
import net.minecraft.entity.monster.EntityIronGolem
import net.minecraft.entity.monster.EntityMob
import net.minecraft.entity.monster.EntityPigZombie
import net.minecraft.entity.passive.EntityVillager
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.MobEffects
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.pathfinding.PathNavigateGround
import net.minecraft.potion.PotionEffect
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.energy.CapabilityEnergy
import net.minecraftforge.energy.EnergyStorage
import org.lwjgl.input.Mouse

class EntityPromethean(worldIn: World) : EntityMob(worldIn) {

    companion object {
        val loot: ResourceLocation = ResourceLocation(Prometheus.MODID, "entities/promethean")
        private val rfValue = EntityDataManager.createKey(EntityPromethean::class.java, DataSerializers.VARINT)
        val maxRFRange: Int = 8
        val maxRfExtract: Int = 100
    }

    init {
        this.health = this.maxHealth
        this.setSize(0.9F, 3.5F)
        this.isImmuneToFire = true
        (this.getNavigator() as PathNavigateGround).canSwim = true
        this.experienceValue = 50
    }

    override fun onLivingUpdate() {
        super.onLivingUpdate()
        if (world.isRemote)
            return

        implodeRF()
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

                if (giver != null && giver.energyStored > 1000) {
                    println(te.displayName?.plus("valid 2, extracting..."))
                    world.setBlockToAir(blockPos)
                    world.newExplosion(null, blockPos.x.toDouble(), blockPos.y.toDouble(), blockPos.z.toDouble(), (Math.log(giver.energyStored.toDouble())/4).toFloat(), true, true)
                    addRF(giver.energyStored)
                    println(getRF())
                }
            }
        }
    }

    override fun entityInit() {
        super.entityInit()
        this.getDataManager().register(rfValue, 0)
    }

    override fun applyEntityAttributes() {
        super.applyEntityAttributes()
        // Here we set various attributes for our mob. Like maximum health, armor, speed, ...
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).baseValue = 64.0
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).baseValue = 0.13
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).baseValue = 3.0
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).baseValue = 2.0
    }

    fun addRF(input: Int) {
        this.getDataManager().set(rfValue, this.getDataManager().get(rfValue) + input)
    }

    fun getRF(): Int {
        return this.getDataManager().get(rfValue)
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
        this.targetTasks.addTask(1, EntityAIHurtByTarget(this, true, EntityPigZombie::class.java))
        this.targetTasks.addTask(2, EntityAINearestAttackableTarget(this, EntityPlayer::class.java, true))
        this.targetTasks.addTask(3, EntityAINearestAttackableTarget(this, EntityVillager::class.java, false))
        this.targetTasks.addTask(3, EntityAINearestAttackableTarget(this, EntityIronGolem::class.java, true))
    }

    override fun attackEntityAsMob(entityIn: Entity): Boolean {
        return if (super.attackEntityAsMob(entityIn)) {
            if (entityIn is EntityLivingBase) {
                // This zombie gives health boost and regeneration when it attacks
                entityIn.addPotionEffect(PotionEffect(MobEffects.HEALTH_BOOST, 200))
                entityIn.addPotionEffect(PotionEffect(MobEffects.REGENERATION, 200))
            }
            true
        } else {
            false
        }
    }

    override fun getLootTable(): ResourceLocation? {
        return loot
    }
}