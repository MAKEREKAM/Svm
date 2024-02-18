package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.Random
import java.util.UUID

class Main : JavaPlugin(), Listener {
    companion object {
        val playerTemp = HashMap<UUID, Double>()
        val playerFatigue = HashMap<UUID, Double>()
        val random = Random()
    }

    override fun onEnable() {
        Bukkit.getConsoleSender().sendMessage("Hello, World!")

        Bukkit.getPluginManager().registerEvents(SettingPlayerTemp(), this)

        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(e : PlayerJoinEvent) {
        playerTemp[e.player.uniqueId] = 36.0
        playerFatigue[e.player.uniqueId] = 0.0

        SettingPlayerTemp.setTemp(e.player)
    }

    private fun settingRecipe() {

    }
}