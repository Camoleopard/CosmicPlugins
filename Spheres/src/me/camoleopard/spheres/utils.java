package me.camoleopard.spheres;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class utils {
	public static List<Player> players = new ArrayList<Player>();
	
public static List<Player> getPlayersInRadius(Player p, int radius){
		
		double maxDist = radius;
		for (Player other : Bukkit.getOnlinePlayers()) {
		  if (other.getLocation().distance(p.getLocation()) <= maxDist) {
			players.add(other); 
			 
		  }
		  
		  
		}
		return players;
	}

}
