package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemGenerator {
	
	public static enum ItemEnum{TNTPOUCH};
	
	public static ItemStack generateRandomItem(Material mat){
		Random r = new Random();
		ItemStack item = new ItemStack(mat);
		// Store all possible enchantments for the item
	    List<Enchantment> possible = new ArrayList<Enchantment>();
	 
	    // Loop through all enchantemnts
	    for (Enchantment ench : Enchantment.values()) {
	        
	        
	            possible.add(ench);
	        
	    }
	  int enchantAmount = r.nextInt(10);
	  for(int i = 0; i < enchantAmount; i++){
	    // If we have at least one possible enchantment
	    if (possible.size() >= 1) {
	        // Randomize the enchantments
	        Collections.shuffle(possible);
	        // Get the first enchantment in the shuffled list
	        Enchantment chosen = possible.get(0);
	        if(!(item.containsEnchantment(chosen))){
	        item.addUnsafeEnchantment(chosen, 1 + (int) (Math.random() * ((chosen.getMaxLevel() - 1) + 1)));
	        }else{
	        	Collections.shuffle(possible);
	        }
	    }
	 
	  }
		
		
		return item;
		
	}
	
	public static ItemStack generateItem(Material mat, String name, int amount, String[] lore,boolean hasEnchant, Enchantment[] ench, int[] enchantlevel){
		ItemStack itemStack = new ItemStack(mat);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		List<String> lore2 = new ArrayList<String>();
		for(String str:lore)
			lore2.add(str);
		
		itemMeta.setLore(lore2);
		itemMeta.setDisplayName(name);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
	if(hasEnchant){
		for(Enchantment e: ench){
		for(int i: enchantlevel){
			itemStack.addUnsafeEnchantment(e, i);
		}
		}
	}
		return itemStack;
	}
	
	public static ItemStack itemFromEnum(ItemEnum ie){
		switch(ie){
			case TNTPOUCH:
				String[] lore = {"Gives a random amount of a random piece of custom tnt!"};
				return generateItem(Material.CAULDRON_ITEM, "TNT Pouch", 1, lore,false,null,null);
			default:
				return null;
		}
	}
public static Material randomMaterial(){
	Random r = new Random();
	List<Material> materialpossible = new ArrayList<Material>();
	for(Material m :Material.values()){
		materialpossible.add(m);
	}
	Collections.shuffle(materialpossible);
	Material m = materialpossible.get(r.nextInt(materialpossible.size()));
	return m;
}
}
