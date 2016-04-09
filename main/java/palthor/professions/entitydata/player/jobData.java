package palthor.professions.entitydata.player;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import palthor.professions.reference;
import palthor.professions.entitydata.professionsData;
import palthor.professions.events.events;

public class jobData {
	
	private static final String dataTag = "Jobs";
	
	public static NBTTagCompound data = new NBTTagCompound();
	
	public jobData() {}
	
	public static NBTTagCompound getData() { return professionsData.getData().getCompoundTag(dataTag); }
	
	public static void setData(String job, NBTTagCompound nbt) { 
		
		if (professionsData.getData().getCompoundTag(dataTag).hasKey(job)) 
			data = professionsData.getData().getCompoundTag(dataTag);
		
		data.setTag(job, nbt);
		events.data.getData().setTag(dataTag, data);
		events.data.markDirty();
		
		System.out.println("[SAVING JOB DATA]"); }
}
	
