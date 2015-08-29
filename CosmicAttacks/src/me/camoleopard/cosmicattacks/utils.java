package me.camoleopard.cosmicattacks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
@SuppressWarnings({ })
public class utils implements Listener {
	public static List<Player> players = new ArrayList<Player>();
	
public static List<Player> getPlayersInRadius(Player p, int radius){
		
		double maxDist = radius;
		for (Player other : Bukkit.getOnlinePlayers()) {
		  if (other.getLocation().distance(p.getLocation()) <= maxDist) {
			players.add(other); 
			 
		  }
		  
		  
		}
		return players;
		
	}
public static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore) {
ItemStack item = new ItemStack(material);
ItemMeta meta = item.getItemMeta();
meta.setDisplayName(name);
ArrayList<String> Lore = new ArrayList<String>();
Lore.add(lore);
meta.setLore(Lore);
item.setItemMeta(meta);
 
inv.setItem(Slot, item); 
 
}

}
