package me.camoleopard.cosmicattacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.slikey.effectlib.EffectManager;
public class SphereListener implements Listener {
	
	EffectManager em = Main.em;
	
	public static List<Player> players = new ArrayList<Player>();
	public static HashMap<Location, Inventory> openInventory = new HashMap<Location, Inventory>();
	private HashMap<Location, Location> blockLocation = new HashMap<Location, Location>();
	
	@EventHandler
	public void playerClickEvent(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
		
		if(blockLocation.get(e.getClickedBlock().getLocation()) != null && e.getClickedBlock().getType() == Material.CAULDRON){
		 p.sendMessage("hello");
		 
		 if(openInventory.get(blockLocation.get(e.getClickedBlock().getLocation())) == null){
			Inventory inv = Menu.createMenu(54); 
			openInventory.put(e.getClickedBlock().getLocation(), inv);
			p.sendMessage(ChatColor.GOLD+"Menu created successfully!");
		 }
		 else{
			 Inventory inv = openInventory.get(blockLocation.get(e.getClickedBlock().getLocation()));
			 p.openInventory(inv);
		 }
		}
		
		
		}
		
	}
	
	
		
	
    @EventHandler
	public void playerPlaceBlock(BlockPlaceEvent e){
		Player p = e.getPlayer();
		if(e.getBlockPlaced().getType() == Material.CAULDRON ){// some name that can be easily distinguished
			blockLocation.put(e.getBlockPlaced().getLocation(), e.getBlockPlaced().getLocation());
			p.sendMessage("Beacon Placed!");
		}
	}
    
	@EventHandler
	public void playerBreakBeacon(BlockBreakEvent e){
	Player p = e.getPlayer();
	
	if(blockLocation.get(e.getBlock().getLocation()) != null && e.getBlock().getType() == Material.CAULDRON){
		e.setCancelled(true);
		Location loc = blockLocation.get(e.getBlock().getLocation());
		p.playSound(p.getLocation(), Sound.ITEM_BREAK, 10, 1);
		p.sendMessage(ChatColor.RED + "Cannot be destroyed, collect via GUI!");
		
	}
		
	}
	
	
	
}
