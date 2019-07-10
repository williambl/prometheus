package com.williambl.prometheus.objectholder;

import com.williambl.prometheus.Prometheus;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Prometheus.MODID)
public class ModEntityHolder {

    @GameRegistry.ObjectHolder("ancient_drone")
    public static EntityEntry ancientDrone;

    @GameRegistry.ObjectHolder("plasma_ball")
    public static EntityEntry plasmaBall;

}
