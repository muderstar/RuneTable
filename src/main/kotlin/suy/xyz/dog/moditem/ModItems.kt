package suy.xyz.dog.moditem

import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import suy.xyz.dog.RuneCraftMod

object ModItems {
    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS,RuneCraftMod.ID)

    // Put item into this bus.
    val FIRE_RUNE: RegistryObject<Item> = ITEMS.register("fire_rune") {
        Item(Item.Properties())
    }

    val FROST_RUNE: RegistryObject<Item> = ITEMS.register("frost_rune") {
        Item(Item.Properties())
    }

    fun register(eventBus: IEventBus) = ITEMS.register(eventBus)
    

}