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
import kotlin.math.min

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
                            (Main.playerTemp[player.uniqueId]!! - 38) * 20
                        }
                        else if (Main.playerTemp[player.uniqueId]!! <= 34) {
                            (34 - Main.playerTemp[player.uniqueId]!!) * 20
                        }
                        else {
                            0.0
                        }

                    val point = min(((healthScore + foodLevelScore + tempScore) / 50), 1.0)

                    Main.playerFatigue[player.uniqueId] = point

                    bossBar.progress = point
                    bossBar.setTitle(String.format("§b피로도 §e%.1f%%", Main.playerFatigue[player.uniqueId]!! * 100))

                    if (point >= 0.5) {
                        bossBar.color = BarColor.RED

                        player.walkSpeed = 0.2F - (point / 6).toFloat()
                    }
                    else bossBar.color = BarColor.GREEN

                    if (player.isOnline) Bukkit.getScheduler().runTaskLater(Main.instance, this, 1)
                }
            }

            Bukkit.getScheduler().runTask(Main.instance, computeFatigue)
        }
    }
}