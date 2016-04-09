package palthor.professions.gui;

import net.minecraft.client.gui.GuiScreen;

public class newGUI extends GuiScreen{
	
	public newGUI() {}
	
	public void intiGui() {}
	
	public boolean doesGuiPauseGame() {
		return false; }
	
	public void drawScreen(int i, int j, float f) {
		
		drawDefaultBackground();
		super.drawScreen(i, j, f); }
}
