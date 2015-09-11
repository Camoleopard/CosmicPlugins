package me.temporary.serials;

import java.util.ArrayList;
import java.util.List;

import me.camoleopard.toolbox.Util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;


public class BlockSerializer {
	
 public static List<String> saveBlockAsString(Block b){
	 
	List<String> blockData = new ArrayList<String>(); 
	String location = Util.loc2str(b.getLocation());
	String material = b.getType().toString();
	blockData.add(location);
	blockData.add(material);
	
	return blockData;	
	}
 
public static Block loadBlock(List<String> list){
Location loc = Util.str2loc(list.get(0));	
Block b = loc.getBlock();

Material m = Material.getMaterial(list.get(1));

b.setType(m);

return b;

	
}

}
