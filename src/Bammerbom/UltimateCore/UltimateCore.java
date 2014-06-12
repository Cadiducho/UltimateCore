package Bammerbom.UltimateCore;

import java.io.IOException;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import Bammerbom.UltimateCore.UltimateUpdater.UpdateType;
import Bammerbom.UltimateCore.Commands.CmdBack;
import Bammerbom.UltimateCore.Commands.CmdBan;
import Bammerbom.UltimateCore.Commands.CmdButcher;
import Bammerbom.UltimateCore.Commands.CmdCi;
import Bammerbom.UltimateCore.Commands.CmdEffect;
import Bammerbom.UltimateCore.Commands.CmdEnchant;
import Bammerbom.UltimateCore.Commands.CmdFeed;
import Bammerbom.UltimateCore.Commands.CmdFly;
import Bammerbom.UltimateCore.Commands.CmdFreeze;
import Bammerbom.UltimateCore.Commands.CmdGTool;
import Bammerbom.UltimateCore.Commands.CmdGive;
import Bammerbom.UltimateCore.Commands.CmdGm;
import Bammerbom.UltimateCore.Commands.CmdGod;
import Bammerbom.UltimateCore.Commands.CmdHat;
import Bammerbom.UltimateCore.Commands.CmdHeal;
import Bammerbom.UltimateCore.Commands.CmdHome;
import Bammerbom.UltimateCore.Commands.CmdIP;
import Bammerbom.UltimateCore.Commands.CmdInv;
import Bammerbom.UltimateCore.Commands.CmdKick;
import Bammerbom.UltimateCore.Commands.CmdKill;
import Bammerbom.UltimateCore.Commands.CmdKillAll;
import Bammerbom.UltimateCore.Commands.CmdKittycannon;
import Bammerbom.UltimateCore.Commands.CmdLag;
import Bammerbom.UltimateCore.Commands.CmdList;
import Bammerbom.UltimateCore.Commands.CmdMe;
import Bammerbom.UltimateCore.Commands.CmdMobTP;
import Bammerbom.UltimateCore.Commands.CmdMore;
import Bammerbom.UltimateCore.Commands.CmdMsg;
import Bammerbom.UltimateCore.Commands.CmdMute;
import Bammerbom.UltimateCore.Commands.CmdNick;
import Bammerbom.UltimateCore.Commands.CmdPlugin;
import Bammerbom.UltimateCore.Commands.CmdPowertool;
import Bammerbom.UltimateCore.Commands.CmdRegion;
import Bammerbom.UltimateCore.Commands.CmdReload;
import Bammerbom.UltimateCore.Commands.CmdRemoveAll;
import Bammerbom.UltimateCore.Commands.CmdRepair;
import Bammerbom.UltimateCore.Commands.CmdSave;
import Bammerbom.UltimateCore.Commands.CmdSay;
import Bammerbom.UltimateCore.Commands.CmdSetSpawn;
import Bammerbom.UltimateCore.Commands.CmdSmite;
import Bammerbom.UltimateCore.Commands.CmdSpawn;
import Bammerbom.UltimateCore.Commands.CmdSpawnmob;
import Bammerbom.UltimateCore.Commands.CmdSpeed;
import Bammerbom.UltimateCore.Commands.CmdStop;
import Bammerbom.UltimateCore.Commands.CmdTime;
import Bammerbom.UltimateCore.Commands.CmdTop;
import Bammerbom.UltimateCore.Commands.CmdTp;
import Bammerbom.UltimateCore.Commands.CmdUC;
import Bammerbom.UltimateCore.Commands.CmdVanish;
import Bammerbom.UltimateCore.Commands.CmdWarp;
import Bammerbom.UltimateCore.Commands.CmdWeather;
import Bammerbom.UltimateCore.Commands.CmdWorkbench;
import Bammerbom.UltimateCore.Commands.CmdWorld;
import Bammerbom.UltimateCore.Events.EventAFK;
import Bammerbom.UltimateCore.Events.EventAutosave;
import Bammerbom.UltimateCore.Events.EventBleed;
import Bammerbom.UltimateCore.Events.EventChat;
import Bammerbom.UltimateCore.Events.EventColorSign;
import Bammerbom.UltimateCore.Events.EventDeathmessages;
import Bammerbom.UltimateCore.Events.EventExplosion;
import Bammerbom.UltimateCore.Events.EventActionMessage;
import Bammerbom.UltimateCore.Events.EventMOTD;
import Bammerbom.UltimateCore.Events.EventMessages;
import Bammerbom.UltimateCore.Events.EventMinecraftServers;
import Bammerbom.UltimateCore.Events.EventNoRespawnScreen;
import Bammerbom.UltimateCore.Events.EventSpawn;
import Bammerbom.UltimateCore.Events.EventTimber;
import Bammerbom.UltimateCore.Events.EventUnknownCommand;
import Bammerbom.UltimateCore.Events.EventWeather;
import Bammerbom.UltimateCore.Minigames.MinigameManager;
import Bammerbom.UltimateCore.Resources.BossBar;
import Bammerbom.UltimateCore.Resources.FireworkEffectPlayer;
import Bammerbom.UltimateCore.Resources.Window;
import Bammerbom.UltimateCore.Resources.Databases.BlockDatabase;
import Bammerbom.UltimateCore.Resources.Databases.ItemDatabase;
import Bammerbom.UltimateCore.Resources.Utils.GhostsUtil;
import Bammerbom.UltimateCore.Resources.Utils.InventoryUtil;
import Bammerbom.UltimateCore.Resources.Utils.SettingsUtil;

