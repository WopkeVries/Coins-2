package com.parkermc.coins.gui;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

import javax.annotation.Nullable;
import java.util.Set;

public class GuiModConfigFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {

	}
	
	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new GuiModConfig(parentScreen);
	}

	@Nullable
	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
}