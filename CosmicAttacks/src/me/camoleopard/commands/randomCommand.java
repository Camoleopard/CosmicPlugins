package me.camoleopard.commands;

import me.camoleopard.betterfactions.ItemGenerator;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class randomCommand implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("random")){
			 ((Player)sender).getInventory().addItem(ItemGenerator.generateRandomItem(ItemGenerator.randomMaterial()));
			}
		
		
		
		
		
		
		
		
		return false;	
	}

}
