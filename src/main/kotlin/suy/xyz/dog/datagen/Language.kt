package suy.xyz.dog.datagen

import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider
import suy.xyz.dog.RuneCraftMod
import suy.xyz.dog.moditem.ModCreativeModTabs

class Language(output: PackOutput,locale: String): LanguageProvider(
    output,RuneCraftMod.ID,locale
) {
    override fun addTranslations() {
        // add items
        add(ModCreativeModTabs.RUNECRAFT_TAB_STRING,"RuneCraftMod")
    }
}