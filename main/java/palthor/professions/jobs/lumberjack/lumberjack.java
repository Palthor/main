package palthor.professions.jobs.lumberjack;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import palthor.professions.entitydata.professionsData;
import palthor.professions.entitydata.player.jobData;
import palthor.professions.events.events;
import palthor.professions.jobs.jobs;
import palthor.professions.tasks.newTasks;

public class lumberjack {
	
	// < ---- CONFIGURABLE VARIABLES ---- > \\
	
		static final String jobName = "Lumberjack";
	
		// < ---- Rank Experience Requirements ---- > \\
		
		static final  int adept =   1000,
				  novice =  3000,
				  veteran = 6000,
				  master = 10000;
		
		// < ---- Experience required to cut down a tree type ----> \\
		
		static final int  cutAcacia = 	7000,
				  cutBirch = 	1000, 
				  cutDarkOak = 	2000,
				  cutJungle = 	5000,
				  cutOak = 	   0,
				  cutSpruce = 	3000;
		
		// <---- Experience gained from cutting a log of a tree ----> \\
		
		static final int  xpAcacia = 	50,
				  xpBirch = 	30,
				  xpDarkOak = 	35,
				  xpJungle = 	45,
				  xpOak = 	25,
				  xpSpruce = 	40;
	
	// < ---- [END] CONFIGURABLE VARIABLES ---- > \\
		
	// < ---- DECLARED VARIABLES ---- > \\
		
		public EntityPlayer player;
		
		NBTTagCompound data, stats;
		
		int currentXP = 0, previousXP, missingXP;
		
	// < ---- [END] VARIABLE DECLARATIONS ---- > \\
		
	public lumberjack() {}
	
	public void setPlayer(EntityPlayer player) { this.player = player; }
		
	public void setXP(int addXP) {
		
		data =  new NBTTagCompound();
		stats = new NBTTagCompound();
		
		String message;
			
		previousXP = jobData.getData().getCompoundTag(jobName).getCompoundTag(player.getDisplayNameString()).getInteger("xp");
		currentXP =  jobData.getData().getCompoundTag(jobName).getCompoundTag(player.getDisplayNameString()).getInteger("xp");
		
		if (!player.worldObj.isRemote) {
			
			stats.setInteger("xp", currentXP + addXP);
		
			if (jobData.getData().hasKey(jobName)) data = jobData.getData().getCompoundTag(jobName); 
			else data = new NBTTagCompound();
		
			data.setTag(player.getDisplayNameString(), stats);
			jobData.setData(jobName, data);
		
			currentXP = currentXP + addXP;
		
			// < ---- [RESULTS] SENDS RESULTS TO PLAYER ---- > \\
		
				message = EnumChatFormatting.DARK_GREEN + "[LUMBERJACK] " + EnumChatFormatting.WHITE + "Gained " + 
					  addXP + EnumChatFormatting.DARK_GREEN + " Lumberjack " + EnumChatFormatting.WHITE + 
					  "exp. " + EnumChatFormatting.DARK_GREEN + "(Total: " + currentXP + ")";
				
				player.addChatMessage(new ChatComponentText(message));
				
				checkAwards();
				
			// < ---- [END] RESULTS ---- > \\
	}
	}
	
	// < ---- [SET] / [GET] Experience ---- > \\
	
	public EntityPlayer getPlayer() { return player; }
	
	public int getXP(EntityPlayer player) { 

		if 	(jobData.getData().getCompoundTag(jobName).getCompoundTag(player.getDisplayNameString()).hasKey("xp")) 
		return 	 jobData.getData().getCompoundTag(jobName).getCompoundTag(player.getDisplayNameString()).getInteger("xp");
		return 	 0; }
	
	// < ---- [END] Set / Get Experience ---- > \\
		
	// < ---- [CHECK] Check if a player has earned any bonus rewards. ---- > \\
	
