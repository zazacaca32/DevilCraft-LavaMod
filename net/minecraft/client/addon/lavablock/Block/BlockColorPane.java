package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.ModBlocks;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockColorPane extends Block
{
    private Random random = new Random();
    private final String ico;
    private final String bico;
    @SideOnly(Side.CLIENT)
    public Icon icon;

    public BlockColorPane(int par1, String par2, String par3)
    {
        super(par1, Material.glass);
        this.ico = par2;
        this.bico = par3;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister ir)
    {
        this.icon = ir.registerIcon(this.ico);
        super.blockIcon = ir.registerIcon(this.bico);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return ModBlocks.render_id;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    protected boolean canSilkHarvest()
    {
        return true;
    }

    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        boolean flag = this.canPaneConnectTo(par1World, par2, par3, par4, ForgeDirection.NORTH);
        boolean flag1 = this.canPaneConnectTo(par1World, par2, par3, par4, ForgeDirection.SOUTH);
        boolean flag2 = this.canPaneConnectTo(par1World, par2, par3, par4, ForgeDirection.WEST);
        boolean flag3 = this.canPaneConnectTo(par1World, par2, par3, par4, ForgeDirection.EAST);

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2 && !flag3)
            {
                this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
            else if (!flag2 && flag3)
            {
                this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }

        if (flag && flag1 || !flag2 && !flag3 && !flag && !flag1)
        {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
        else if (flag && !flag1)
        {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
        else if (!flag && flag1)
        {
            this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
        }
    }

    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean flag = this.canPaneConnectTo(par1IBlockAccess, par2, par3, par4, ForgeDirection.NORTH);
        boolean flag1 = this.canPaneConnectTo(par1IBlockAccess, par2, par3, par4, ForgeDirection.SOUTH);
        boolean flag2 = this.canPaneConnectTo(par1IBlockAccess, par2, par3, par4, ForgeDirection.WEST);
        boolean flag3 = this.canPaneConnectTo(par1IBlockAccess, par2, par3, par4, ForgeDirection.EAST);

        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
        {
            if (flag2 && !flag3)
            {
                f = 0.0F;
            }
            else if (!flag2 && flag3)
            {
                f1 = 1.0F;
            }
        }
        else
        {
            f = 0.0F;
            f1 = 1.0F;
        }

        if (flag && flag1 || !flag2 && !flag3 && !flag && !flag1)
        {
            f2 = 0.0F;
            f3 = 1.0F;
        }
        else if (flag && !flag1)
        {
            f2 = 0.0F;
        }
        else if (!flag && flag1)
        {
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public boolean canPaneConnectTo(IBlockAccess access, int x, int y, int z, ForgeDirection dir)
    {
        return this.canThisPaneConnectToThisBlockID(access.getBlockId(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) || access.isBlockSolidOnSide(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, dir.getOpposite(), false);
    }

    public final boolean canThisPaneConnectToThisBlockID(int par1)
    {
        return Block.opaqueCubeLookup[par1] || par1 == super.blockID || par1 == Block.glass.blockID;
    }

    @SideOnly(Side.CLIENT)
    public Icon getSideTextureIndex()
    {
        return this.icon;
    }
}
