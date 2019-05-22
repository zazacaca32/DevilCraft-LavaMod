package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.Tile.VenokTileBlock;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockVenok extends BlockContainer
{
    private ForgeDirection side;
    @SideOnly(Side.CLIENT)
    private Icon icon;

    public BlockVenok(int par1)
    {
        super(par1, Material.circuits);
        this.setLightValue(0.5F);
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
        this.updateLadderBounds(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = Block.blockSnow.getIcon(1, 0);
    }

    public void updateLadderBounds(int par1)
    {
        float f = 0.225F;

        if (par1 == 2)
        {
            this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        }

        if (par1 == 3)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        }

        if (par1 == 4)
        {
            this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (par1 == 5)
        {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        }
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int getRenderType()
    {
        return -1;
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        int var10001 = par2 - 1;
        ForgeDirection var10004 = this.side;
        boolean var10000;

        if (!par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.EAST))
        {
            var10001 = par2 + 1;
            var10004 = this.side;

            if (!par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.WEST))
            {
                int var10003 = par4 - 1;
                var10004 = this.side;

                if (!par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.SOUTH))
                {
                    var10003 = par4 + 1;
                    var10004 = this.side;

                    if (!par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.NORTH))
                    {
                        var10000 = false;
                        return var10000;
                    }
                }
            }
        }

        var10000 = true;
        return var10000;
    }

    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        int j1 = par9;
        int var10003;
        ForgeDirection var10004;

        if (par9 == 0 || par5 == 2)
        {
            var10003 = par4 + 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.NORTH))
            {
                j1 = 2;
            }
        }

        if (j1 == 0 || par5 == 3)
        {
            var10003 = par4 - 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.SOUTH))
            {
                j1 = 3;
            }
        }

        int var10001;

        if (j1 == 0 || par5 == 4)
        {
            var10001 = par2 + 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.WEST))
            {
                j1 = 4;
            }
        }

        if (j1 == 0 || par5 == 5)
        {
            var10001 = par2 - 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.EAST))
            {
                j1 = 5;
            }
        }

        return j1;
    }

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        boolean flag = false;
        int var10003;
        ForgeDirection var10004;

        if (i1 == 2)
        {
            var10003 = par4 + 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.NORTH))
            {
                flag = true;
            }
        }

        if (i1 == 3)
        {
            var10003 = par4 - 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(par2, par3, var10003, ForgeDirection.SOUTH))
            {
                flag = true;
            }
        }

        int var10001;

        if (i1 == 4)
        {
            var10001 = par2 + 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.WEST))
            {
                flag = true;
            }
        }

        if (i1 == 5)
        {
            var10001 = par2 - 1;
            var10004 = this.side;

            if (par1World.isBlockSolidOnSide(var10001, par3, par4, ForgeDirection.EAST))
            {
                flag = true;
            }
        }

        if (!flag)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, i1, 0);
            par1World.setBlockToAir(par2, par3, par4);
        }

        super.onNeighborBlockChange(par1World, par2, par3, par4, par5);
    }

    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new VenokTileBlock();
    }
}
