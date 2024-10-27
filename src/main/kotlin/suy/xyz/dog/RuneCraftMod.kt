package suy.xyz.dog

import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.KotlinModLoadingContext
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */

@Mod(RuneCraftMod.ID)
object RuneCraftMod {
    const val ID = "rune_craft_dog"

    // the logger for our mod
    private val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        // Register the KDeferredRegister to the mod-specific event bus

        val modEventBus: IEventBus = KotlinModLoadingContext.get().getKEventBus()
        CommonProxy.init(modEventBus)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(RuneCraftMod::onClientSetup)
                MOD_BUS.addListener(CommonProxy::initCreativeModeTab)
            },
            serverTarget = {
                MOD_BUS.addListener(RuneCraftMod::onServerSetup)
            }
        )
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
    }
}