package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.Tile.PrizentTileBlock;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockPrizent extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon icon;

    public BlockPrizent(int id)
    {
        super(id, Material.pumpkin);
    }

    public int getRenderType()
    {
        return -1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = Block.blockSnow.getIcon(1, 0);
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new PrizentTileBlock();
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return true;
    }
}
