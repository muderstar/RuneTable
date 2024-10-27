package suy.xyz.dog.features.item

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

fun interface AcceptItemStacks {
    operator fun invoke(vararg itemStacks: ItemStack)
}

interface ICreativeModeTabItem {

    fun addCreativeTabComponents(tab: CreativeModeTab, accept: AcceptItemStacks)

}