package palthor.professions.tasks;

import net.minecraft.entity.player.EntityPlayer;
import palthor.professions.tasks.newTasks;
import palthor.professions.recipes.unlock;

public class award {
	
	public static void Lumberjack(EntityPlayer player) {
		player.addStat(newTasks.Lumberjack, 1);
		unlock.test_item(); }
}