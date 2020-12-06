package me.fxmxgragfx.vouchers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Verify {

    private static List<Player> players;
    private static List<Player> trueInventory;
    private static Inventory inventory;

    public static void setup() {
        players = new ArrayList<Player>();
        trueInventory = new ArrayList<Player>();
    }

    public static void setupInventory(String name) {
        inventory = Bukkit.createInventory(null, 27, CC.translateS(Main.getInstance().getConfig().getString("VOUCHERS." + name + ".CONFIRMATION_GUI_NAME")));
        for (int i = 0; i < 9; ++i) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }

        for (int i = 9; i < 12; ++i ) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }

        ItemStack yes = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta yesMeta = yes.getItemMeta();
        yesMeta.setDisplayName(CC.translateS("&aYES"));
        List<String> lore = new ArrayList<String>();
        lore.add("&7- Click to accept");
        yesMeta.setLore(CC.translate(lore));
        yes.setItemMeta(yesMeta);
        inventory.setItem(12, yes);

        inventory.setItem(13, new ItemStack(Material.STAINED_GLASS_PANE));

        ItemStack no = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta noMeta = no.getItemMeta();
        noMeta.setDisplayName(CC.translateS("&cNO"));
        List<String> loreNo = new ArrayList<String>();
        loreNo.add("&7- Click to accept");
        noMeta.setLore(CC.translate(loreNo));
        no.setItemMeta(noMeta);
        inventory.setItem(14, no);

        for (int i = 14; i < 18; ++i) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }

        for (int i = 18; i < 27; ++i) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }

    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static boolean haveVoucher(Player player) {
        ItemStack itemInHand = player.getItemInHand();
        for(String s : Main.getInstance().getConfig().getConfigurationSection("VOUCHERS").getKeys(false)) {
            ItemStack paperStack = new ItemStack(Material.PAPER);
            ItemMeta paperMeta = paperStack.getItemMeta();
            paperMeta.setDisplayName(CC.translateS(Main.getInstance().getConfig().getString("VOUCHERS." + s + ".ITEM.NAME")));
            paperMeta.setLore(CC.translate(Main.getInstance().getConfig().getStringList("VOUCHERS." + s + ".ITEM.LORE")));
            paperStack.setItemMeta(paperMeta);
            ItemStack bookStack = new ItemStack(Material.BOOK);
            ItemMeta bookMeta = bookStack.getItemMeta();
            bookMeta.setDisplayName(CC.translateS(Main.getInstance().getConfig().getString("VOUCHERS." + s + ".ITEM.NAME")));
            bookMeta.setLore(CC.translate(Main.getInstance().getConfig().getStringList("VOUCHERS." + s + ".ITEM.LORE")));
            bookStack.setItemMeta(bookMeta);
            if(Main.getInstance().getConfig().getBoolean("VOUCHERS." + s + ".´PAPER") || Main.getInstance().getConfig().getBoolean("VOUCHERS." + s + ".´BOOK")) {
                if(itemInHand.isSimilar(paperStack) || itemInHand.isSimilar(bookStack)) {
                    if(Main.getInstance().getConfig().getBoolean("VOUCHERS." + s + ".NEED_CONFIRMATION")) {
                        setupInventory(s);
                        player.openInventory(getInventory());
                    }
                    return true;
                }
            } else {
                throw new Error("ERROR, BAD CONFIG.YML: <" + s + ">");
            }
        }
        return false;
    }
}
