package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRedstoneLogic extends BlockDirectional
{
    /** Tells whether the repeater is powered or not */
    protected final boolean isRepeaterPowered;

    protected BlockRedstoneLogic(int par1, boolean par2)
    {
        super(par1, Material.circuits);
        this.isRepeaterPowered = par2;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) ? false : super.canPlaceBlockAt(par1World, par2, par3, par4);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return !par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4) ? false : super.canBlockStay(par1World, par2, par3, par4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);

        if (!this.func_94476_e(par1World, par2, par3, par4, l))
        {
            boolean flag = this.func_94478_d(par1World, par2, par3, par4, l);

            if (this.isRepeaterPowered && !flag)
            {
                par1World.setBlock(par2, par3, par4, this.func_94484_i().blockID, l, 2);
            }
            else if (!this.isRepeaterPowered)
            {
                par1World.setBlock(par2, par3, par4, this.func_94485_e().blockID, l, 2);

                if (!flag)
                {
                    par1World.func_82740_a(par2, par3, par4, this.func_94485_e().blockID, this.func_94486_g(l), -1);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 0 ? (this.isRepeaterPowered ? Block.torchRedstoneActive.getBlockTextureFromSide(par1) : Block.torchRedstoneIdle.getBlockTextureFromSide(par1)) : (par1 == 1 ? this.blockIcon : Block.stoneDoubleSlab.getBlockTextureFromSide(1));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.isRepeaterPowered ? "repeater_lit" : "repeater");
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 != 0 && par5 != 1;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 36;
    }

    protected boolean func_96470_c(int par1)
    {
        return this.isRepeaterPowered;
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);

        if (!this.func_96470_c(i1))
        {
            return 0;
        }
        else
        {
            int j1 = getDirection(i1);
            return j1 == 0 && par5 == 3 ? this.func_94480_d(par1IBlockAccess, par2, par3, par4, i1) : (j1 == 1 && par5 == 4 ? this.func_94480_d(par1IBlockAccess, par2, par3, par4, i1) : (j1 == 2 && par5 == 2 ? this.func_94480_d(par1IBlockAccess, par2, par3, par4, i1) : (j1 == 3 && par5 == 5 ? this.func_94480_d(par1IBlockAccess, par2, par3, par4, i1) : 0)));
        }
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
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
        }
        else
        {
            this.func_94479_f(par1World, par2, par3, par4, par5);
        }
    }

    protected void func_94479_f(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);

        if (!this.func_94476_e(par1World, par2, par3, par4, i1))
        {
            boolean flag = this.func_94478_d(par1World, par2, par3, par4, i1);

            if ((this.isRepeaterPowered && !flag || !this.isRepeaterPowered && flag) && !par1World.isBlockTickScheduled(par2, par3, par4, this.blockID))
            {
                byte b0 = -1;

                if (this.func_83011_d(par1World, par2, par3, par4, i1))
                {
                    b0 = -3;
                }
                else if (this.isRepeaterPowered)
                {
                    b0 = -2;
                }

                par1World.func_82740_a(par2, par3, par4, this.blockID, this.func_94481_j_(i1), b0);
            }
        }
    }

    public boolean func_94476_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return false;
    }

    protected boolean func_94478_d(World par1World, int par2, int par3, int par4, int par5)
    {
        return this.getInputStrength(par1World, par2, par3, par4, par5) > 0;
    }

    /**
     * Returns the signal strength at one input of the block. Args: world, X, Y, Z, side
     */
    protected int getInputStrength(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = getDirection(par5);
        int j1 = par2 + Direction.offsetX[i1];
        int k1 = par4 + Direction.offsetZ[i1];
        int l1 = par1World.getIndirectPowerLevelTo(j1, par3, k1, Direction.directionToFacing[i1]);
        return l1 >= 15 ? l1 : Math.max(l1, par1World.getBlockId(j1, par3, k1) == Block.redstoneWire.blockID ? par1World.getBlockMetadata(j1, par3, k1) : 0);
    }

    protected int func_94482_f(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = getDirection(par5);

        switch (i1)
        {
            case 0:
            case 2:
                return Math.max(this.func_94488_g(par1IBlockAccess, par2 - 1, par3, par4, 4), this.func_94488_g(par1IBlockAccess, par2 + 1, par3, par4, 5));

            case 1:
            case 3:
                return Math.max(this.func_94488_g(par1IBlockAccess, par2, par3, par4 + 1, 3), this.func_94488_g(par1IBlockAccess, par2, par3, par4 - 1, 2));

            default:
                return 0;
        }
    }

    protected int func_94488_g(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int i1 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return this.func_94477_d(i1) ? (i1 == Block.redstoneWire.blockID ? par1IBlockAccess.getBlockMetadata(par2, par3, par4) : par1IBlockAccess.isBlockProvidingPowerTo(par2, par3, par4, par5)) : 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = ((MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 3);
        boolean flag = this.func_94478_d(par1World, par2, par3, par4, l);

        if (flag)
        {
            par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 1);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        this.func_94483_i_(par1World, par2, par3, par4);
    }

    protected void func_94483_i_(World par1World, int par2, int par3, int par4)
    {
        int l = getDirection(par1World.getBlockMetadata(par2, par3, par4));

        if (l == 1)
        {
            par1World.notifyBlockOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID, 4);
        }

        if (l == 3)
        {
            par1World.notifyBlockOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID, 5);
        }

        if (l == 2)
        {
            par1World.notifyBlockOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID, 2);
        }

        if (l == 0)
        {
            par1World.notifyBlockOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID, 3);
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5)
    {
        if (this.isRepeaterPowered)
        {
            par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this.blockID);
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, this.blockID);
        }

        super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    protected boolean func_94477_d(int par1)
    {
        Block block = Block.blocksList[par1];
        return block != null && block.canProvidePower();
    }

    protected int func_94480_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return 15;
    }

    public static boolean isRedstoneRepeaterBlockID(int par0)
    {
        return Block.redstoneRepeaterIdle.func_94487_f(par0) || Block.redstoneComparatorIdle.func_94487_f(par0);
    }

    public boolean func_94487_f(int par1)
    {
        return par1 == this.func_94485_e().blockID || par1 == this.func_94484_i().blockID;
    }

    public boolean func_83011_d(World par1World, int par2, int par3, int par4, int par5)
    {
        int i1 = getDirection(par5);

        if (isRedstoneRepeaterBlockID(par1World.getBlockId(par2 - Direction.offsetX[i1], par3, par4 - Direction.offsetZ[i1])))
        {
            int j1 = par1World.getBlockMetadata(par2 - Direction.offsetX[i1], par3, par4 - Direction.offsetZ[i1]);
            int k1 = getDirection(j1);
            return k1 != i1;
        }
        else
        {
            return false;
        }
    }

    protected int func_94486_g(int par1)
    {
        return this.func_94481_j_(par1);
    }

    protected abstract int func_94481_j_(int i);

    protected abstract BlockRedstoneLogic func_94485_e();

    protected abstract BlockRedstoneLogic func_94484_i();

    /**
     * Returns true if the given block ID is equivalent to this one. Example: redstoneTorchOn matches itself and
     * redstoneTorchOff, and vice versa. Most blocks only match themselves.
     */
    public boolean isAssociatedBlockID(int par1)
    {
        return this.func_94487_f(par1);
    }
}
