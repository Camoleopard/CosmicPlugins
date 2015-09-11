package me.temporary;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import me.camoleopard.toolbox.Util;


public class Menu {
	
	public static Inventory createMenu(int Size, String name){
		Inventory inv = Bukkit.createInventory(null, Size, name);
		Util.createDisplay(Material.REDSTONE_BLOCK, inv,49,"§bDestroy Beacon", "Destroys Beacon");
		
		return inv;
		
	}

}
