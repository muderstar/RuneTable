package suy.xyz.dog.block.rune.registry

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import net.minecraft.resources.ResourceLocation
import suy.xyz.dog.RuneCraftMod
import suy.xyz.dog.block.rune.entity.RuneStone
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

object RuneStoneRegistry {
    private val RUNESTONES = mutableMapOf<String, RuneStone>()
    private val GSON = Gson()

    init {
        loadRuneStones()
    }

    private fun loadRuneStones() {
        val resource = ResourceLocation(RuneCraftMod.ID, "runes/rune_stones.json")
        val stream = javaClass.classLoader.getResourceAsStream(resource.path)
        val reader = InputStreamReader(stream, StandardCharsets.UTF_8)
        val runeStoneListType = object : TypeToken<List<RuneStone>>() {}.type
        val runeStones: List<RuneStone> = GSON.fromJson(reader, runeStoneListType)

        for (runeStone in runeStones) {
            RUNESTONES[runeStone.id] = runeStone
        }
    }

    fun getRuneStone(id: String): RuneStone? = RUNESTONES[id]

    fun getAllRuneStones(): Collection<RuneStone> = RUNESTONES.values
}