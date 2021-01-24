package com.parkermc.coins.events;

import com.parkermc.coins.CoinsConfig;
import com.parkermc.coins.proxy.ProxyClient;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class EventHandlerClientConnection {
	
	@SubscribeEvent
	public void onDisconnect(ClientDisconnectionFromServerEvent event) {
		ProxyClient.serverConfig = null;
		CoinsConfig.readConfig();
	}
}
