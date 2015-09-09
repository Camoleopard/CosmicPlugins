package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectManager;

public class BetterFactions extends JavaPlugin{
	public static EffectManager em;
	
	ItemStack sphere = new ItemStack(Material.DIAMOND);
	
	public static List<Player> players = new ArrayList<Player>();
	
	public void onEnable(){
		em = new EffectManager(this);
		Bukkit.getServer().getPluginManager().registerEvents(new SphereListener(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomEnchantModule(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomTNTModule(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MiscModule(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomKits(), this);
	}
	
	public void onDisable(){
		
	}
 	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player))
			return false;
		
		ItemStack sphere = new ItemStack(Material.DIAMOND);
		String name = "§3Sphere";
		ItemMeta spheremeta = sphere.getItemMeta();
		spheremeta.setDisplayName(name);
		sphere.setItemMeta(spheremeta);
		
		if(cmd.getName().equalsIgnoreCase("sphere")){
			Player p = (Player) sender;
			Inventory inv = p.getInventory();
			inv.addItem(sphere);
		}
		
		if(cmd.getName().equalsIgnoreCase("cenchant")){
			List<String> lore = new ArrayList<String>();
			ItemStack itemStack = ((Player)sender).getInventory().getItemInHand();
			ItemMeta itemMeta = itemStack.getItemMeta();
			String loreStr="§4";
			for(String str:args)
				loreStr=loreStr+str+" ";
			if(itemMeta.getLore()!=null)lore.addAll(itemMeta.getLore());
			lore.add(loreStr);
			itemMeta.setLore(lore);
			itemStack.setItemMeta(itemMeta);
			((Player)sender).getInventory().setItemInHand(itemStack);
		}
		
		if(cmd.getName().equalsIgnoreCase("tnt")){
			Entity entity = ((Player)sender).getWorld().spawn(((Player)sender).getLocation(), TNTPrimed.class);
			entity.setMetadata("customTnt", new FixedMetadataValue(this, args[0]));
		}
		
		if(cmd.getName().equalsIgnoreCase("kit")){
			CustomKits.requestKit((Player)sender, args[0], this);
		}
		
		
		return false;	
	}
	
	

}
