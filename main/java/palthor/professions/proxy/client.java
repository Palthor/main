package palthor.professions.proxy;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import palthor.professions.blocks.newBlocks;
import palthor.professions.items.newItems;

public class client extends common{
	
	private final Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void registerRenders() {
		newBlocks.registerRenders();
		newItems.registerRenders(); }
}

/*
	// In your client proxy:
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
	 // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
	 // your packets will not work because you will be getting a client
	 // player even when you are on the server! Sounds absurd, but it's true.

	 // Solution is to double-check side before returning the player:
	 return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
	
	public IThreadListener getThreadFromContext(MessageContext ctx) {
		return (ctx.side.isClient() ? mc : super.getThreadFromContext(ctx)); }
*/
