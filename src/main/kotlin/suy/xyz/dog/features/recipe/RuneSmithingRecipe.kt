package suy.xyz.dog.features.recipe

import com.google.gson.JsonObject
import net.minecraft.core.RegistryAccess
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.Container
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.ShapedRecipe
import net.minecraft.world.item.crafting.SmithingTransformRecipe
import net.minecraft.world.level.Level
import suy.xyz.dog.features.item.RuneItem

@Suppress("unused")
class RuneSmithingRecipe(
    id: ResourceLocation,
    template: Ingredient,
    base: Ingredient,
    addition: Ingredient,
    result: ItemStack
) : SmithingTransformRecipe(id, template, base, addition, result) {

    override fun matches(container: Container, level: Level): Boolean {
        return template.test(container.getItem(0)) && base.test(container.getItem(1)) && addition.test(
            container.getItem(
                2
            )
        )
    }

    override fun assemble(container: Container, registryAccess: RegistryAccess): ItemStack {
        val resultStack = super.assemble(container, registryAccess)

        val runeItemStack = getIngredientItem(template)
        if (runeItemStack != null && runeItemStack.item is RuneItem) {
            RuneItem.applyRuneToItemStack(runeItemStack.item as RuneItem, resultStack)
        }
        return resultStack
    }

    companion object {
        val SERIALIZER: RecipeSerializer<RuneSmithingRecipe> = Serializer()

        class Serializer : RecipeSerializer<RuneSmithingRecipe> {
            override fun fromJson(recipeId: ResourceLocation, json: JsonObject): RuneSmithingRecipe {
                val template = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template"))
                val base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"))
                val addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"))
                val result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"))
                return RuneSmithingRecipe(recipeId, template, base, addition, result)
            }

            override fun fromNetwork(recipeId: ResourceLocation, buffer: FriendlyByteBuf): RuneSmithingRecipe {
                val template = Ingredient.fromNetwork(buffer)
                val base = Ingredient.fromNetwork(buffer)
                val addition = Ingredient.fromNetwork(buffer)
                val result = buffer.readItem()
                return RuneSmithingRecipe(recipeId, template, base, addition, result)
            }

            override fun toNetwork(buffer: FriendlyByteBuf, recipe: RuneSmithingRecipe) {
                recipe.template.toNetwork(buffer)
                recipe.base.toNetwork(buffer)
                recipe.addition.toNetwork(buffer)
                buffer.writeItem(recipe.result)
            }
        }

        private fun getIngredientItem(ingredient: Ingredient): ItemStack? {
            val itemStacks = ingredient.itemStacks
            return if (itemStacks!!.isNotEmpty()) itemStacks[0] else null
        }
    }
}