package suy.xyz.dog.registry

import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import suy.xyz.dog.RuneCraftMod
import suy.xyz.dog.features.item.SimpleCreativeModeTabItem

object ModItems {
    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, RuneCraftMod.ID)

    // Put item into this bus.
    val FIRE_RUNE: RegistryObject<Item> = ITEMS.register("fire_rune") {
        object : Item(Properties()), SimpleCreativeModeTabItem {}
    }

    val FROST_RUNE: RegistryObject<Item> = ITEMS.register("frost_rune") {
        object : Item(Properties()), SimpleCreativeModeTabItem {}
    }

    fun register(eventBus: IEventBus) = ITEMS.register(eventBus)

}