package com.parkermc.coins;

import com.parkermc.coins.items.*;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CoinsItems {
	public static ItemCoin coin;
	
	public static void preInit() {
		ForgeRegistries.ITEMS.register(coin = new ItemCoin());
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
