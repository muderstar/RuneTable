package suy.xyz.dog.features.entity

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player

data class RuneStone(
    val id: String,
    val displayName: String,
    val tooltip: String,
    val particleEffect: (ServerLevel, Player) -> Unit,
    val armorParticleEffect: (ServerLevel, Player) -> Unit
)