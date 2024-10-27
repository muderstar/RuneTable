package suy.xyz.dog.registry

import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.RegistryObject
import suy.xyz.dog.RuneCraftMod

object ModCreativeModeTabs {
    @Suppress("MemberVisibilityCanBePrivate")
    const val RUNECRAFT_TAB_STRING = "creativetab.runecraft_tab"

    @Suppress("MemberVisibilityCanBePrivate")
    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RuneCraftMod.ID)

    val RUNECRAFT_TAB: RegistryObject<CreativeModeTab> = CREATIVE_MODE_TABS.register("runecraft_tab") {
        CreativeModeTab.builder().icon { ItemStack(Items.WRITABLE_BOOK) }
            .title(Component.translatable(RUNECRAFT_TAB_STRING))
            .displayItems { _, pOutput ->
                pOutput.accept(Items.DIAMOND)
            }
            .build()
    }

    fun register(eventBus: IEventBus) = CREATIVE_MODE_TABS.register(eventBus)

}