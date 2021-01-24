package com.parkermc.coins;

import com.parkermc.coins.proxy.ProxyClient;
import com.parkermc.coins.proxy.ProxyCommon;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CoinsConfig {
	private static final String CATEGORY_GENERAL = "general";
	
	public static String hoverText = "";
	public static int maxStackSize = 64;
	
    public static void readConfig() {
        Configuration cfg = ProxyCommon.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            FMLLog.log.error("Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
                cfg.load();
            }
        }
        if(ProxyClient.serverConfig != null){
        	initGeneralConfig(ProxyClient.serverConfig);
        }
        CoinsItems.updateConfigData();
    }
    
    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        hoverText = cfg.getString("hoverText", CATEGORY_GENERAL, hoverText, "The text that appears when you hover over the coin.");
        maxStackSize = cfg.getInt("maxStackSize", CATEGORY_GENERAL, maxStackSize, 1, 64, "The max number of coins you can stack.");
    }
    
    @Mod.EventBusSubscriber
	static class ConfigurationHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(CoinsMod.MODID) && ProxyCommon.config.hasChanged()) {
				ProxyCommon.config.save();
				readConfig();
			}
		}
	}
}
