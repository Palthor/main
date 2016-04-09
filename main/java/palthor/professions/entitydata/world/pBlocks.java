package palthor.professions.entitydata.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

import palthor.professions.reference;
import palthor.professions.entitydata.professionsData;
import palthor.professions.entitydata.player.jobData;
import palthor.professions.entitydata.world.blockLog;
import palthor.professions.events.events;

public class pBlocks {
	
	// < ---- CONFIGURABLE VARIABLES ---- > \\
	
	final static int chunkSize = 16;
	
	// < ---- [END] CONFIGURABLE VARIABLES ---- > \\
	
	// < ---- DECLARED VARIABLES ---- > \\
	
	NBTTagCompound data = new NBTTagCompound();
	
	// < ---- [END] VARIABLE DECLARATIONS ---- > \\
	
	// < ---- [SET] - a player placed block into BlockLog ---- > \\
	
	public void setBlock (String name, BlockPos e, World w) {
		
		if (!w.isRemote) {
		
		NBTTagCompound chunk = new NBTTagCompound(), block = new NBTTagCompound(), owner = new NBTTagCompound();
		String chunkPOS, blockPOS;
				
		owner.setString("Owner", name);
		
		blockPOS = e.toString();
		block.setTag(blockPOS, owner);
		
		chunkPOS = "Chunk {" + String.valueOf(Math.floorDiv(e.getX(), chunkSize)) + " , " + 
				String.valueOf(Math.floorDiv(e.getY(), chunkSize)) + " , " + String.valueOf(Math.floorDiv(e.getZ(), chunkSize)) + "}";
		
		if (blockLog.getData().hasKey(chunkPOS)) data = blockLog.getData().getCompoundTag(chunkPOS);
		else data = new NBTTagCompound();
		
			data.setTag(blockPOS, owner);
			blockLog.setData(chunkPOS, data);

		// System.out.println("[BLOCKLOG][ADDING] " + blockPOS);
	
		}
	}
	
	// < ---- [END] setBlock() ---- > \\
	
	// < ---- [REMOVE] - a player created block from BlockLog ---- > \\
	
	public void remBlock(BlockPos e, World w) {
		
		if (!w.isRemote) {
		String chunkPOS, blockPOS;
		
		chunkPOS = "Chunk {" + String.valueOf(Math.floorDiv(e.getX(), chunkSize)) + " , " + 
				String.valueOf(Math.floorDiv(e.getY(), chunkSize)) + " , " + String.valueOf(Math.floorDiv(e.getZ(), chunkSize)) + "}";
		
		blockPOS = e.toString();
		
		if (blockLog.getData().getCompoundTag(chunkPOS).hasKey(blockPOS)) {
			
			data = blockLog.getData().getCompoundTag(chunkPOS); 
			data.removeTag(blockPOS);
			
			blockLog.setData(chunkPOS, data);
			
			System.out.println("[BLOCKLOG][REMOVING] " + blockPOS); }
		else System.out.println("[BLOCKLOG] Tried to remove a player placed block that wasn't saved.");	
	}
	}
	
	// < ---- [END] remBlock() ---- > \\

	// < ---- [GET] - the Owner of a block ---- > \\
	
	public String getOwner(BlockPos e) {
		
		String chunkPOS, blockPOS;
		
		chunkPOS = "Chunk {" + String.valueOf(Math.floorDiv(e.getX(), chunkSize)) + " , " + 
				String.valueOf(Math.floorDiv(e.getY(), chunkSize)) + " , " + String.valueOf(Math.floorDiv(e.getZ(), chunkSize)) + "}";
		
		blockPOS = e.toString();
		
		if (blockLog.getData().getCompoundTag(chunkPOS).getCompoundTag(blockPOS).hasKey("Owner"))
			return blockLog.getData().getCompoundTag(chunkPOS).getCompoundTag(blockPOS).getString("Owner");
		else	
			return "none";
	}
	
	// < ---- [END] getOwner() ---- > \\
}
	