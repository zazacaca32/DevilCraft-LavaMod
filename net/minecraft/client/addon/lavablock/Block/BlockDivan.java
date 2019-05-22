package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.Tile.DivanTileBlock;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDivan extends BlockContainer
{
    public BlockDivan(int id)
    {
        super(id, Material.ground);
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
        super.blockIcon = par1IconRegister.registerIcon("lavablock:divan_item");
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            par5EntityPlayer.mountEntity((Entity)null);
            return false;
        }
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new DivanTileBlock();
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int id1 = par1World.getBlockId(par2 + 1, par3, par4);
        int id2 = par1World.getBlockId(par2, par3, par4 + 1);
        int id3 = par1World.getBlockId(par2 - 1, par3, par4);
        int id4 = par1World.getBlockId(par2, par3, par4 - 1);
        byte met = 0;
        int met1 = par1World.getBlockMetadata(par2 + 1, par3, par4);
        int met2 = par1World.getBlockMetadata(par2, par3, par4 + 1);
        int met3 = par1World.getBlockMetadata(par2 - 1, par3, par4);
        int met4 = par1World.getBlockMetadata(par2, par3, par4 - 1);

        switch (l)
        {
            case 0:
                if (id2 == super.blockID)
                {
                    if (met2 == 1)
                    {
                        met = 9;
                    }

                    if (met2 == 3)
                    {
                        met = 7;
                    }
                }

                break;

            case 1:
                met = 1;

                if (id3 == super.blockID)
                {
                    if (met3 == 2)
                    {
                        met = 10;
                    }

                    if (met3 == 0)
                    {
                        met = 4;
                    }
                }

                break;

            case 2:
                met = 2;

                if (id4 == super.blockID)
                {
                    if (met4 == 1)
                    {
                        met = 5;
                    }

                    if (met4 == 3)
                    {
                        met = 11;
                    }
                }

                break;

            case 3:
                met = 3;

                if (id1 == super.blockID)
                {
                    if (met1 == 2)
                    {
                        met = 6;
                    }

                    if (met1 == 0)
                    {
                        met = 8;
                    }
                }
        }

        if (met < 4)
        {
            if (id4 == super.blockID)
            {
                if (id1 == super.blockID)
                {
                    met = 12;
                }

                if (id3 == super.blockID)
                {
                    met = 15;
                }
            }

            if (id2 == super.blockID)
            {
                if (id3 == super.blockID)
                {
                    met = 14;
                }

                if (id1 == super.blockID)
                {
                    met = 13;
                }
            }
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, met, 2);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
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
            case 4:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.3F, 1.0F, 1.0F);
                break;

            case 5:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.3F);
                break;

            case 6:
                this.setBlockBounds(1.0F, 0.0F, 0.0F, 0.7F, 1.0F, 1.0F);
                break;

            case 7:
                this.setBlockBounds(0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.7F);
                break;

            case 8:
                this.setBlockBounds(1.0F, 0.0F, 0.0F, 0.7F, 1.0F, 1.0F);
                break;

            case 9:
                this.setBlockBounds(0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.7F);
                break;

            case 10:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.3F, 1.0F, 1.0F);
                break;

            case 11:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.3F);
                break;

            default:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.4F, 1.0F);
        }
    }
}
