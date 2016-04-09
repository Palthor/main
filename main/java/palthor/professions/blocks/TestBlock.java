package palthor.professions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;

public class TestBlock extends Block {

	public TestBlock(Material materialIn) {
		super(materialIn);
		this.setHardness(0.5f);
		this.setStepSound(soundTypeSand); }

	@Override
	public boolean isOpaqueCube() {
		return false; }
	
	@Override
	public boolean isFullCube() {
		return false; }
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT; }
}
