package com.williambl.prometheus.objectholder;

import com.williambl.prometheus.Prometheus;
import com.williambl.prometheus.common.block.OrientableTileEntityProviderBlock;
import com.williambl.prometheus.common.block.base.BaseBlock;
import com.williambl.prometheus.common.block.base.BaseLightBlock;
import com.williambl.prometheus.common.block.base.BaseMultiBlockMasterBlock;
import com.williambl.prometheus.common.block.base.BaseTileEntityProviderBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Prometheus.MODID)
public class ModBlockHolder {
    @GameRegistry.ObjectHolder("ancient_circuitry")
    public static BaseBlock ancientCircuitry;
    @GameRegistry.ObjectHolder("ancient_casing")
    public static BaseBlock ancientCasing;
    @GameRegistry.ObjectHolder("ancient_concrete")
    public static BaseBlock ancientConcrete;

    @GameRegistry.ObjectHolder("ancient_light")
    public static BaseLightBlock ancientLight;

    @GameRegistry.ObjectHolder("ancient_core")
    public static BaseMultiBlockMasterBlock ancientDeviceMaster;

    @GameRegistry.ObjectHolder("ancient_drone_spawner")
    public static BaseTileEntityProviderBlock ancientDroneSpawner;
    @GameRegistry.ObjectHolder("gravity_well")
    public static OrientableTileEntityProviderBlock gravityWell;
    @GameRegistry.ObjectHolder("confiscator")
    public static OrientableTileEntityProviderBlock confiscator;
    @GameRegistry.ObjectHolder("plasma_ball_dispenser")
    public static OrientableTileEntityProviderBlock plasmaBallDispenser;
}