public class UltimateCore extends JavaPlugin{
	Boolean t_ = false; //TESTMODE
	static MinigameManager minigames = null;
	static Window window;	
	static BlockDatabase database;
	static ItemDatabase items;
	 public static Economy economy = null;
	@Override
	public void onEnable(){
		new r(this);
		r.log(ChatColor.GREEN + "Enabling Ultimate Core...");
		//Register all classes
	    new UltimateFileLoader(this);
	    new UltimateCommands(this);
		UltimateFileLoader.Enable();
		new InventoryUtil(this);
	    //TODO new classload-system
	    new CmdBack(this);
	    new CmdBan(this);
	    new CmdButcher(this);
	    new CmdCi(this);
	    new CmdEffect(this);
	    new CmdEnchant(this);
	    new CmdFeed(this);
	    new CmdFly(this);
	    new CmdFreeze(this);
	    new CmdGive(this);
	    new CmdGm(this);
	    new CmdGod(this);
	    new CmdHat(this);
	    new CmdHeal(this);
	    new CmdHome(this);
	    new CmdInv(this);
	    new CmdIP(this);
	    new CmdKick(this);
	    new CmdKill(this);
	    new CmdKillAll(this);
	    new CmdLag(this);
	    new CmdList(this);
	    new CmdMe(this);
	    new CmdMobTP(this);
	    new CmdMore(this);
	    new CmdMsg(this);
	    new CmdMute(this);
	    new CmdNick(this);
	    new CmdPlugin(this);
	    new CmdPowertool(this);
	    new CmdReload(this);
	    new CmdRemoveAll(this);
	    new CmdRepair(this);
	    new CmdSave(this);
	    new CmdSay(this);
	    new CmdSetSpawn(this);
	    new CmdSmite(this);
	    new CmdSpawn(this);
	    new CmdSpawnmob(this);
	    new CmdSpeed(this);
	    new CmdStop(this);
	    new CmdTime(this);
	    new CmdTop(this);
	    new CmdTp(this);
	    new CmdUC(this);
	    new CmdVanish(this);
	    new CmdWarp(this);
	    new CmdWeather(this);
	    new CmdWorkbench(this);
	    new CmdWorld(this);
	    new CmdKittycannon(this);
	    new CmdGTool(this);
	    new CmdRegion(this);
	    //
	    new EventAFK(this);
	    new EventAutosave(this);
	    new EventBleed(this);
	    new EventChat(this);
	    new EventColorSign(this);
	    new EventDeathmessages(this);
	    new EventExplosion(this);
	    new EventActionMessage(this);
	    new EventMessages(this);
	    new EventMinecraftServers(this);
	    new EventMOTD(this);
	    new EventNoRespawnScreen(this);
	    new EventSpawn(this);
	    new EventTimber(this);
	    new EventUnknownCommand(this);
	    new EventWeather(this);
	    //
	    new FireworkEffectPlayer();
	    new GhostsUtil(this);
	    new BossBar(this);
	    new SettingsUtil(this);
	    //
	    items = new ItemDatabase(this);
	    items.enable();
		database = new BlockDatabase(this);
		database.enable();
		//
	    minigames = new MinigameManager(this);
	    Integer amount = minigames.loadArenas();
	    r.log(ChatColor.YELLOW + "Loaded " + amount + " minigame arenas.");
	    //
	    r.log(ChatColor.YELLOW + "Loaded commands and events.");
	    //TODO end
		if(getConfig().getBoolean("console")){
		window = new Window(this);
		}
		if(getConfig().getBoolean("updater") == true){
			new UltimateUpdater(this, 66979, this.getFile(), UpdateType.DEFAULT, true);
			r.log(ChatColor.YELLOW + "Loaded updater");
		}else{
			r.log(ChatColor.YELLOW + "Updater disabled in config.");
		}
		if(getConfig().getBoolean("metrics") == true){
			UltimateMetrics metrics = null;
			try {
				metrics = new UltimateMetrics(this);
			} catch (IOException e) {
			}
			metrics.start();
			r.log(ChatColor.YELLOW + "Loaded plugin metrics.");
		}else{
			r.log(ChatColor.YELLOW + "Metrics disabled in config.");
		}
		r.log(ChatColor.GREEN + "Enabled Ultimate Core!");	
		CmdWorld.loadws();
		if(t_) new t(this);
	}
    public static BlockDatabase getSQLdatabase(){
    	return database;
    }
	public static MinigameManager getMinigamesManager(){
		return minigames;
	}
	public static Window getWindow(){
		return window;
	}
	@Override
	public void onDisable(){
		Bukkit.getScheduler().cancelTasks(this);
		getServer().getServicesManager().unregisterAll(this);
		if(window != null){
		window.close();
		}
		BossBar.disable();
		System.gc();
		database.disable();
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		UltimateCommands.onCmd(sender, cmd, label, args);
		return true;
	}
}
