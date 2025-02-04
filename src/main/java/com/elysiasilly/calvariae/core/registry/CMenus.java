package com.elysiasilly.calvariae.core.registry;

import com.elysiasilly.calvariae.core.Calvariae;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CMenus {

    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, Calvariae.MODID);

}
