package palthor.professions.gui;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import palthor.professions.items.newItems;

public class tab extends CreativeTabs {

	public tab(String label) {
		super(label);
		this.setBackgroundImageName("tab.png");
	}

	@Override
	public Item getTabIconItem() {
		return newItems.test_item;
	}
	
}
