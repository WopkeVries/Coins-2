package com.parkermc.coins.proxy;

import com.parkermc.coins.CoinsItems;
import com.parkermc.coins.events.EventHandlerClientConnection;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyClient extends ProxyCommon {
	public static Configuration serverConfig = null;
	private EventHandlerClientConnection eventHandlerConnect = new EventHandlerClientConnection();
	
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		MinecraftForge.EVENT_BUS.register(eventHandlerConnect);
		CoinsItems.initModels();
	}
	
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}
