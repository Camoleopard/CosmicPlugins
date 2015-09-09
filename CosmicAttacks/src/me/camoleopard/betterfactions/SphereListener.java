package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.List;

import me.camoleopard.toolbox.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;

import de.slikey.effectlib.effect.SphereEffect;
import de.slikey.effectlib.util.ParticleEffect;
public class SphereListener implements Listener {
	
	public static List<Player> players = new ArrayList<Player>();	
	public static JavaPlugin owningPlugin = null;
	
	public SphereListener(JavaPlugin jPlugin) {
		owningPlugin = jPlugin;
	}

	@EventHandler
	public void clickEvent(PlayerInteractEvent e){
		ItemStack sphere = new ItemStack(Material.DIAMOND);
		String name = "§3Sphere";
		ItemMeta spheremeta = sphere.getItemMeta();
		spheremeta.setDisplayName(name);
		sphere.setItemMeta(spheremeta);
		
		Player p = e.getPlayer();
		
		if(e.getItem().getItemMeta().getDisplayName()!=null && e.getItem().getItemMeta().getDisplayName().equals("§3Sphere")){
			if(e.getAction() == Action.LEFT_CLICK_AIR ||e.getAction() == Action.LEFT_CLICK_BLOCK||e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){

				SphereEffect effect = new SphereEffect(BetterFactions.em);
				effect.radius = 10;
				effect.iterations = 5*20;
				effect.particle = ParticleEffect.FLAME;
				effect.setEntity(p);
				effect.start();
				
			}
			
			for(Player pp : utils.getPlayersInRadius(p,10)){
				Rel relationship = MPlayer.get(p).getRelationTo(MPlayer.get(pp));
				
				if(!(relationship.isFriend()) ){
					pp.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 1));
				}	
			}
		}
	}
}
