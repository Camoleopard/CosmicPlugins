package me.camoleopard.toolbox;

import me.camoleopard.betterfactions.ItemGenerator;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemsThing {
public static JavaPlugin owningPlugin = null;
	
	public ItemsThing(JavaPlugin jPlugin) {
		owningPlugin = jPlugin;
	}
	
	String[] lorebow = {"A bow of heroes"};	
	double[] simpleItemsPercent = {0.1};
	Enchantment[] bowEnchants = {Enchantment.ARROW_DAMAGE,Enchantment.ARROW_FIRE,Enchantment.ARROW_INFINITE};
	int[] bowlevel = {5,2,1};
	
	ItemStack[] simpleItems = new ItemStack[30];
	public void thingsinotherthings(){
	simpleItems[0]=ItemGenerator.generateItem(Material.BOW, Util.format("&3Bow of Destiny"), 1, lorebow, true,bowEnchants, bowlevel);
	
	}
}
