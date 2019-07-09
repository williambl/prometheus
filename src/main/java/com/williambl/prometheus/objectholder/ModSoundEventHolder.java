package com.williambl.prometheus.objectholder;

import com.williambl.prometheus.Prometheus;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Prometheus.MODID)
public class ModSoundEventHolder {

    @GameRegistry.ObjectHolder("confiscator_hum")
    public static SoundEvent confiscatorHum;

}
