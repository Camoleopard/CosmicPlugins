package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemGenerator {
	
	public static enum ItemEnum{TNTPOUCH};
	
	public static ItemStack generateItem(Material mat, String name, int amount, String[] lore){
		ItemStack itemStack = new ItemStack(mat);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		List<String> lore2 = new ArrayList<String>();
		for(String str:lore)
			lore2.add(str);
		
		itemMeta.setLore(lore2);
		itemMeta.setDisplayName(name);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	public static ItemStack itemFromEnum(ItemEnum ie){
		switch(ie){
			case TNTPOUCH:
				String[] lore = {"Gives a random amount of a random piece of custom tnt!"};
				return generateItem(Material.CAULDRON_ITEM, "TNT Pouch", 1, lore);
			default:
				return null;
		}
	}

}
