package suy.xyz.dog.item

import net.minecraft.core.particles.ParticleTypes
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.living.LivingHurtEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.registries.ForgeRegistries
import suy.xyz.dog.moditem.ICreativeTab
import suy.xyz.dog.moditem.ModItems

class RuneItem(properties: Properties) : Item(properties) , ICreativeTab{

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    override fun isFoil(stack: ItemStack): Boolean {
        return getAppliedRune(stack) != null
    }

    companion object {
        private const val RUNE_TAG = "AppliedRune"

        fun applyRuneToItemStack(rune: RuneItem, stack: ItemStack) {
            stack.orCreateTag.putString(RUNE_TAG, ForgeRegistries.ITEMS.getKey(rune).toString())
        }

        fun getAppliedRune(stack: ItemStack): RuneItem? {
            val tag = stack.tag ?: return null
            val runeName = tag.getString(RUNE_TAG)
            return if (runeName.isNotEmpty()) {
                val item = ForgeRegistries.ITEMS.getValue(ResourceLocation(runeName))
                if (item is RuneItem) item else null
            } else null
        }
    }

    fun spawnParticleEffect(target: Entity, attacker: Player) {
        val level = target.level()
        val pos = target.position()

        if (!level.isClientSide) {
            val particleType = when (this) {
                ModItems.FIRE_RUNE.get() -> ParticleTypes.FLAME
                ModItems.FROST_RUNE.get() -> ParticleTypes.SNOWFLAKE
                else -> ParticleTypes.HAPPY_VILLAGER
            }

            for (i in 0 until 20) {
                val offsetX = level.random.nextGaussian() * 0.02
                val offsetY = level.random.nextGaussian() * 0.02
                val offsetZ = level.random.nextGaussian() * 0.02
                level.addParticle(
                    particleType,
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    0.0, 0.0, 0.0
                )
            }
        }
    }

    @SubscribeEvent
    fun onLivingDeath(event: LivingDeathEvent) {
        val entity = event.entity
        val source = event.source
        val attacker = source.entity
        if (attacker is Player) {
            val weapon = attacker.mainHandItem
            if (weapon.item is RuneItem) {
                (weapon.item as RuneItem).spawnParticleEffect(entity, attacker)
            }
        }
    }

    @SubscribeEvent
    fun onLivingHurt(event: LivingHurtEvent) {
        val entity = event.entity
        val source = event.source
        val attacker = source.entity
        if (attacker is Player) {
            val weapon = attacker.mainHandItem
            if (weapon.item is RuneItem) {
                (weapon.item as RuneItem).spawnParticleEffect(entity, attacker)
            }
        }
    }

    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.phase == TickEvent.Phase.END && event.player.level() is ServerLevel) {
            val level = event.player.level() as ServerLevel
            val player = event.player
            val stack = player.mainHandItem

            if (stack.item is RuneItem) {
                val rune = getAppliedRune(stack)
                rune?.spawnParticleEffect(player, player)
            }
        }
    }

    override fun showCreativeTab(): Boolean = true
}
