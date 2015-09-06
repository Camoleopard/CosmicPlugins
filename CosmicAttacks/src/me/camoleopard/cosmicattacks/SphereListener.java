package me.camoleopard.cosmicattacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.camoleopar.serials.BlockSerializer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class SphereListener implements Listener {
	
	public HashMap<String, String> blockLocation = Main.blockLocation;
	public HashMap<String, String> openInventory = Main.openInventory;
	public HashMap<String, List<String>> blocks = Main.block;
	public List<String> block;
	
	
	
	@EventHandler
	public void blockPlace (BlockPlaceEvent e){
	//SerializableLocation loc = new SerializableLocation(e.getBlockPlaced().getLocation());
	String loc = utils.loc2str(e.getBlockPlaced().getLocation());
	Player p = e.getPlayer();
	 Block b = e.getBlockPlaced();
	
	if(blockLocation.get(loc) == null && b.getType() == Material.CAULDRON){
	
	blockLocation.put(loc, loc);
	p.sendMessage(ChatColor.GOLD + "Beacon Placed!");
	
	}
	}
	
	@EventHandler
	public void interactEvent (PlayerInteractEvent e){
		Block b = e.getClickedBlock();
		Player p = e.getPlayer();
		block = BlockSerializer.saveBlockAsString(b);
		
		
		String loc = utils.loc2str(e.getClickedBlock().getLocation());
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
		
			
		 if(openInventory.get(loc) != null && b.getType() == Material.CAULDRON){
			 
			 String invstring = openInventory.get(loc);
			 p.sendMessage(invstring);
			 Inventory invo = InventoryStringDeSerializer.StringToInventory(invstring);
				
			 p.openInventory(invo);
				
		 } else if(b.getType() == Material.CAULDRON){
			 Inventory inv = Menu.createMenu(54, "Menu");
			
				String invo = InventoryStringDeSerializer.InventoryToString(inv);
				blocks.put(invo, block );
				
				openInventory.put(loc, invo);
				
					 
			
		 }
		}
		
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		Player p = (Player)e.getWhoClicked();
		String invo = InventoryStringDeSerializer.InventoryToString(inv);
		Block b = BlockSerializer.loadBlock(block);
		
		if(blocks.get(invo) != null){
			p.sendMessage("Hi");
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bDestroy Beacon")){
			
				p.closeInventory();
				b.breakNaturally();
				
			}
			
		}
		
	}
	@EventHandler
	public void destroyBlock(BlockBreakEvent e){
	Player p = e.getPlayer();
	Location loco = e.getBlock().getLocation();
	


	if(blockLocation.get(utils.loc2str(loco)) != null){
		e.setCancelled(true);
		p.sendMessage(ChatColor.RED + "Break Via GUI");
	}
		
		
	}
	
	
}
