package palthor.professions.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import palthor.professions.main;
import palthor.professions.events.events;
import palthor.professions.gui.handler;

public class common {
	
	public void init() {
		
		// Event Handler
		
		events handler = new events();
		MinecraftForge.EVENT_BUS.register(handler);
		FMLCommonHandler.instance().bus().register(handler);
		
		// GUI Handler
		
		NetworkRegistry.INSTANCE.registerGuiHandler(main.instance, new handler());
		
		// Packet Handler
	}
		
	public void registerRenders() {}

}
	
/*
	
	// In your server proxy (mine is named CommonProxy):
	// Returns a side-appropriate EntityPlayer for use during message handling
		 
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		 return ctx.getServerHandler().playerEntity;
	}
	
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity.getServerForPlayer();
	}
*/

