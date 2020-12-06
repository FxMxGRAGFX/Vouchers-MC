package me.fxmxgragfx.vouchers

import me.fxmxgragfx.vouchers.CC.translateS
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Main : JavaPlugin() {
    override fun onEnable() {
        instance = this
        val config = File(dataFolder, "config.yml")
        if (!config.exists()) {
            getConfig().options().copyDefaults(true)
            saveConfig()
        }
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE.toString() + "[Vouchers] By FxMxGRAGFX!")
        getCommand("vouchers").executor = VCommand()
        getCommand("vouchers").permission = "vouchers.command"
        getCommand("vouchers").permissionMessage = translateS("You no have permissions to use this command!")
        server.pluginManager.registerEvents(Handler(), this)
        VerifyClass.setup()
    }

    override fun onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE.toString() + "[Vouchers] By FxMxGRAGFX!")
    }

    companion object {
        @JvmStatic
        var instance: Main? = null
            private set
    }
}