package me.fxmxgragfx.vouchers

import me.fxmxgragfx.vouchers.CC.translate
import me.fxmxgragfx.vouchers.CC.translateS
import me.fxmxgragfx.vouchers.Main.Companion.instance
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class Handler : Listener {
    @EventHandler
    fun onPlayerClick(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        val itemInHand = player.itemInHand
        val config = instance!!.config
        for (s in config.getConfigurationSection("VOUCHERS").getKeys(false)) {
            val paper = config.getBoolean("VOUCHERS.$s.PAPER")
            val rBlock = config.getBoolean("VOUCHERS.$s.RIGHT_CLICK_BLOCK")
            val lBlock = config.getBoolean("VOUCHERS.$s.LEFT_CLICK_BLOCK")
            val rAir = config.getBoolean("VOUCHERS.$s.RIGHT_CLICK_AIR")
            val lAir = config.getBoolean("VOUCHERS.$s.LEFT_CLICK_AIR")
            val permission = config.getString("VOUCHERS.$s.PERMISSION_TO_USE")
            val command = config.getString("VOUCHERS.$s.COMMAND")
            val itemName = config.getString("VOUCHERS.$s.ITEM.NAME")
            val itemLore = config.getStringList("VOUCHERS.$s.ITEM.LORE")
            val paperItem = ItemStack(Material.PAPER)
            val meta = paperItem.itemMeta
            if (paper) {
                if (itemInHand.isSimilar(paperItem)) {
                    meta.displayName = translateS(itemName)
                    meta.lore = itemLore
                    paperItem.itemMeta = meta
                }
            }
            val book = config.getBoolean("VOUCHERS.$s.BOOK")
            val bookItem = ItemStack(Material.BOOK)
            val bookMeta = bookItem.itemMeta
            bookMeta.displayName = config.getString("VOUCHERS.$s.ITEM.NAME")
            val bookLore = config.getStringList("VOUCHERS.$s.ITEM.LORE")
            bookMeta.lore = translate(bookLore)
            bookItem.itemMeta = bookMeta
            if (book) {
                if (itemInHand.isSimilar(bookItem)) {
                    if (rBlock && lBlock && rAir && lAir) {
                        if (player.hasPermission(permission)) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)
                        }
                    }
                }
            }
            if (rBlock && action == Action.RIGHT_CLICK_BLOCK) {
            }
            if (lBlock && action == Action.LEFT_CLICK_BLOCK) {
            }
            if (rAir && action == Action.RIGHT_CLICK_AIR) {
            }
            if (lAir && action == Action.LEFT_CLICK_AIR) {
            }
        }
    }
}