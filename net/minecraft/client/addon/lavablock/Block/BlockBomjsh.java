package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.Tile.BomjshTileBlock;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBomjsh extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon icon;

    public BlockBomjsh(int id)
    {
        super(id, Material.cloth);
        this.setLightValue(0.5F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3F, 1.0F);
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
        super.blockIcon = Block.cloth.getIcon(1, 7);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        byte var5 = 1;
        float var6 = 0.2F;
        return AxisAlignedBB.getAABBPool().getAABB((double)par2 + super.minX, (double)par3 + super.minY, (double)par4 + super.minZ, (double)par2 + super.maxX, (double)((float)par3 + (float)var5 * var6), (double)par4 + super.maxZ);
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new BomjshTileBlock();
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
