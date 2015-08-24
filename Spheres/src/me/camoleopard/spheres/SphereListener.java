package me.camoleopard.spheres;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.SphereEffect;
import de.slikey.effectlib.util.ParticleEffect;
public class SphereListener implements Listener {
	
	EffectManager em = Main.em;
	
	public static List<Player> players = new ArrayList<Player>();
	
	/*public static List<Player> getPlayersInRadius(Player p, int radius){
			
			double maxDist = radius;
			for (Player other : Bukkit.getOnlinePlayers()) {
			  if (other.getLocation().distance(p.getLocation()) <= maxDist) {
				players.add(other); 
				 
			  }
			  
			  
			}
			return players;
		}*/

	
	
	
	
	@EventHandler
	public void clickEvent(PlayerInteractEvent e){
		Boolean sphereIsActive;
		
		ItemStack sphere = new ItemStack(Material.DIAMOND);
		String name = "Sphere";
		ItemMeta spheremeta = sphere.getItemMeta();
		spheremeta.setDisplayName(name);
		sphere.setItemMeta(spheremeta);
		
		Player p = e.getPlayer();
		
		
		
		if(e.getItem().getItemMeta().getDisplayName() == "Sphere"){
			if(e.getAction() == Action.LEFT_CLICK_AIR ||e.getAction() == Action.LEFT_CLICK_BLOCK||e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK){
			sphereIsActive = true;
			SphereEffect effect = new SphereEffect(em);
			effect.radius = 10;
			effect.iterations = 5*20;
			effect.particle = ParticleEffect.FLAME;
			effect.setEntity(p);
			effect.start();
			
			
			
			
			
			if(effect.isDone()){
				sphereIsActive = false;
			}
		if(sphereIsActive){	
		for(Player pp : utils.getPlayersInRadius(p,10)){
			Rel relationship = MPlayer.get(p).getRelationTo(MPlayer.get(pp));
			
		if(!(relationship.isFriend()) && sphereIsActive ){
		pp.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 1));
		}
		
		}
		
		}
		
		}
		}
	}
}
