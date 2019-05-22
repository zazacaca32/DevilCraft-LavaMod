package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.Tile.ColumnTileBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColumn extends BlockContainer
{
    public BlockColumn(int par1)
    {
        super(par1, Material.rock);
        this.setHardness(0.8F);
        this.setResistance(7.0F);
        this.setStepSound(new StepSound("stone", 1.0F, 1.0F));
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return -1;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int damageDropped(int par1)
    {
        return par1;
    }

    public boolean canPaneConnectToUp(World world, int x, int y, int z)
    {
        return world.getBlockId(x, y + 1, z) == super.blockID;
    }

    public boolean canPaneConnectToDown(World world, int x, int y, int z)
    {
        return world.getBlockId(x, y - 1, z) == super.blockID;
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new ColumnTileBlock();
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int meta = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        switch (meta)
        {
            case 0:
                this.setBlockBounds(0.19F, 0.0F, 0.19F, 0.81F, 1.0F, 0.81F);
                break;

            case 1:
                this.setBlockBounds(0.06F, 0.0F, 0.06F, 0.94F, 1.0F, 0.94F);
                break;

            default:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
