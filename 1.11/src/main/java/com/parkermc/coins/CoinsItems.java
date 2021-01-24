package com.parkermc.coins;

import com.parkermc.coins.items.*;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class CoinsItems {
	public static ItemCoin coin;
	
	public static void preInit() {
		GameRegistry.register(coin = new ItemCoin());
	}
	
	public static void initModels() {
		coin.initModel();
	}
	
	public static void updateConfigData() {
		if(coin != null) {
			coin.updateConfigData();
		}
	}
}
