package com.parkermc.coins.items;

import java.util.List;

import com.parkermc.coins.CoinsConfig;
import com.parkermc.coins.CoinsMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ItemCoin extends Item {
	public static final String unlocalizedName = "coin";
	private final int coins = 5;
	
	public ItemCoin() {
		super();
		this.setCreativeTab(CoinsMod.tab);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.updateConfigData();
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName(stack) + "_" + stack.getMetadata();
	}
	
	public void updateConfigData() {
		this.setMaxStackSize(CoinsConfig.maxStackSize);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for(int i = 0; i < coins; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn){
		if(!CoinsConfig.hoverText.isEmpty()) {
			tooltip.add(CoinsConfig.hoverText);
		}
    }
	
	@SideOnly(Side.CLIENT)
	public void initModel() {
		for(int i = 0; i < coins; i++) {
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(this.getRegistryName() + "_" + String.valueOf(i), "inventory"));
		}
	}
}
