package suy.xyz.dog.moditem.rune.event

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import net.minecraftforge.event.TickEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import suy.xyz.dog.item.RuneItem
import suy.xyz.dog.moditem.ModItems

@Mod.EventBusSubscriber object RuneEvents {
    @SubscribeEvent
    fun onPlayerTick(event: TickEvent.PlayerTickEvent) {
        if (event.phase == TickEvent.Phase.END && event.player.level() is ServerLevel) {
            val level = event.player.level() as ServerLevel
            val player = event.player
            val stack = player.mainHandItem

            if (stack.item is RuneItem) {
                val rune = RuneItem.getAppliedRune(stack)
                rune?.spawnParticleEffect(player, player)
            }
        }
    }

    @SubscribeEvent
    fun onLivingDeath(event: LivingDeathEvent) {
        val source = event.source
        val entity = event.entity
        val level = entity.level()

        if (source.entity is Player && level is ServerLevel) {
            val player = source.entity as Player
            val stack = player.mainHandItem

            if (stack.item is RuneItem) {
                val rune = RuneItem.getAppliedRune(stack)
                rune?.spawnParticleEffect(entity, player)
            }
        }
    }
}