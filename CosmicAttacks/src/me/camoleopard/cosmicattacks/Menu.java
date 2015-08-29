package me.camoleopard.cosmicattacks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class Menu {
	
	public static Inventory createMenu(int Size){
		Inventory inv = Bukkit.createInventory(null, Size, "Menu");
		utils.createDisplay(Material.REDSTONE_BLOCK, inv, 1 ,"§bDestroy Beacon", "Destroys Beacon");
		
		
		return inv;
		
	}

}
