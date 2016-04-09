package palthor.professions.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import palthor.professions.reference;
import palthor.professions.main;

public class newItems {
	
	public static Item test_item;
	
	public static void create() { 
		test_item = new TestItem().setUnlocalizedName("test_item").setCreativeTab(main.tab); }
	
	public static void register() {
		GameRegistry.registerItem(test_item, test_item.getUnlocalizedName().substring(5)); }
	
	public static void registerRenders() {
		registerRender(test_item); }
	
	public static void registerRender(Item item) {
		test_item.isFull3D();
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory")); }
}
