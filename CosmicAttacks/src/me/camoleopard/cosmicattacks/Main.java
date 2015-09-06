package me.camoleopard.cosmicattacks;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectManager;



@SuppressWarnings({ })
public class Main<Return> extends JavaPlugin implements Listener {
	public static EffectManager em;
	
	ItemStack sphere = new ItemStack(Material.DIAMOND);
	public static HashMap<String, String> openInventory = new HashMap<String, String>();//Location string , inventory string
	public static HashMap<String, String> blockLocation = new HashMap<String, String>();//Location String, Location String
	public static HashMap<String, List<String>> block = new HashMap<String, List<String>>(); //Inventory string, block string
	
	public void onEnable(){
		
		Bukkit.getServer().getPluginManager().registerEvents(new SphereListener(), this);
		em = new EffectManager(this);
		try{
			
			openInventory = SLAPI.loadHash("Inventory.bin");
			blockLocation = SLAPI.loadHash("Block.bin");
			block = SLAPI.loadHash1("Location.bin");
		} catch(Exception e) {
		e.printStackTrace();	
		}
		
	}
	
	public void onDisable(){
		try{
			SLAPI.saveHash(openInventory,"Inventory.bin" );
			SLAPI.saveHash(blockLocation,"Location.bin" );
			SLAPI.saveHash1(block, "Block.bin");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
 
	
	
	

}
