package palthor.professions.blocks.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import palthor.professions.tasks.award;

public class TestBlock_item extends ItemBlock{
	
    public TestBlock_item(Block block) {
		super(block); }

	public void onCreated(ItemStack stack, World worldIn, EntityPlayer player) {}
	
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean advanced) {
		tooltip.add("Test Block"); }
}
