package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Item;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class CustomTNTModule implements Listener{
	
	public static JavaPlugin owningPlugin = null;
	
	public CustomTNTModule(JavaPlugin jPlugin) {
		owningPlugin = jPlugin;
	}
	
	//Because who cares about deprecation?
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onExplosion(ExplosionPrimeEvent e){
		if(e.getEntity().hasMetadata("customTnt")){
			if(e.getEntity().getMetadata("customTnt").get(0).asString().equals("shrapnel")){
				e.setCancelled(true);
				Random rand = new Random();
				for(int i=0; i<40; i++){
					TNTPrimed entity = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), TNTPrimed.class);
					entity.setVelocity(new Vector(rand.nextFloat()*1.5-0.75, rand.nextFloat()*1.5, rand.nextFloat()*1.5-0.75));
					entity.setYield(entity.getYield()/2);
					entity.setFuseTicks(20);
				}
			} else if(e.getEntity().getMetadata("customTnt").get(0).asString().equals("heatsensitive")){
				if(!(e.getEntity().getNearbyEntities(1.5, 1.5, 1.5).stream().filter((item) -> !(item instanceof Item)).anyMatch(((item) -> !(item instanceof TNTPrimed))))){
					e.setCancelled(true);
					TNTPrimed entity = e.getEntity().getWorld().spawn(e.getEntity().getLocation(), TNTPrimed.class);
					entity.setFuseTicks(1);
					entity.setYield(7);
					entity.setVelocity(new Vector(0,-0.1,0));
					entity.setMetadata("customTnt", new FixedMetadataValue(owningPlugin, "heatsensitive"));
				}
			} else if(e.getEntity().getMetadata("customTnt").get(0).asString().equals("deconstruction")){
				e.setCancelled(true);
				if(e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation()).isLiquid()){
					return;
				}
				for(int i=-2; i<3; i++){
					for(int j=-2; j<3; j++){
						for(int k=-2; k<3; k++){
							Block block = e.getEntity().getWorld().getBlockAt(new Location(e.getEntity().getWorld(), e.getEntity().getLocation().getBlockX()+i,e.getEntity().getLocation().getBlockY()+j,e.getEntity().getLocation().getBlockZ()+k));
							if(!block.isLiquid() && !block.isEmpty()){
								
								ItemStack item = new MaterialData(block.getType()).toItemStack(1);
								item.setDurability(block.getData());
								if(block.getState() instanceof CreatureSpawner){
									List<String> lore = new ArrayList<String>();
									ItemMeta itemMeta = item.getItemMeta();
									lore.add("SpawnerType: " + ((CreatureSpawner)block.getState()).getCreatureTypeName());
									itemMeta.setLore(lore);
									item.setItemMeta(itemMeta);
								}
								e.getEntity().getWorld().dropItem(block.getLocation(), item);
								block.setType(Material.AIR);
							}
						}
					}
				}
			} else if(e.getEntity().getMetadata("customTnt").get(0).asString().equals("nature")){
				e.setCancelled(true);
				TreeType tt = null;
				switch(new Random().nextInt(6)){
					case 0:
						tt = TreeType.BROWN_MUSHROOM;
						break;
					case 1:
						tt = TreeType.ACACIA;
						break;
					case 2:
						tt = TreeType.REDWOOD;
						break;
					case 3:
						tt = TreeType.SWAMP;
						break;
					case 4:
						tt = TreeType.SMALL_JUNGLE;
						break;
					case 5:
						tt = TreeType.DARK_OAK;
						break;
				}
				e.getEntity().getWorld().generateTree(e.getEntity().getLocation(), tt);
			}
		}
	}
	
	@EventHandler
	public static void onBlockPlace(BlockPlaceEvent e){
		switch(e.getPlayer().getInventory().getItemInHand().getItemMeta().getLore().get(0)){
			case "A rare, high tech piece of TNT that disperses nanobots to deconstruct nearby blocks, ":
				
		}
	}
}
