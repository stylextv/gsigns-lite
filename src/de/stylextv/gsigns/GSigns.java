package de.stylextv.gsigns;

import org.bukkit.plugin.java.JavaPlugin;

import de.stylextv.gsigns.command.CommandManager;
import de.stylextv.gsigns.image.color.ColorSpace;
import de.stylextv.gsigns.image.color.io.asset.ColorSpaceAsset;
import de.stylextv.gsigns.packet.PacketManager;
import de.stylextv.gsigns.util.async.AsyncUtil;
import de.stylextv.gsigns.world.map.MapImageManager;

public class GSigns extends JavaPlugin {
	
	private static GSigns instance;
	
	public GSigns() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		CommandManager.registerPluginCommands();
		
		PacketManager.start();
		
		MapImageManager.loadImages();
		
		ColorSpace s = new ColorSpace();
		
		s.update();
		
		ColorSpaceAsset a = new ColorSpaceAsset("1_16.dat");
		
		a.writeSpace(s);
	}
	
	@Override
	public void onDisable() {
		MapImageManager.saveImages();
		
		AsyncUtil.shutdown();
	}
	
	public static GSigns getInstance() {
		return instance;
	}
	
}