	public void checkAwards() {
		
		int previousAwardLvl = 0, newAwardLvl, currentXP = 0, previousXP;
		
		previousXP = currentXP;
		currentXP = jobData.getData().getCompoundTag(jobName).getCompoundTag(player.getDisplayNameString()).getInteger("xp");
		
		
		if 	(currentXP > master - 1) 	newAwardLvl = 4;
		else if (currentXP > veteran - 1) 	newAwardLvl = 3;
		else if (currentXP > novice - 1) 	newAwardLvl = 2;
		else if (currentXP > adept - 1) 	newAwardLvl = 1;
		else 					newAwardLvl = 0;
			
		if 	(previousXP > master - 1) 	previousAwardLvl = 4;
		else if (previousXP > veteran - 1) 	previousAwardLvl = 3;
		else if (previousXP > novice -1) 	previousAwardLvl = 2;
		else if (previousXP > adept -1 ) 	previousAwardLvl = 1;
		else 					previousAwardLvl = 0;
			
		if (previousAwardLvl == 0 || newAwardLvl > previousAwardLvl) 
			giveAward(newAwardLvl);
				
	}

	// < ---- [END] Bonus Reward Check ---- > \\
	
	// < ---- [AWARD] Reward player based on award level ---- > \\
	
	public void giveAward (int AwardLvl) {
		
		switch (AwardLvl) {
		
		case 4: player.addStat(newTasks.MasterLumberjack, 1);
		break;
		
		case 3: player.addStat(newTasks.VeteranLumberjack, 1);
		break;
		
		case 2: player.addStat(newTasks.NoviceLumberjack, 1);
		break;
		
		case 1: player.addStat(newTasks.AdeptLumberjack, 1);
		break;
		
		default: player.addStat(newTasks.Lumberjack, 1);
		break; }
	}
	
	// < ---- [END] Award ---- > \\
	
	// < ---- [CHECK] Determines if player has enough experience to cut the log ---- > \\
	
	public boolean cutCheck(IBlockState state) {
		
		String message, logType = new String();
		
		currentXP = jobData.getData().getCompoundTag(jobName).getCompoundTag(player.getDisplayNameString()).getInteger("xp");
		
		if (state.getBlock() == Blocks.log) logType = state.getValue(BlockNewLog.VARIANT).toString();
		else if (state.getBlock() == Blocks.log2) logType = state.getValue(BlockNewLog.VARIANT).toString();
				
			switch (logType) {
			
			case "oak":
				setXP(xpOak);
				return true;
				
			case "birch":
		
				if (currentXP > cutBirch - 1) {
						setXP(xpBirch);
						return true; }
					
				missingXP = cutBirch - currentXP;
				break;
		
			case "dark_oak":
				
				if (currentXP > cutDarkOak - 1) {
						setXP(xpDarkOak);
						return true; }
				
				missingXP = cutDarkOak - currentXP;
				break;
				
			case "spruce":
				
				if (currentXP > cutSpruce - 1) {
						setXP(xpSpruce);
						return true; }
				
				missingXP = cutSpruce - currentXP;
				break;

			case "jungle":
				
				if (currentXP > cutJungle - 1) {
						setXP(xpJungle);
						return true; }
				
				missingXP = cutJungle - currentXP;
				break;

			case "acacia":
				
				if (currentXP > cutAcacia - 1) {
						setXP(xpAcacia);
						return true; }
				
				missingXP = cutAcacia - currentXP;
				break;

			default:
				return true; }
			
			
			// < ---- [RESULTS] SENDS RESULTS TO PLAYER ---- > \\
			
				message = EnumChatFormatting.DARK_GREEN + "[LUMBERJACK] " + EnumChatFormatting.WHITE + "Need " + EnumChatFormatting.DARK_RED +
					missingXP + EnumChatFormatting.WHITE + " more exp to cut " + EnumChatFormatting.DARK_GREEN + "[" + logType + "]";
			
				player.addChatMessage(new ChatComponentText(message));	
			
			// < ---- [END] RESULTS ---- > \\
		
			return false;
	}
	
	// < ---- [END] cutCheck() ---- > \\
	
}
