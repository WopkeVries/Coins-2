package com.parkermc.coins.events;

import com.parkermc.coins.CoinsMod;
import com.parkermc.coins.network.MessageConfig;
import com.parkermc.coins.proxy.ProxyCommon;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandlerServerConnection {
	
	@SubscribeEvent
	public void onDisconnect(PlayerLoggedInEvent event) {
		CoinsMod.network.sendTo(new MessageConfig(ProxyCommon.config), (EntityPlayerMP)event.player);
	}
}
