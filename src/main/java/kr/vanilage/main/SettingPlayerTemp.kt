package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Biome
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerMoveEvent

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
                    var change = 0.0

                    for (x in -1..1) {
                        for (y in -1..1) {
                            for (z in -1..1) {
                                if (increaseTempBlock.contains(
                                        player.location.add(x.toDouble(), y.toDouble(), z.toDouble()).block.type)) {

                                    change += 0.01
                                }

                                if (randomIncreaseTempBlock.contains(
                                        player.location.add(x.toDouble(), y.toDouble(), z.toDouble()).block.type)) {

                                    if (Main.random.nextInt(2) == 0) change += 0.01
                                }

                                if (decreaseTempBlock.contains(
                                        player.location.add(x.toDouble(), y.toDouble(), z.toDouble()).block.type)) {

                                    change -= 0.01
                                }

                                if (randomDecreaseTempBlock.contains(
                                        player.location.add(x.toDouble(), y.toDouble(), z.toDouble()).block.type)) {

                                    if (Main.random.nextInt(2) == 0) change -= 0.01
                                }

                                if (player.location.add(x.toDouble(), y.toDouble(), z.toDouble()).block.type == Material.WATER &&
                                    player.vehicle?.type != EntityType.BOAT) {
                                    if (Main.random.nextInt(15) == 0) change -= 0.01
                                }
                            }
                        }
                    }

                    player.getNearbyEntities(1.5, 1.5, 1.5).forEach {
                        if (increaseTempEntity.contains(it.type)) {
                            change += 0.01
                        }

                        if (randomIncreaseTempEntity.contains(it.type)) {
                            if (Main.random.nextInt(2) == 0) change += 0.01
                        }

                        if (decreaseTempEntity.contains(it.type)) {
                            change -= 0.01
                        }

                        if (randomDecreaseTempEntity.contains(it.type)) {
                            if (Main.random.nextInt(2) == 0) change -= 0.01
                        }
                    }

                    if (increaseTempBiome.contains(player.location.block.biome)) {
                        if (Main.random.nextInt(3) == 0) change += 0.01
                    }

                    if (decreaseTempBiome.contains(player.location.block.biome)) {
                        if (Main.random.nextInt(3) == 0) change -= 0.01
                    }

                    if (player.world.environment == World.Environment.NETHER) {
                        if (Main.random.nextInt(10) == 0) change += 0.01
                    }

                    if (player.world.environment == World.Environment.THE_END) {
                        if (Main.random.nextInt(10) == 0) change -= 0.01
                    }

                    Main.playerTemp[player.uniqueId] = Main.playerTemp[player.uniqueId]!! + change

                    val temp = Main.playerTemp[player.uniqueId]!!

                    if (temp <= 35) {
                        bossBar.color = BarColor.RED
                        player.freezeTicks = 200
                        bossBar.progress = 0.0
                    }

                    else if (temp >= 40) {
                        bossBar.color = BarColor.RED
                        player.fireTicks = 100
                        bossBar.progress = 1.0
                    }

                    else {
                        bossBar.color = BarColor.GREEN
                        bossBar.progress = (temp - 35) / 5
                    }

                    bossBar.setTitle(String.format("§b온도 §e%.1f°C", Main.playerTemp[player.uniqueId]))

                    if (player.isOnline) Bukkit.getScheduler().runTaskLater(Main.instance, this, 1)
                }
            }

            Bukkit.getScheduler().runTask(Main.instance, computeTemp)
        }

        private val increaseTempBlock =
            arrayOf(
                Material.LAVA,
                Material.SOUL_CAMPFIRE,
                Material.SOUL_FIRE
            )

        private val randomIncreaseTempBlock =
            arrayOf(
                Material.CAMPFIRE,
                Material.FIRE,
                Material.TORCH,
                Material.SOUL_TORCH,
                Material.WALL_TORCH,
                Material.SOUL_WALL_TORCH,
                Material.SPAWNER,
                Material.MAGMA_BLOCK,
                Material.NETHER_PORTAL,
                Material.REDSTONE_TORCH,
                Material.REDSTONE_WALL_TORCH,
                Material.RESPAWN_ANCHOR,
                Material.GLOWSTONE,
                Material.LANTERN,
                Material.SOUL_LANTERN
            )

        private val decreaseTempBlock =
            arrayOf(
                Material.PACKED_ICE,
                Material.BLUE_ICE,
                Material.ICE
            )

        private val randomDecreaseTempBlock =
            arrayOf(
                Material.SNOW,
                Material.SNOW_BLOCK,
                Material.POWDER_SNOW,
                Material.FROSTED_ICE,
                Material.END_PORTAL,
                Material.END_GATEWAY,
            )

        private val increaseTempEntity =
            arrayOf(
                EntityType.MAGMA_CUBE,
                EntityType.BLAZE,
                EntityType.GHAST,
                EntityType.PRIMED_TNT,
                EntityType.WITHER_SKELETON,
                EntityType.WITHER
            )

        private val randomIncreaseTempEntity =
            arrayOf(
                EntityType.HUSK,
                EntityType.FIREBALL,
                EntityType.SMALL_FIREBALL
            )

        private val decreaseTempEntity =
            arrayOf(
                EntityType.SNOWMAN,
                EntityType.SNOWBALL
            )

        private val randomDecreaseTempEntity =
            arrayOf(
                EntityType.ELDER_GUARDIAN,
                EntityType.GUARDIAN,
                EntityType.DOLPHIN,
                EntityType.DROWNED
            )

        private val increaseTempBiome =
            arrayOf(
                Biome.DESERT,
                Biome.BADLANDS,
                Biome.WOODED_BADLANDS,
                Biome.ERODED_BADLANDS,
                Biome.JUNGLE,
                Biome.SPARSE_JUNGLE,
                Biome.BAMBOO_JUNGLE,
                Biome.SAVANNA,
                Biome.SAVANNA_PLATEAU
            )

        private val decreaseTempBiome =
            arrayOf(
                Biome.SNOWY_TAIGA,
                Biome.SNOWY_PLAINS,
                Biome.SNOWY_BEACH,
                Biome.SNOWY_SLOPES,
                Biome.FROZEN_PEAKS,
                Biome.ICE_SPIKES,
                Biome.COLD_OCEAN,
                Biome.DEEP_COLD_OCEAN,
                Biome.DEEP_DARK,
                Biome.STONY_SHORE
            )
    }

    @EventHandler
    fun onMove(e : PlayerMoveEvent) {
        if (e.player.isSprinting) {
            if (Main.random.nextInt(100) == 0) {
                Main.playerTemp[e.player.uniqueId] = Main.playerTemp[e.player.uniqueId]!! + 0.1
            }
        }

        if (e.player.vehicle?.type == EntityType.BOAT) {
            if (Main.random.nextInt(80) == 0) {
                Main.playerTemp[e.player.uniqueId] = Main.playerTemp[e.player.uniqueId]!! + 0.1
            }
        }
    }

    @EventHandler
    fun onBreak(e : BlockBreakEvent) {
        if (e.block.isSolid) {
            if (Main.random.nextInt(15) == 0) {
                Main.playerTemp[e.player.uniqueId] = Main.playerTemp[e.player.uniqueId]!! + 0.1
            }
        }
    }
}