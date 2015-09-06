package me.camoleopard.cosmicattacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class Menu {
	
	public static Inventory createMenu(int Size, String name){
		Inventory inv = Bukkit.createInventory(null, Size, name);
		 utils.createDisplay(Material.REDSTONE_BLOCK, inv,49,"§bDestroy Beacon", "Destroys Beacon");
		
		
		return inv;
		
	}

}
