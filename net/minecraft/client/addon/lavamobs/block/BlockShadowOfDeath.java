package net.minecraft.client.addon.lavamobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavamobs.tile.TileBlockDeat;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockShadowOfDeath extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon icon;

    public BlockShadowOfDeath(int par1)
    {
        super(par1, Material.rock);
        this.setResistance(6000000.0F);
        this.setBlockUnbreakable();
        this.setStepSound(new StepSound("stone", 1.0F, 1.0F));
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("ShadowOfDeathBlock");
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
        super.blockIcon = Block.stone.getIcon(1, 0);
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new TileBlockDeat();
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        par1World.getBlockMetadata(par2, par3, par4);

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
        }
    }
}
