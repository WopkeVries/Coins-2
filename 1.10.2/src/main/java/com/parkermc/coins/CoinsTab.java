package com.parkermc.coins;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CoinsTab extends CreativeTabs {
	
	public CoinsTab(String tabLevel) {
		super(tabLevel);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return CoinsItems.coin;
	}
}
