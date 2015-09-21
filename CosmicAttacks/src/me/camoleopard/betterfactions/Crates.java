package me.camoleopard.betterfactions;

import me.camoleopard.toolbox.Util;
import me.camoleopard.toolbox.ItemsEnum;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class Crates implements Listener {
	public void crates(){
	//()	
	}
	
	@EventHandler
	public void block(PlayerInteractEvent e, Boolean b){
		
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR ||e.getAction() == Action.RIGHT_CLICK_BLOCK){
		if(e.getPlayer().getItemInHand().getItemMeta().getDisplayName().contains("§0Crate")){
		switch( p.getItemInHand().getItemMeta().getDisplayName()){
		case "§6Legendary §0Crate" : Util.setItemsInArray(ItemsEnum.legendaryItems);
			break;
		case "§4Ultimate §0Crate": Util.setItemsInArray(ItemsEnum.ultimateItems);
			break;
		case "§1Ultra §1Rare §0Crate":
			break;
		case "":
			
		}
		}
	}	
	}
	
	
}
