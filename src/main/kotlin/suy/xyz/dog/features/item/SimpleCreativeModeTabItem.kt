package suy.xyz.dog.features.item

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import suy.xyz.dog.registry.ModCreativeModeTabs.RUNECRAFT_TAB

interface SimpleCreativeModeTabItem : ICreativeModeTabItem {
    fun showInCreativeTab(): Boolean = true

    override fun addCreativeTabComponents(tab: CreativeModeTab, accept: AcceptItemStacks) {
        if (showInCreativeTab() && tab == RUNECRAFT_TAB.get())
            accept(ItemStack(this as Item))
    }

}