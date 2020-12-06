package me.fxmxgragfx.vouchers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryHandler implements Listener {

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        if(!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if(VerifyClass.getPlayers().contains(player)) {
        }
    }

    @EventHandler
    public void onPlayerClose(InventoryCloseEvent event) {
        if(!(event.getPlayer() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getPlayer();
        if(VerifyClass.getPlayers().contains(player)) {
            player.sendMessage(CC.translateS("&cVoucher redemption canceled!"));
            VerifyClass.getPlayers().remove(player);
        }
    }


}
