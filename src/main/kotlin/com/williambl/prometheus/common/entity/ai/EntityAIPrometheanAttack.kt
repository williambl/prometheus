package com.williambl.prometheus.common.entity.ai

import com.williambl.prometheus.common.entity.EntityPromethean
import net.minecraft.entity.ai.EntityAIAttackMelee

class EntityAIPrometheanAttack(private val entity: EntityPromethean, speed: Double, longMemory: Boolean) : EntityAIAttackMelee(entity, speed, longMemory) {

    override fun startExecuting() {
        super.startExecuting()
    }

    override fun resetTask() {
        super.resetTask()
    }

    override fun updateTask() {
        super.updateTask()
    }
}
