package palthor.professions.entitydata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import palthor.professions.reference;

// < ---- Change professionsData to Custom Data Name ( all 7 instances in file ) ---- > \\

public class professionsData  extends WorldSavedData { 			
	
	// < ---- CUSTOMIZABLE TAG NAMES ---- > > \\
	
	private static final String fileTag = reference.MOD_NAME + "_GlobalData";
	private static final String dataTag = "SavedGlobalData";
	
	// < ---------------------------------- > \\
	
	private static NBTTagCompound data = new NBTTagCompound();
	
	public professionsData(String name) { 					
		super(name); }

	// < ---- CREATE AN INSTANCE ---- > \\
	
	public static professionsData get(World world) { 
		MapStorage storage = world.getPerWorldStorage();
		professionsData instance = (professionsData) storage.loadData(professionsData.class, fileTag);

		  if (instance == null) {
		    instance = new professionsData(fileTag);
		    storage.setData(dataTag, instance); }
		  
		return instance; }
	
	// < ---- READ & WRITE from/to NBT ---- > \\
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) { data = nbt.getCompoundTag(dataTag); }

	@Override
	public void writeToNBT(NBTTagCompound nbt) { nbt.setTag(dataTag, data); }
	
	// < ---- GET SAVED DATA ---- > \\
	
	public static NBTTagCompound getData() { return data; }
	
} 