package me.camoleopard.betterfactions;


import java.util.HashMap;
import java.util.List;

import me.temporary.*;
import me.camoleopard.toolbox.Util;
import me.temporary.InventoryStringDeSerializer;
import me.temporary.serials.BlockSerializer;

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
public class SphereListener implements Listener {
	
	public HashMap<String, String> blockLocation = new  HashMap<String, String>();
	public HashMap<String, String> openInventory = new  HashMap<String, String>();
	public HashMap<String, List<String>> blocks = new  HashMap<String, List<String>>();
	public List<String> block;
	
	
	
	@EventHandler
	public void blockPlace (BlockPlaceEvent e){
	//SerializableLocation loc = new SerializableLocation(e.getBlockPlaced().getLocation());
	String loc = Util.loc2str(e.getBlockPlaced().getLocation());
	Player p = e.getPlayer();
	 Block b = e.getBlockPlaced();
	loadAll();
	if(blockLocation.get(loc) == null && b.getType() == Material.CAULDRON){
	
	blockLocation.put(loc, loc);
	p.sendMessage(ChatColor.GOLD + "Beacon Placed!");
	saveAll();
	}
	}
	
	@EventHandler
	public void interactEvent (PlayerInteractEvent e){
		Block b = e.getClickedBlock();
		Player p = e.getPlayer();
		block = BlockSerializer.saveBlockAsString(b);
		loadAll();
		String loc = Util.loc2str(e.getClickedBlock().getLocation());
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
			
		
			
		 if(openInventory.get(loc) != null && b.getType() == Material.CAULDRON){
			 
			 String invstring = openInventory.get(loc);
			 p.sendMessage(invstring);
			 Inventory invo = InventoryStringDeSerializer.StringToInventory(invstring);
				
			 p.openInventory(invo);
			 saveAll();
		 } else if(b.getType() == Material.CAULDRON){
			 Inventory inv = Menu.createMenu(54, "Menu");
			
				String invo = InventoryStringDeSerializer.InventoryToString(inv);
				blocks.put(invo, block );
				
				openInventory.put(loc, invo);
				saveAll();
					 
			
		 }
		}
		
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e){
		Inventory inv = e.getClickedInventory();
		Player p = (Player)e.getWhoClicked();
		String invo = InventoryStringDeSerializer.InventoryToString(inv);
		Block b = BlockSerializer.loadBlock(block);
		loadAll();

		if(blocks.get(invo) != null){
			p.sendMessage("Hi");
			if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bDestroy Beacon")){
			
				p.closeInventory();
				b.breakNaturally();
				saveAll();
				
			}
			
		}
		
	}
	@EventHandler
	public void destroyBlock(BlockBreakEvent e){
		loadAll();
	Player p = e.getPlayer();
	Location loco = e.getBlock().getLocation();
	


	if(blockLocation.get(Util.loc2str(loco)) != null){
		e.setCancelled(true);
		p.sendMessage(ChatColor.RED + "Break Via GUI");
	}
		
		
	}
	public void loadAll(){
		try{
			blockLocation = SLAPI.loadHash("Location.bin");	
			openInventory = SLAPI.loadHash("Inventory.bin");
			blocks = SLAPI.loadHash1("Block.bin");
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
	public void saveAll(){
		try{
			SLAPI.saveHash(blockLocation, "Location.bin");	
			SLAPI.saveHash(openInventory, "Inventory.bin");
			SLAPI.saveHash1(blocks, "Block.bin");
			}catch(Exception ex){
				ex.printStackTrace();
			}
	}
	
}