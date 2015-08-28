package me.camoleopard.cosmicattacks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectManager;



@SuppressWarnings({ })
public class Main<Return> extends JavaPlugin implements Listener {
	public static EffectManager em;
	
	ItemStack sphere = new ItemStack(Material.DIAMOND);
	
	
	
	 
	
	
	
	
	public static List<Player> players = new ArrayList<Player>();
	
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(new SphereListener(), this);
		em = new EffectManager(this);
	}
	
	public void onDisable(){
		
	}
 
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		ItemStack sphere = new ItemStack(Material.DIAMOND);
		String name = "Sphere";
		ItemMeta spheremeta = sphere.getItemMeta();
		spheremeta.setDisplayName(name);
		sphere.setItemMeta(spheremeta);
		
		if(cmd.getName().equalsIgnoreCase("sphere")){
			Player p = (Player) sender;
		Inventory inv = p.getInventory();
		inv.addItem(sphere);
			}
		
		
		return false;	
	}
	
	

}
