package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.camoleopard.betterfactions.ItemGenerator.ItemEnum;
import me.camoleopard.toolbox.KitCollection;
import me.camoleopard.toolbox.Timer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomKits implements Listener{
	
	public static List<KitCollection> playersUsedKits = new ArrayList<KitCollection>();
	private static Random rand = new Random();
	
	public static boolean requestKit(Player p, String kitName, JavaPlugin main){
		System.out.println(playersUsedKits);
		for(KitCollection kc : playersUsedKits){
			if(kc.player==p){
				if(kc.timer.ifFinishedReset()){
					p.getInventory().addItem(new ItemStack(Material.TNT, 32));
					p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 16));
					p.getInventory().addItem(new ItemStack(Material.BREAD, 4));
					p.getInventory().addItem(ItemGenerator.itemFromEnum(ItemEnum.TNTPOUCH));
					
					p.sendMessage("Received kit basic! Wait another " + kc.timer.getInterval()/Timer.secondsToNano(1) + " seconds to use it again!");
					return true;
				} else {
					long maxTime = kc.timer.getInterval()/Timer.secondsToNano(1);
					double percent = kc.timer.getPercentageCompleted();
					p.sendMessage("Cannot use kit, wait another " + ( maxTime-Math.floor(percent*maxTime) ) + " seconds!");
					return false;
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public static void onPlayerJoin(PlayerLoginEvent e){
		for(int i=0; i<playersUsedKits.size(); i++){
			KitCollection kc=playersUsedKits.get(i);
			if(kc.player.getName().equals(e.getPlayer().getName())){
				playersUsedKits.remove(kc);
			}
		}
		playersUsedKits.add(new KitCollection(e.getPlayer(), new Timer(Timer.secondsToNano(5))));
	}
	
	@EventHandler
	public void onItemClick(PlayerInteractEvent e){
		
		if(e.getItem()==null)
			return;
		
		if(e.getAction() == Action.LEFT_CLICK_AIR ||e.getAction() == Action.LEFT_CLICK_BLOCK||e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(e.getPlayer().getInventory().getItemInHand().getItemMeta().getLore().get(0).equals("Gives a random amount of a random piece of custom tnt!")){
					switch(rand.nextInt(4)){
						case 0:
							String[] lore = {"This destructive warhead releases a spray of shrapnel before exploding, ",
									"dealing small amounts of damage over a large area!","§3Super Rare"};
							e.getPlayer().getInventory().setItemInHand(ItemGenerator.generateItem(Material.TNT, "§3Shrapnel TNT", rand.nextInt(8)+1, lore));
							break;
						case 1:
							String[] lore2 = {"This TNT is set off by body heat from nearby players or mobs, it does massive amounts of damage!","§3Rare"};
							e.getPlayer().getInventory().setItemInHand(ItemGenerator.generateItem(Material.TNT, "§3Heat Sensitive TNT", rand.nextInt(24)+1, lore2));
							break;
						case 2:
							String[] lore3 = {"A rare, high tech piece of TNT that disperses nanobots to deconstruct nearby blocks, ",
									"yielding a very high success rate and will collect spawners",
									"WARNING: The nanobots cannot operate in liquid, they will die on contact and fail to operate","§3Legendary"};
							e.getPlayer().getInventory().setItemInHand(ItemGenerator.generateItem(Material.TNT, "§3Deconstruction TNT", rand.nextInt(4)+1, lore3));
							break;
						case 3:
							e.getPlayer().getInventory().setItemInHand(ItemGenerator.generateItem(Material.TNT, "TNT", rand.nextInt(48)+1, new String[0]));
							break;
					}
					e.setCancelled(true);
				}
		}
	}
}
