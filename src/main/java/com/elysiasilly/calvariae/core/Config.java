package com.elysiasilly.calvariae.core;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public static ModConfigSpec CLIENT_CONFIG;

    public static ModConfigSpec.BooleanValue CUSTOM_FOG;

    static {
        ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();

        CLIENT.push("visual");

        CUSTOM_FOG = CLIENT.comment("if custom fog is enabled").define("customFog", true);

        CLIENT.pop();

        CLIENT_CONFIG = CLIENT.build();
    }

    public static ModConfigSpec COMMON_CONFIG;

    public static ModConfigSpec.IntValue DETERIORATED_TOOL_COST;

    static {
        ModConfigSpec.Builder COMMON = new ModConfigSpec.Builder();

        COMMON.push("gameplay");

        DETERIORATED_TOOL_COST = COMMON.comment("exp cost of deteriorated tools").defineInRange("expCost", 5, 0, Integer.MAX_VALUE);

        COMMON.pop();

        COMMON_CONFIG = COMMON.build();
    }
}
