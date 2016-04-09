package palthor.professions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import palthor.professions.blocks.newBlocks;
import palthor.professions.events.events;
import palthor.professions.gui.tab;
import palthor.professions.items.newItems;
import palthor.professions.recipes.newRecipes;
import palthor.professions.tasks.newTasks;
import palthor.professions.proxy.common;

@Mod(modid = reference.MOD_ID, name = reference.MOD_NAME, version = reference.VERSION)
public class main {
	
	public static SimpleNetworkWrapper network;
	
	@SidedProxy (clientSide = reference.CLIENT_PROXY_CLASS, serverSide = reference.SERVER_PROXY_CLASS)
	public static common proxy;
	
	public static final tab tab = new tab("tab");
	
	@Instance
	public static main instance = new main();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		newBlocks.create();
		newBlocks.register();
		
		newItems.create();
		newItems.register();
		
		newRecipes.register();
		
		newTasks.register();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		proxy.registerRenders(); }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
	
}