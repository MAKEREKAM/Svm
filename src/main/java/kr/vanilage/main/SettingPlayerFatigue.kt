package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Biome
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerMoveEvent

class SettingPlayerFatigue : Listener {
    companion object {
        fun setFatigue(player: Player) {
            val bossBar = Bukkit.createBossBar(
                "§b피로도 §e${Main.playerFatigue[player.uniqueId]}%",
                BarColor.GREEN,
                BarStyle.SOLID
            )

            bossBar.addPlayer(player)

            val computeFatigue = object : Runnable {
                override fun run() {
                    val healthScore = 20 - player.health
                    val foodLevelScore = 20 - player.foodLevel
                    val tempScore =
                        if (Main.playerTemp[player.uniqueId]!! >= 38) {
                            Main.playerTemp[player.uniqueId]!! - 38
                        }
                        else if (Main.playerTemp[player.uniqueId]!! <= 34) {
                            34 - Main.playerTemp[player.uniqueId]!!
                        }
                        else {
                            0.0
                        }

                    val point = 50 / (healthScore + foodLevelScore + tempScore)

                    bossBar.setTitle(String.format("§b피로도 §e%.1f%", Main.playerFatigue[player.uniqueId]!! * 100))

                    if (player.isOnline) Bukkit.getScheduler().runTaskLater(Main.instance, this, 1)
                }
            }

            Bukkit.getScheduler().runTask(Main.instance, computeFatigue)
        }
    }
}