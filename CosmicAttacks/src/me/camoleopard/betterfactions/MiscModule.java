package me.camoleopard.betterfactions;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MiscModule implements Listener{
	
	public static JavaPlugin owningPlugin = null;
	
	public MiscModule(JavaPlugin jPlugin) {
		owningPlugin = jPlugin;
	}
	
	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent e){
		if(e.getBlockPlaced().getState() instanceof CreatureSpawner){
			for(String str:e.getItemInHand().getItemMeta().getLore()){
				if(str.contains("SpawnerType")){
					((CreatureSpawner)e.getBlockPlaced().getState()).setCreatureTypeByName(str.split(":")[1].trim());
				}
			}
		}
	}
}
