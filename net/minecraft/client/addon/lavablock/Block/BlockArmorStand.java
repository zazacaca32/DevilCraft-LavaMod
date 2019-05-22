package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.ModBlocks;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockArmorStand extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon icon;
    private final Random furnaceRand = new Random();

    public BlockArmorStand(int par1)
    {
        super(par1, Material.rock);
        this.setHardness(2.5F);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("ArmorStandBlock");
    }

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 2.0F, 0.875F);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = ModBlocks.QuartzBlock.getIcon(1, 0);
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

    public TileEntity createNewTileEntity(World world)
    {
        return new ArmorStandTile();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack stack)
    {
        ArmorStandTile tile = (ArmorStandTile)world.getBlockTileEntity(x, y, z);
        tile.rotation = Math.round((player.rotationYawHead + 180.0F) * 16.0F / 360.0F);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!player.isSneaking())
        {
            ArmorStandTile tile = (ArmorStandTile)world.getBlockTileEntity(x, y, z);

            if (tile != null)
            {
                player.openGui(Tiny.instance, 502, world, x, y, z);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        ArmorStandTile tileentityBase = (ArmorStandTile)par1World.getBlockTileEntity(par2, par3, par4);

        if (tileentityBase != null)
        {
            for (int j1 = 0; j1 < tileentityBase.getSizeInventory(); ++j1)
            {
                ItemStack itemstack = tileentityBase.getStackInSlot(j1);

                if (itemstack != null)
                {
                    float f = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int k1 = this.furnaceRand.nextInt(21) + 10;

                        if (k1 > itemstack.stackSize)
                        {
                            k1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= k1;
                        EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.furnaceRand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.furnaceRand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.furnaceRand.nextGaussian() * f3);
                        par1World.spawnEntityInWorld(entityitem);
                    }
                }
            }

            par1World.func_96440_m(par2, par3, par4, par5);
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}
