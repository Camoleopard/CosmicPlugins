package util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
@SuppressWarnings({ })
public class utils implements Listener {
	public static List<Player> getPlayersInRadius(Player p, int radius){	
		double maxDist = radius;	
		List<Player> players = new ArrayList<Player>();
		
		for (Player other : Bukkit.getOnlinePlayers()) {
			if (other.getLocation().distance(p.getLocation()) <= maxDist) {
				players.add(other); 		 
			}		    
		}
		return players;	
	}
	
	public static List<LivingEntity> getEntitiesInRadius(LivingEntity e, int radius){	
		double maxDist = radius;
		List<LivingEntity> entities = new ArrayList<LivingEntity>();
		
		for (LivingEntity other : e.getWorld().getLivingEntities()) {
			if (other.getLocation().distance(e.getLocation()) <= maxDist) {
				entities.add(other); 		 
			}		    
		}
		return entities;	
	}
}
