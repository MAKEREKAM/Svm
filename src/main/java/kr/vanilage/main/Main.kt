package kr.vanilage.main

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.java.JavaPlugin
import java.util.Random
import java.util.UUID

class Main : JavaPlugin(), Listener {
    companion object {
        val playerTemp = HashMap<UUID, Double>()
        val playerFatigue = HashMap<UUID, Double>()
        val random = Random()
        lateinit var instance : Main

        val potionItem = ItemStack(Material.POTION)
    }

    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("Hello, World!")

        instance = this

        settingRecipe()

        Bukkit.getPluginManager().registerEvents(SettingPlayerTemp(), this)
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(e : PlayerJoinEvent) {
        if (!playerTemp.containsKey(e.player.uniqueId)) {
            playerTemp[e.player.uniqueId] = 36.0
            playerFatigue[e.player.uniqueId] = 0.0
        }

        SettingPlayerTemp.setTemp(e.player)
    }

    private fun settingRecipe() {
        val meta = potionItem.itemMeta

        meta.setCustomModelData(12345)
        meta.displayName(MiniMessage.miniMessage().deserialize(
            "<red>해열제"
        ))
        meta.lore(
            mutableListOf(
                MiniMessage.miniMessage().deserialize(
                    "<yellow>마시면 온도가 1도 낮아집니다."
                )
            )
        )

        potionItem.itemMeta = meta

        val recipe = ShapelessRecipe(NamespacedKey(this, "potion"), potionItem)

        recipe.addIngredient(Material.DANDELION)
        recipe.addIngredient(Material.POPPY)
        recipe.addIngredient(Material.BOWL)

        Bukkit.addRecipe(recipe)
    }
}