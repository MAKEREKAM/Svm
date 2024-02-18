package kr.vanilage.main

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Biome
import org.bukkit.block.Furnace
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
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

    val increaseTempBlock =
        arrayOf(
            Material.LAVA,
            Material.SOUL_CAMPFIRE,
            Material.SOUL_FIRE
        )

    val randomIncreaseTempBlock =
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

    val decreaseTempBlock =
        arrayOf(
            Material.PACKED_ICE,
            Material.BLUE_ICE,
            Material.ICE,
            Material.WATER,
            Material.WET_SPONGE
        )

    val randomDecreaseTempBlock =
        arrayOf(
            Material.SNOW,
            Material.SNOW_BLOCK,
            Material.POWDER_SNOW,
            Material.FROSTED_ICE,
            Material.END_PORTAL,
            Material.END_GATEWAY
        )

    val increaseTempEntity =
        arrayOf(
            EntityType.MAGMA_CUBE,
            EntityType.BLAZE,
            EntityType.GHAST,
            EntityType.PRIMED_TNT,
            EntityType.WITHER_SKELETON,
            EntityType.WITHER
        )

    val randomIncreaseTempEntity =
        arrayOf(
            EntityType.HUSK,
            EntityType.FIREBALL,
            EntityType.SMALL_FIREBALL,
            EntityType.CREEPER
        )

    val decreaseTempEntity =
        arrayOf(
            EntityType.SNOWMAN,
            EntityType.SNOWBALL
        )

    val randomDecreaseTempEntity =
        arrayOf(
            EntityType.COD,
            EntityType.SALMON,
            EntityType.TROPICAL_FISH,
            EntityType.ELDER_GUARDIAN,
            EntityType.GUARDIAN,
            EntityType.DOLPHIN,
            EntityType.TURTLE,
            EntityType.DROWNED
        )

    val increaseTempBiome =
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

    val decreaseTempBiome =
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