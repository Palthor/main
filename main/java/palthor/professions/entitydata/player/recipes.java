package palthor.professions.entitydata.player;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;
import palthor.professions.recipes.unlock;

public class recipes implements IExtendedEntityProperties {

	
	private static final String identifier = "Recipes";
	private final EntityPlayer player;
	
	// Custom locked recipe list property...
	
	public static List recipe = new ArrayList<String>();
	
	@Override
	public void init(Entity entity, World world) {}
	
	public recipes(EntityPlayer player) {
		this.player = player; }	
	
	public static void register(EntityPlayer player) {
		player.registerExtendedProperties(identifier, new recipes(player)); }
	
	public static recipes get(EntityPlayer player) {
		return (recipes) player.getExtendedProperties(identifier); }
	
	@Override
	public void saveNBTData(NBTTagCompound nbt) {
		NBTTagCompound tag = new NBTTagCompound();
		
		NBTTagList tagRecipe = new NBTTagList();
		
		for (int i = 0; i < recipe.size(); i++) {
			
			String s = recipe.get(i).toString();
		
			if (s != null) {
				
				tag.setString("recipe" + i, s);
				tagRecipe.appendTag(tag); }
		}
		nbt.setTag(identifier, tagRecipe);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
		
		NBTTagList tag = nbt.getTagList(identifier, Constants.NBT.TAG_COMPOUND);
		
		for (int i = 0; i < tag.tagCount(); i++) {
			NBTTagCompound tagRecipe = tag.getCompoundTagAt(i);
			String s = tagRecipe.getString("recipe" + i);
			recipe.add(i, s); }	
	}
	
	// Save unlocked recipe...
	
	public static void saveRecipe(String name) {
		if (recipe.contains(name) == false)
			recipe.add(name);
	}
	
	// Unlocks recipes for player based on previous unlocks...
	
	public void syncRecipes() {
		
		if (recipe.size() > -1) {
			
			for (int i = 0; i < recipe.size(); i++) {
				
				if (recipe.get(i).toString() == "Lumberjack") {
					unlock.test_item(); }
			}
		}
	}
}
