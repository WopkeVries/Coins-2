package com.parkermc.coins.gui;


import java.util.List;

import com.parkermc.coins.CoinsMod;
import com.parkermc.coins.proxy.ProxyCommon;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class GuiModConfig extends GuiConfig {
	private static final String LANG_PREFIX = CoinsMod.MODID + ".category.";

	public GuiModConfig(GuiScreen parentScreen) {
		super(parentScreen, getConfigElements(), CoinsMod.MODID, false, false, I18n.format(CoinsMod.MODID + ".config.title"));
	}

	private static List<IConfigElement> getConfigElements() {
		final Configuration configuration = ProxyCommon.config;

		final ConfigCategory topLevelCategory = configuration.getCategory(Configuration.CATEGORY_GENERAL);
		topLevelCategory.getChildren().forEach(configCategory -> configCategory.setLanguageKey(LANG_PREFIX + configCategory.getName()));

		return new ConfigElement(topLevelCategory).getChildElements();
	}
}