package suy.xyz.dog

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent
import net.minecraftforge.eventbus.api.IEventBus
import suy.xyz.dog.block.ModBlocks
import suy.xyz.dog.features.item.ICreativeModeTabItem
import suy.xyz.dog.registry.ModCreativeModeTabs
import suy.xyz.dog.registry.ModItems
import thedarkcolour.kotlinforforge.forge.MOD_BUS

object CommonProxy {

    fun init(eventBus: IEventBus) {
        ModCreativeModeTabs.register(eventBus)
        ModItems.register(eventBus)
        ModBlocks.REGISTRY.register(MOD_BUS)
    }

    fun initCreativeModeTab(event: BuildCreativeModeTabContentsEvent) {
        for (itemEntry in ModItems.ITEMS.entries) {
            val item = itemEntry.get()
            if (item is ICreativeModeTabItem) {
                item.addCreativeTabComponents(event.tab) { components ->
                    event.acceptAll(components.toList())
                }
            }
        }
    }

}