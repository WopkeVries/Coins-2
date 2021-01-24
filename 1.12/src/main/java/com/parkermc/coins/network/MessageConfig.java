package com.parkermc.coins.network;

import com.parkermc.coins.CoinsConfig;
import com.parkermc.coins.proxy.ProxyClient;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Property.Type;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageConfig implements IMessage {

	Configuration config;
	
	public MessageConfig(){
	}
	
	public MessageConfig(Configuration config){
		this.config = config;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.config = new Configuration();
		int categoryCount = buf.readInt();
		for(int catNum = 0; catNum < categoryCount; catNum++) {
			String categoryName = ByteBufUtils.readUTF8String(buf);
			ConfigCategory category = this.config.getCategory(categoryName);
			int variableCount = buf.readInt();
			for(int varNum = 0; varNum < variableCount; varNum++) {
				String varName = ByteBufUtils.readUTF8String(buf);
				switch(buf.readByte()) {
				case 0:
					category.put(varName, new Property(varName, ByteBufUtils.readUTF8String(buf), Type.STRING));
					break;
				case 1:
					category.put(varName, new Property(varName, ByteBufUtils.readUTF8String(buf), Type.INTEGER));
					break;
				case 2:
					category.put(varName, new Property(varName, ByteBufUtils.readUTF8String(buf), Type.BOOLEAN));
					break;
				case 3:
					category.put(varName, new Property(varName, ByteBufUtils.readUTF8String(buf), Type.DOUBLE));
					break;
				}
			}
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(config.getCategoryNames().size());
		for(String categoryName : config.getCategoryNames()) {
			ConfigCategory category = config.getCategory(categoryName);
			ByteBufUtils.writeUTF8String(buf, category.getName());
			buf.writeInt(category.size());
			for(String variableName : category.keySet()) {
				ByteBufUtils.writeUTF8String(buf, category.get(variableName).getName());
				switch(category.get(variableName).getType()) {
				case STRING:
					buf.writeByte(0);
					ByteBufUtils.writeUTF8String(buf, category.get(variableName).getString());
					break;
				case INTEGER:
					buf.writeByte(1);
					ByteBufUtils.writeUTF8String(buf, String.valueOf(category.get(variableName).getInt()));
					break;
				case BOOLEAN:
					buf.writeByte(2);
					ByteBufUtils.writeUTF8String(buf, String.valueOf(category.get(variableName).getBoolean()));
					break;
				case DOUBLE:
					buf.writeByte(3);
					ByteBufUtils.writeUTF8String(buf, String.valueOf(category.get(variableName).getDouble()));
					break;
				default:
					buf.writeByte(-1);
					buf.writeByte(-1);
					break;
				}
			}
		}	
	}
	
	public static class Handler implements IMessageHandler<MessageConfig, IMessage>{

		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(MessageConfig message, MessageContext ctx) {
			ProxyClient.serverConfig = message.config;
			CoinsConfig.readConfig();
			return null;
		}
		
	}
}
