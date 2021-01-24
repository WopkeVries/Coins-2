package com.parkermc.coins.proxy;

import java.io.File;

import com.parkermc.coins.CoinsConfig;
import com.parkermc.coins.CoinsItems;
import com.parkermc.coins.CoinsMod;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon {
	public static Configuration config;
	
	public void preInit(FMLPreInitializationEvent event) {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), CoinsMod.MODID+".cfg"));
        CoinsConfig.readConfig();
		CoinsItems.preInit();
	}
	
	public void init(FMLInitializationEvent event) {
	}
	
	public void postInit(FMLPostInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
	}
}
