package me.camoleopar.serials;

import java.util.ArrayList;
import java.util.List;

import me.camoleopard.cosmicattacks.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;


public class BlockSerializer {
	
 public static List<String> saveBlockAsString(Block b){
	 
	List<String> blockData = new ArrayList<String>(); 
	String location = utils.loc2str(b.getLocation());
	String material = b.getType().toString();
	blockData.add(location);
	blockData.add(material);
	
	return blockData;	
	}
 
public static Block loadBlock(List<String> list){
Location loc = utils.str2loc(list.get(0));	
Block b = loc.getBlock();

Material m = Material.getMaterial(list.get(1));

b.setType(m);

return b;

	
}

}
