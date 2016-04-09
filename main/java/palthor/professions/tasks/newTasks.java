package palthor.professions.tasks;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;
import palthor.professions.blocks.newBlocks;

public class newTasks {
	
	// ---- [DECLARE] New Achievements ---- > \\
	
	public static Achievement Lumberjack,
							  AdeptLumberjack,
							  NoviceLumberjack,
							  VeteranLumberjack,
							  MasterLumberjack;
	
	// ---- [DECLARE] New Achievement Pages ---- > \\
	
	public static AchievementPage Classes;
	
	public static void register() {
		
		// < ---- [REGISTER] Achievements ---- > \\ 
		
			// < ---- [LUMBERJACK] ---- > \\
		
			Lumberjack = new Achievement
				("Lumberjack", "Lumberjack", 0, 0, Blocks.log, null).func_180788_c();
		
			AdeptLumberjack = new Achievement
				("AdeptLumberjack", "AdeptLumberjack", 1, 0, Blocks.log, Lumberjack).func_180788_c();
		
			NoviceLumberjack = new Achievement
				("NoviceLumberjack", "NoviceLumberjack", 2, 0, Blocks.log2, AdeptLumberjack).func_180788_c();
		
			VeteranLumberjack = new Achievement
				("VeteranLumberjack", "VeteranLumberjack", 3, 0, Blocks.log2, NoviceLumberjack).func_180788_c();
		
			MasterLumberjack = new Achievement
				("MasterLumberjack", "MasterLumberjack", 4, 0, Blocks.log, VeteranLumberjack).func_180788_c();
		
		// < ---- [NEW] Achievement Pages ---- > \\
		
			Classes = new AchievementPage ( 
			"Classes", Lumberjack, 
					   AdeptLumberjack,
					   NoviceLumberjack,
					   VeteranLumberjack,
					   MasterLumberjack );
		
		// < ---- [REGISTER] Achievement Pages ---- > \\
		
		AchievementPage.registerAchievementPage(Classes);
	}
	
}
