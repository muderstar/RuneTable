package suy.xyz.dog.datagen

import net.minecraft.data.DataGenerator
import net.minecraft.data.PackOutput
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import suy.xyz.dog.RuneCraftMod

@Mod.EventBusSubscriber(
    modid = RuneCraftMod.ID,
    bus = Mod.EventBusSubscriber.Bus.MOD,
)
object DataGenerators {
    @SubscribeEvent
    fun gatherData(e: GatherDataEvent) {
        val generator: DataGenerator = e.generator
        val output: PackOutput = generator.packOutput
        // val lookupProvider: CompletableFuture<HolderLookup.Provider> = e.lookupProvider
        val helper = e.existingFileHelper

        generator.addProvider(e.includeClient(), ItemModes(output, helper))
        generator.addProvider(e.includeClient(), Language(output, "en_us"))
    }

}