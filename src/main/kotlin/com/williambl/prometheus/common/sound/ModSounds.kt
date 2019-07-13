package com.williambl.prometheus.common.sound

import com.williambl.prometheus.Prometheus
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

@EventBusSubscriber
object ModSounds {

    @SubscribeEvent
    @JvmStatic
    fun registerSoundEvents(event: RegistryEvent.Register<SoundEvent>) {
        val confiscatorHum = ResourceLocation(Prometheus.MODID, "confiscator_hum")
        event.registry.registerAll(
                SoundEvent(confiscatorHum).setRegistryName(confiscatorHum)
        )
    }
}
