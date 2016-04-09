package palthor.professions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

// Mod Imports
import palthor.professions.reference;
import palthor.professions.main;

// Mod Blocks
import palthor.professions.blocks.item.TestBlock_item;

public class newBlocks {
	
	public static Block test_block;
	
	public static void create() {
		test_block = new TestBlock(Material.sand).setUnlocalizedName("test_block").setCreativeTab(main.tab); }
	
	public static void register() {
		GameRegistry.registerBlock(test_block, TestBlock_item.class, test_block.getUnlocalizedName().substring(5)); }
	
	public static void registerRenders() {
		registerRender(test_block); }
	
	public static void registerRender(Block block) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory")); }
}
