package palthor.professions.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import palthor.professions.items.newItems;
import palthor.professions.entitydata.player.recipes;

public class unlock {
	
	public static void test_item() {
		GameRegistry.addRecipe(new ItemStack(newItems.test_item), 
				new Object[]{"   ", " S ", "   ", 'S', Blocks.sand});
		recipes.saveRecipe("test_item"); }	
}
