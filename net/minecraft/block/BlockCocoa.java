package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BlockDirectional
{
    public static final String[] cocoaIcons = new String[] {"cocoa_0", "cocoa_1", "cocoa_2"};
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;

    public BlockCocoa(int par1)
    {
        super(par1, Material.plants);
        this.setTickRandomly(true);
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return this.iconArray[2];
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
        else if (par1World.rand.nextInt(5) == 0)
        {
            int l = par1World.getBlockMetadata(par2, par3, par4);
            int i1 = func_72219_c(l);

            if (i1 < 2)
            {
                ++i1;
                par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 << 2 | getDirection(l), 2);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon func_94468_i_(int par1)
    {
        if (par1 < 0 || par1 >= this.iconArray.length)
        {
            par1 = this.iconArray.length - 1;
        }

        return this.iconArray[par1];
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int l = getDirection(par1World.getBlockMetadata(par2, par3, par4));
        par2 += Direction.offsetX[l];
        par4 += Direction.offsetZ[l];
        int i1 = par1World.getBlockId(par2, par3, par4);
        return i1 == Block.wood.blockID && BlockLog.limitToValidMetadata(par1World.getBlockMetadata(par2, par3, par4)) == 3;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 28;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        int i1 = getDirection(l);
        int j1 = func_72219_c(l);
        int k1 = 4 + j1 * 2;
        int l1 = 5 + j1 * 2;
        float f = (float)k1 / 2.0F;

        switch (i1)
        {
            case 0:
                this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)l1) / 16.0F, (15.0F - (float)k1) / 16.0F, (8.0F + f) / 16.0F, 0.75F, 0.9375F);
                break;

            case 1:
                this.setBlockBounds(0.0625F, (12.0F - (float)l1) / 16.0F, (8.0F - f) / 16.0F, (1.0F + (float)k1) / 16.0F, 0.75F, (8.0F + f) / 16.0F);
                break;

            case 2:
                this.setBlockBounds((8.0F - f) / 16.0F, (12.0F - (float)l1) / 16.0F, 0.0625F, (8.0F + f) / 16.0F, 0.75F, (1.0F + (float)k1) / 16.0F);
                break;

            case 3:
                this.setBlockBounds((15.0F - (float)k1) / 16.0F, (12.0F - (float)l1) / 16.0F, (8.0F - f) / 16.0F, 0.9375F, 0.75F, (8.0F + f) / 16.0F);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = ((MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 0) % 4;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        if (par5 == 1 || par5 == 0)
        {
            par5 = 2;
        }

        return Direction.rotateOpposite[Direction.facingToDirection[par5]];
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!this.canBlockStay(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }

    public static int func_72219_c(int par0)
    {
        return (par0 & 12) >> 2;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> dropped = super.getBlockDropped(world, x, y, z, metadata, fortune);
        int j1 = func_72219_c(metadata);
        byte b0 = 1;

        if (j1 >= 2)
        {
            b0 = 3;
        }

        for (int k1 = 0; k1 < b0; ++k1)
        {
            dropped.add(new ItemStack(Item.dyePowder, 1, 3));
        }

        return dropped;
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Item.dyePowder.itemID;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return 3;
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[cocoaIcons.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = par1IconRegister.registerIcon(cocoaIcons[i]);
        }
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }
}
