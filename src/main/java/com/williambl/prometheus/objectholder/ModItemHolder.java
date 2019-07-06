package com.williambl.prometheus.objectholder;

import com.williambl.prometheus.Prometheus;
import com.williambl.prometheus.common.item.DebugItem;
import com.williambl.prometheus.common.item.base.BaseItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Prometheus.MODID)
public class ModItemHolder {

    @GameRegistry.ObjectHolder("multiblockstarter")
    public static BaseItem multiBlockStarter;

    @GameRegistry.ObjectHolder("debug_item")
    public static DebugItem debugItem;
}
