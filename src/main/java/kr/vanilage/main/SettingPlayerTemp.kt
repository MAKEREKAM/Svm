package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class SettingPlayerTemp : Listener {
    companion object {
        fun setTemp(player : Player) {
            val bossBar = Bukkit.createBossBar(
                "§b온도 §e${Main.playerTemp[player.uniqueId]}°C",
                BarColor.GREEN,
                BarStyle.SOLID
            )

            bossBar.addPlayer(player)

            val computeTemp = object : Runnable {
                override fun run() {
                    var change = 0

                    for (x in -3..3) {
                        for (y in -3..3) {
                            for (z in -3..3) {
                                
                            }
                        }
                    }
                }
            }
        }
    }
}