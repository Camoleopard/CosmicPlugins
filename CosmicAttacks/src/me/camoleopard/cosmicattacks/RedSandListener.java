package me.camoleopard.cosmicattacks;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RedSandListener implements Listener {
	@EventHandler
	public void onPlayerClick(BlockPlaceEvent e){
		ItemStack redsand = new ItemStack( Material.SAND, (short) 1, (byte) 1 );
		ItemMeta itemmeta = redsand.getItemMeta();
		itemmeta.setDisplayName("Red Sand Bomb");
		redsand.setItemMeta(itemmeta);
		if(e.getBlockPlaced().getDrops().contains(redsand) && e.getBlock().getType() == Material.SAND){
		Player p = e.getPlayer();
		List<Entity> entity = p.getNearbyEntities(10, 10, 10);
		for(Entity ee : entity){
		if(ee instanceof FallingBlock){
			FallingBlock fb = (FallingBlock)ee;
		fb.setCustomName("RedSandBomb");
		}
		
			
		}
	}
	}//*/
	@EventHandler
	public void onBlockLand(EntityChangeBlockEvent e){
		if(e.getEntity() instanceof FallingBlock){
		FallingBlock fb = (FallingBlock)e.getEntity();
		ItemStack redsand = new ItemStack( Material.SAND, (short) 1, (byte) 2 );
		ItemMeta itemmeta = redsand.getItemMeta();
		itemmeta.setDisplayName("Red Sand Bomb");
		redsand.setItemMeta(itemmeta);
		if(fb.getMaterial() == Material.SAND && e.getBlock().getDrops() == redsand && fb.getCustomName() == "RedSandBomb"){
		fb.setDropItem(true);
		Location explode = fb.getLocation();
		Entity tnt = fb.getWorld().spawn(explode, TNTPrimed.class);
		((TNTPrimed)tnt).setFuseTicks(0);
		
			
		}
			
			
			
		}
	}
	

}
