package palthor.professions.events;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

// < ---- MOD IMPORTS ---- > \\

import palthor.professions.entitydata.professionsData;
import palthor.professions.entitydata.player.jobData;
import palthor.professions.entitydata.world.blockLog;
import palthor.professions.entitydata.world.pBlocks;
import palthor.professions.jobs.jobs;
import palthor.professions.jobs.lumberjack.lumberjack;

public class events {
	
	public static professionsData data; 
	private pBlocks pBlocks = new pBlocks();
	
	private lumberjack Lumberjack = new lumberjack();
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) { 
				
		// < ---- LOAD MOD DATA ---- > \\
				
		if (e.entity instanceof EntityPlayerMP) 
			if (!e.world.isRemote) {
			data = professionsData.get(e.world); }
		
		// < ---- [END] LOAD DATA ---- > \\
	}

	@SubscribeEvent
	public void EntityConstructing(EntityConstructing e) {}

	@SubscribeEvent
	public void blockPlace(PlaceEvent e) { 
		
		// < ---- BLOCKLOG ---- > \\
		if (e.player instanceof EntityPlayerMP) 
			if (!e.player.worldObj.isRemote) {
				pBlocks.setBlock(e.player.getDisplayNameString(), e.pos, e.world); }
		
		// < ---- [END] BLOCKLOG ENTRIES ---- > \\
	}

	@SubscribeEvent
	public void blockBreak(BreakEvent e) { 
		
		// < ---- BLOCKLOG ---- > \\
		if (e.getPlayer() instanceof EntityPlayerMP) 
		if (!e.getPlayer().worldObj.isRemote) {
		
		String owner = pBlocks.getOwner(e.pos);
		
		if (owner.equals(e.getPlayer().getDisplayNameString())) { pBlocks.remBlock(e.pos, e.world); }
		else if (owner.equals("none")) {
			
			// < ---- [JOBS] Give jobs experience based on block broken ---- > \\
			
				// < ---- [LUMBERJACK] ---- > \\
			
				System.out.println("< ---- [LUMBERJACK] ---- > ");
				
					Lumberjack.setPlayer(e.getPlayer());
					
				if (Lumberjack.cutCheck(e.state) == false) {
					 e.setCanceled(true); }
				
		
				// < ---- [END] LUMBERJACK ---- > \\
			
			// < ---- [END] Job Checks ---- > \\
		}
		else {
			
				         // < ---- [ERROR] Blocked Owned by Someone Else ---- > \\
			
			String message;
			message = EnumChatFormatting.RED + "[ERROR] " + EnumChatFormatting.WHITE + "Block owned by [" + 
					  EnumChatFormatting.RED + owner + EnumChatFormatting.WHITE + "]";
			
			e.getPlayer().addChatMessage(new ChatComponentText(message));
			
			e.setCanceled(true); }
		}
		           // < ------------------------------------------------------------ > \\	
		}
		
				
	@SubscribeEvent
	public void explosionEvent(ExplosionEvent.Start e) { e.setCanceled(true); }

	@SubscribeEvent
	public void NeighborNotifyEvent(NeighborNotifyEvent e) { e.setCanceled(true); }
		
}
