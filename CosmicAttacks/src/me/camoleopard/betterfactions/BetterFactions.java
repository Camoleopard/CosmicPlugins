package me.camoleopard.betterfactions;

import java.util.ArrayList;
import java.util.List;

import me.camoleopard.commands.randomCommand;
import me.camoleopard.toolbox.Itemstacktostring;
import me.temporary.SLAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import de.slikey.effectlib.EffectManager;

public class BetterFactions extends JavaPlugin implements CommandExecutor{
	public static EffectManager em;
	
	ItemStack sphere = new ItemStack(Material.DIAMOND);
	
	public static List<Player> players = new ArrayList<Player>();
	
	public void onEnable(){
		em = new EffectManager(this);
		Bukkit.getServer().getPluginManager().registerEvents(new SphereListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomEnchantModule(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomTNTModule(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new MiscModule(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new CustomKits(this), this);
		this.saveDefaultConfig();
	getCommand("random").setExecutor(new randomCommand());

	}
	
	public void onDisable(){
		
	}
 	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p =(Player)sender;
		if(!(sender instanceof Player))
			return false;
		
		if(cmd.getName().equalsIgnoreCase("saveitem")){
		String str = Itemstacktostring.deserialize(p.getItemInHand());
		try{
			SLAPI.save(str, "itemstack.txt");
		}catch(Exception e){
			
		}
		
		}
		
		
		if(cmd.getName().equalsIgnoreCase("cenchant")){
			List<String> lore = new ArrayList<String>();
			ItemStack itemStack = ((Player)sender).getInventory().getItemInHand();
			ItemMeta itemMeta = itemStack.getItemMeta();
			String loreStr="§4";
			for(String str:args)
				loreStr=loreStr+str+" ";
			if(itemMeta.getLore()!=null)lore.addAll(itemMeta.getLore());
			lore.add(loreStr);
			itemMeta.setLore(lore);
			itemStack.setItemMeta(itemMeta);
			((Player)sender).getInventory().setItemInHand(itemStack);
		}
		
		if(cmd.getName().equalsIgnoreCase("tnt")){
			if(args[0].length() == 0){
				
			}else if(args.length == 1){
			Entity entity = ((Player)sender).getWorld().spawn(((Player)sender).getLocation(), TNTPrimed.class);
			entity.setMetadata("customTnt", new FixedMetadataValue(this, args[0]));
		}
		}
		
		if(cmd.getName().equalsIgnoreCase("kit")){
			CustomKits.openKitMenu((Player)sender);
		}
		if(cmd.getName().equalsIgnoreCase("random")){
		 ((Player)sender).getInventory().addItem(ItemGenerator.generateRandomItem(ItemGenerator.randomMaterial()));
		}
		if(cmd.getName().equalsIgnoreCase("createitem")){
			if(args.length > 6){
			p.sendMessage(ChatColor.RED+"Incorrect usage! /createitem type name lore amount enchant enchantlevel");	
			}else if(args.length < 6){
				p.sendMessage(ChatColor.RED+"Incorrect usage! /createitem type name lore amount enchant enchantlevel");	
			}else if(args.length == 6){
				int amount = 0;
				int level = 0;
				try{
				amount = Integer.parseInt(args[3]);
				
				}catch(NumberFormatException e){p.sendMessage(ChatColor.RED+"Amount and level must be an integer!");}
				String displayname = args[1];
				displayname.replaceAll("&", "§");
				displayname.replace("&", "§");
				
				String lore = args[2];
				lore.replaceAll("&", "§");
				
				List<String> lorelist = new ArrayList<String>();
				lorelist.add(lore);
				
				Material m = Material.getMaterial(args[0].toUpperCase());
				ItemStack item = new ItemStack(m,amount);
				
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(displayname);
				item.setItemMeta(meta);
				meta.setLore(lorelist);
				Enchantment ench = Enchantment.getByName(args[4].toUpperCase()); 
				item.addUnsafeEnchantment(ench, level);
				p.getInventory().addItem(item);
			}
			
		}
		if(cmd.getName().equalsIgnoreCase("senditemtoall")){
			ItemStack item = p.getItemInHand();
			List<Inventory> inv = new ArrayList<Inventory>();
			for(Player pp : Bukkit.getOnlinePlayers()){
			Inventory invofplayer = pp.getInventory();
			inv.add(invofplayer);
			
			}for(Inventory invo:inv){
				invo.addItem(item);
			}
			
		}
		
		return false;	
	}
	private ItemStack getColorArmor(Material m, Color c) {
        ItemStack i = new ItemStack(m, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
        meta.setColor(c);
        i.setItemMeta(meta);
        return i;
}
	

}
