package palthor.professions.items;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import palthor.professions.main;
import palthor.professions.tasks.newTasks;

public class TestItem extends Item {
	
	public TestItem() {}
	
		@Override
		public ItemStack onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn) {
        
				playerIn.addChatMessage(new ChatComponentText("Right Clicking test_item..."));
				playerIn.openGui(main.instance, 0, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
				
				return stack; }
		
		@Override
		public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
			tooltip.add("Test Item"); }
}
