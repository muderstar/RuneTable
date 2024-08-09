package suy.xyz.dog.block.moditem

import net.minecraft.world.item.Item
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import suy.xyz.dog.RuneCraftMod

object ModItem {
    val ITEMS: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS,RuneCraftMod.ID)

    // Put item into this bus.

    fun register(eventBus: IEventBus) = ITEMS.register(eventBus)
    

}