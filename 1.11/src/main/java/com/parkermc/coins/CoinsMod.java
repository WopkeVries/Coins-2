package com.parkermc.coins;

import com.parkermc.coins.network.MessageConfig;
import com.parkermc.coins.proxy.ProxyCommon;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = CoinsMod.MODID, name = CoinsMod.MODNAME, version = CoinsMod.VERSION, guiFactory = "com.parkermc.coins.gui.GuiModConfigFactory")
public class CoinsMod{
    public static final String MODID = "coins";
    public static final String MODNAME = "Coins Mod";
    public static final String VERSION = "1.0.3";
    
    @SidedProxy(clientSide = "com.parkermc.coins.proxy.ProxyClient", serverSide = "com.parkermc.coins.proxy.ProxyServer", modId = MODID)
    public static ProxyCommon proxy;
    
    public static SimpleNetworkWrapper network;
    public static CoinsTab tab = new CoinsTab(MODID);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	proxy.preInit(event);
    	network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    	network.registerMessage(MessageConfig.Handler.class, MessageConfig.class, 0, Side.CLIENT);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	proxy.init(event);      
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	proxy.postInit(event);
    }
}

