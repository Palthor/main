package palthor.professions.entitydata.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import palthor.professions.reference;
import palthor.professions.entitydata.professionsData;
import palthor.professions.events.events;

public class blockLog {
	
	private static final String dataTag = "BlockLog";
	
	private static NBTTagCompound data = new NBTTagCompound();
	
	public blockLog() {}
	
	public static NBTTagCompound getData() { return professionsData.getData().getCompoundTag(dataTag); }
	
	public static void setData(String chunkPOS, NBTTagCompound nbt) { 
		
		if (professionsData.getData().hasKey(dataTag)) 
			data = professionsData.getData().getCompoundTag(dataTag);
		
		data.setTag(chunkPOS, nbt);
		events.data.getData().setTag(dataTag, data);
		events.data.markDirty();
		
		System.out.println("[SAVING BLOCK LOG DATA]"); }
}
