package pixelmon.blocks;

import java.util.Random;

import pixelmon.comm.ChatHandler;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class BlockTradeMachine extends BlockContainer {

	public BlockTradeMachine(int id) {
		super(id, Material.rock);
		setHardness(3.5f);
		setStepSound(Block.soundStoneFootstep);
		setRequiresSelfNotify();
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return -1;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	/**
	 * Called upon block activation (left or right click on the block.). The
	 * three integers represent x,y,z of the block.
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (!par1World.isRemote) {
			TileEntityTradeMachine te = (TileEntityTradeMachine) par1World.getBlockTileEntity(par2, par3, par4);
			if (te.playerCount < 2) {
				te.registerPlayer(player);
			} else {
				ChatHandler.sendChat(player, "Healer is busy!");
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityTradeMachine();
	}
}
