package palthor.professions.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import palthor.professions.blocks.newBlocks;
import palthor.professions.items.newItems;

public class newRecipes {
	
	public static void register() {
		GameRegistry.addRecipe(new ItemStack(newBlocks.test_block, 8), 
				new Object[]{"SSS", "SSS", "SSS", 'S', Blocks.sand}); }
}
