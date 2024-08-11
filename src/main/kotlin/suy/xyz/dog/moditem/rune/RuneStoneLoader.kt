package suy.xyz.dog.moditem.rune

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.player.Player
import suy.xyz.dog.moditem.rune.entity.RuneStone
import suy.xyz.dog.moditem.rune.entity.RuneStoneData
import java.io.InputStreamReader



object RuneStoneLoader {
    fun loadRunes(): List<RuneStone> {
        val gson = Gson()
        val inputStream = RuneStoneLoader::class.java.getResourceAsStream("/assets/rune_craft_dog/runestones.json")
        val reader = inputStream?.let { InputStreamReader(it) }
        val type = object : TypeToken<List<RuneStoneData>>() {}.type
        val runeStoneDataList: List<RuneStoneData> = gson.fromJson(reader, type)

        return runeStoneDataList.map { data ->
            val weaponEffect = getParticleEffect(data.weaponParticleEffect)
            val armorEffect = getParticleEffect(data.armorParticleEffect)
            RuneStone(
                id = data.id,
                displayName = data.displayName,
                tooltip = data.tooltip,
                particleEffect = weaponEffect,
                armorParticleEffect = armorEffect
            )
        }
    }

    private fun getParticleEffect(effectName: String): (ServerLevel, Player) -> Unit {
        return when (effectName) {
            "FLAME" -> { level, player -> level.sendParticles(ParticleTypes.FLAME, player.x, player.y + 1, player.z, 10, 0.5, 0.5, 0.5, 0.0) }
            "SMOKE" -> { level, player -> level.sendParticles(ParticleTypes.SMOKE, player.x, player.y + 1, player.z, 10, 0.5, 0.5, 0.5, 0.0) }
            "SNOWFLAKE" -> { level, player -> level.sendParticles(ParticleTypes.SNOWFLAKE, player.x, player.y + 1, player.z, 10, 0.5, 0.5, 0.5, 0.0) }
            "CLOUD" -> { level, player -> level.sendParticles(ParticleTypes.CLOUD, player.x, player.y + 1, player.z, 10, 0.5, 0.5, 0.5, 0.0) }
            else -> { _, _ -> }
        }
    }
}