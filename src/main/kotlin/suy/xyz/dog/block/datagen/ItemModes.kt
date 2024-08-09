package suy.xyz.dog.block.datagen

import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.registries.ForgeRegistries
import suy.xyz.dog.RuneCraftMod
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
        val item: Set<Item> = ForgeRegistries.ITEMS.getValues().stream()
            .filter {
                RuneCraftMod.ID == ForgeRegistries.ITEMS.getKey(it)?.namespace
            }.collect(Collectors.toSet())
        // I will register item here.

        //itemGeneratedModel(ModItems.`REGISTERED NAME`.get(),resourceItem(itemName(ModItems.RAW_SAPPHIRE.get())));
        //items.remove(ModItems.`SAME NAME`.get());

    }

    fun itemName(item: Item): String? = ForgeRegistries.ITEMS.getKey(item)?.path
    fun resourceBlock(path: String): ResourceLocation = ResourceLocation(RuneCraftMod.ID, "block/$path")
    fun resourceItem(path: String): ResourceLocation = ResourceLocation(RuneCraftMod.ID, "item/$path")

}