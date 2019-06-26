package com.williambl.prometheus.common.tileentity

import com.williambl.prometheus.common.entity.EntityAncientDrone
import com.williambl.prometheus.common.tileentity.base.BaseEnergyTileEntity

open class AncientDroneSpawnerTileEntity : BaseEnergyTileEntity() {

    var input = 2500
    var output = 10000
    var capacity = 50000

    init {
        setMaxInput(input)
        setMaxOutput(output)
        maxEnergyStored = capacity
    }

    var tickCounter = 0

    override fun update() {
        if (!this.hasWorld() || this.world.isRemote)
            return

        if (energyStored > 10000) {
            tickCounter++

            if (tickCounter > 600 && world.rand.nextFloat() > 0.9) {
                tickCounter = 0
                extractEnergy(10000, false)
                spawnDrone()
            }
        }
    }

    fun spawnDrone() {
        val drone = EntityAncientDrone(world)
        drone.setPosition(pos.x.toDouble(), pos.y.toDouble() + 1, pos.z.toDouble())
        world.spawnEntity(drone)
    }

}

