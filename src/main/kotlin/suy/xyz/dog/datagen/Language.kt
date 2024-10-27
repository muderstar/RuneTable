package suy.xyz.dog.datagen

import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider
import suy.xyz.dog.RuneCraftMod
import suy.xyz.dog.registry.ModCreativeModeTabs
import suy.xyz.dog.registry.ModItems

class Language(output: PackOutput, locale: String) : LanguageProvider(
    output, RuneCraftMod.ID, locale
) {
    override fun addTranslations() {
        // add items
        add(ModItems.FIRE_RUNE.get(), "FireRune")
        add(ModItems.FROST_RUNE.get(), "FrostRune")
        add(ModCreativeModeTabs.RUNECRAFT_TAB_STRING, "RuneCraftMod")
    }
}