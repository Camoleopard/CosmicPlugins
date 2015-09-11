package me.camoleopard.toolbox;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Util implements Listener {
	
	public static List<Player> getPlayersInRadius(Player p, int radius){	
		double maxDist = radius;	
		List<Player> players = new ArrayList<Player>();
		
		for (Player other : Bukkit.getOnlinePlayers()) {
			if (other.getLocation().distance(p.getLocation()) <= maxDist) {
				players.add(other); 		 
			}		    
		}
		return players;	
	}
	
	public static List<LivingEntity> getEntitiesInRadius(LivingEntity e, int radius){	
		double maxDist = radius;
		List<LivingEntity> entities = new ArrayList<LivingEntity>();
		
		for (LivingEntity other : e.getWorld().getLivingEntities()) {
			if (other.getLocation().distance(e.getLocation()) <= maxDist) {
				entities.add(other); 		 
			}		    
		}
		return entities;	
	}
	
	public static List<Player> players = new ArrayList<Player>();

	public static void createDisplay(Material material, Inventory inv,int slot, String name, String lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> description = new ArrayList<String>();
		description.add(lore);
		meta.setLore(description);
		item.setItemMeta(meta);
		inv.setItem(slot, item);
	}
	
	public static Location str2loc(String str){
	    String str2loc[]=str.split("\\:");
	    Location loc = new Location(Bukkit.getServer().getWorld(str2loc[0]),0,0,0);
	    loc.setX(Double.parseDouble(str2loc[1]));
	    loc.setY(Double.parseDouble(str2loc[2]));
	    loc.setZ(Double.parseDouble(str2loc[3]));
	    return loc;
	 
	}
	
	public static String loc2str(Location loc){ 
	    return loc.getWorld().getName()+":"+loc.getBlockX()+":"+loc.getBlockY()+":"+loc.getBlockZ();
	}
	
	public static void createMenu(Player player, String name, int length, ItemStack[] itemstacks){
		Inventory inventory = Bukkit.getServer().createInventory(null, length, name);
		
		inventory.addItem(itemstacks);
		
		player.openInventory(inventory);
	}
}
