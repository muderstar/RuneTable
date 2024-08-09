package suy.xyz.dog.block.datagen

import net.minecraft.data.PackOutput
import net.minecraftforge.common.data.LanguageProvider
import suy.xyz.dog.RuneCraftMod
import suy.xyz.dog.block.moditem.ModCreativeModTabs

class Language(output: PackOutput,locale: String): LanguageProvider(
    output,RuneCraftMod.ID,locale
) {
    override fun addTranslations() {
        add(ModCreativeModTabs.RUNECRAFT_TAB_STRING,"RuneCraftMod")
    }
}