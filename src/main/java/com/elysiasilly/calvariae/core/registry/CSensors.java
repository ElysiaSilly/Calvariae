package com.elysiasilly.calvariae.core.registry;

import net.minecraft.world.entity.ai.sensing.SensorType;
import net.tslat.smartbrainlib.SBLConstants;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;

import java.util.function.Supplier;

public class CSensors {
    //public static final DeferredRegister<SensorType<?>> SENSORS = DeferredRegister.create(Registries.SENSOR_TYPE, CaveMod.MODID);

    /*
    public static final Supplier<SensorType<InWaterSensor<?>>> IN_WATER_SENSOR =
            SENSORS.register("in_water_sensor", InWaterSensor::new);

     */

    //public static final Supplier<SensorType<InWaterSensor<?>>> NEARBY_BLOCKS = register("nearby_blocks", InWaterSensor::new);


    private static <T extends ExtendedSensor<?>> Supplier<SensorType<T>> register(String id, Supplier<T> sensor) {
        return SBLConstants.SBL_LOADER.registerSensorType(id, sensor);
    }
}
