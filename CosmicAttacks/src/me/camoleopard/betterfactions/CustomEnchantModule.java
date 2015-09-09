package me.camoleopard.betterfactions;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class CustomEnchantModule implements Listener {
	
	public static JavaPlugin owningPlugin = null;
	
	public CustomEnchantModule(JavaPlugin jPlugin) {
		owningPlugin = jPlugin;
	}
	
	@EventHandler
	public void entityDamageByEntityHandler(EntityDamageByEntityEvent e){
		if(((Player)e.getDamager()).getItemInHand() != null){
			for(String lore:((Player)e.getDamager()).getInventory().getItemInHand().getItemMeta().getLore()){
				int level = 0;
				for(Character c:lore.toLowerCase().toCharArray()){
					if(c.equals('i'))
						level++;
				}
				
				if(lore.toLowerCase().contains("tipped blade")){
					tippedBlade(level-1, e);
				}
				
				if(lore.toLowerCase().contains("thor's wrath")){
					thorsWrath(level, e);
				}
			}
		}
	}
	
	@EventHandler
	public void interactHandler(PlayerInteractEvent e){
		if(e.getPlayer().getInventory().getItemInHand()==null)
			return;
		if(!(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK))
			return;
		if(!(e.getPlayer().isSneaking()))
			return;
		
		for(String lore:e.getPlayer().getInventory().getItemInHand().getItemMeta().getLore()){
			
			int level = 0;
			for(Character c:lore.toLowerCase().toCharArray()){
				if(c.equals('i'))
					level++;
			}
			
			if(lore.toLowerCase().contains("titan wind")){
				titanWind(level-2, e);
			}
		}
	}
	
	public static void titanWind(int level, PlayerInteractEvent e){
		for(Entity le:e.getPlayer().getNearbyEntities(10, 10, 10)){
			if(le instanceof LivingEntity){
				((LivingEntity)le).setVelocity(new Vector(0,5,0));
			}
		}
	}
	
	public static void thorsWrath(int level, EntityDamageByEntityEvent e){
		// 1/15 chance of triggering, +1/15 for every extra level, maximum of 5/15 or 1/3
		if(new Random().nextInt(14)<(1+level)){
			Bukkit.getWorlds().get(0).strikeLightning(e.getEntity().getLocation());
		}
	}
	
	public static void tippedBlade(int level, EntityDamageByEntityEvent e){
		
		CircleEffect effect = new CircleEffect(BetterFactions.em);
		effect.radius = 3-level/1.5f;
		effect.speed=level;
		effect.delay=3-level;
		effect.iterations = 100;
		effect.setEntity(e.getEntity());
		
		// 1/40 chance of each scenario, 9/10 chance of nothing happening
		switch(new Random().nextInt(39)){
			case 0:
			case 1:
				((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, level));
				((Player)e.getDamager()).sendMessage("Your Tipped Sword affected your enemy with slowness " + level + "!");
				effect.particle=ParticleEffect.SLIME;
				effect.start();
				break;
			case 2:
				if(level==1)break;
				((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, level-1)); 
				((Player)e.getDamager()).sendMessage("Your Tipped Sword affected your enemy with blindness " + (level-1) + "!");
				effect.particle = ParticleEffect.SUSPENDED_DEPTH;
				effect.start();
				break;
			case 3:
				if(level==1)break;
				((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, level-1));
				((Player)e.getDamager()).sendMessage("Your Tipped Sword affected your enemy with poison " + (level-1) + "!");
				effect.particle = ParticleEffect.SPELL_WITCH;
				effect.start();
				break;
			default:
				
		}
	}

}
