package suy.xyz.dog.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries
import suy.xyz.dog.RuneCraftMod
import suy.xyz.dog.moditem.ModItems
import java.util.stream.Collectors

class ItemModes(
    output: PackOutput,
    existingFileHelper: ExistingFileHelper
) : ItemModelProvider(
    output,
    RuneCraftMod.ID,
    existingFileHelper,
) {

    @Suppress("PropertyName") val GENERATED = "item/generated"

    @Suppress("PropertyName") val HANDHELD = "item/handheld"

    override fun registerModels(){
        val items: MutableSet<Item> = ForgeRegistries.ITEMS.values.stream()
            .filter {
                RuneCraftMod.ID == ForgeRegistries.ITEMS.getKey(it)?.namespace
            }.collect(Collectors.toSet())
        // I will register item here.

        //itemGeneratedModel(ModItems.`REGISTERED NAME`.get(),resourceItem(itemName(ModItems.RAW_SAPPHIRE.get())));
        //items.remove(ModItems.`SAME NAME`.get());

        itemName(ModItems.FIRE_RUNE.get())?.let { resourceItem(it) }
            ?.let { itemGeneratedModel(ModItems.FIRE_RUNE.get(), it) }
        items.remove(ModItems.FIRE_RUNE.get())
        itemName(ModItems.FROST_RUNE.get())?.let { resourceItem(it) }
            ?.let { itemGeneratedModel(ModItems.FROST_RUNE.get(), it) }
        items.remove(ModItems.FROST_RUNE.get())

    }

    private fun itemName(item: Item): String? = ForgeRegistries.ITEMS.getKey(item)?.path
    fun resourceBlock(path: String): ResourceLocation = ResourceLocation(RuneCraftMod.ID, "block/$path")
    private fun resourceItem(path: String): ResourceLocation = ResourceLocation(RuneCraftMod.ID, "item/$path")
    fun itemGeneratedModel(item: Item, texture: ResourceLocation) = withExistingParent(itemName(item),GENERATED).texture("layer0",texture)
}