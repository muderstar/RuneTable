package suy.xyz.dog.features.entity

import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.common.ToolActions
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RuneModule {
    val LOGGER: Logger = LogManager.getLogger("RuneCraft : Rune")

    val ARMOR_SLOTS: Array<EquipmentSlot> = arrayOf(
        EquipmentSlot.HEAD,
        EquipmentSlot.CHEST,
        EquipmentSlot.LEGS,
        EquipmentSlot.FEET
    )

    val WEAPON_CATEGORY = EnchantmentCategory.create("WEAPON") { item ->
        item.canPerformAction(ItemStack(item), ToolActions.SWORD_SWEEP) ||
                item.canPerformAction(ItemStack(item), ToolActions.AXE_DIG)
    }
    val ARMOR_CATEGORY = EnchantmentCategory.create("ARMOR") { item ->
        item is ArmorItem
    }
}