package me.fxmxgragfx.vouchers

import me.fxmxgragfx.vouchers.CC.translateS
import me.fxmxgragfx.vouchers.Main.Companion.instance
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*

object VerifyClass {
    @JvmStatic
    var players: List<Player>? = null
        private set
    var inventory: Inventory? = null
        private set

    fun setup() {
        players = ArrayList()
    }

    fun setupInventory(name: String) {
        inventory = Bukkit.createInventory(null, 27, translateS(instance!!.config.getString("VOUCHERS.$name.CONFIRMATION_GUI_NAME")))
    }
}