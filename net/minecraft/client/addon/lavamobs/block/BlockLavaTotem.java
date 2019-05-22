package net.minecraft.client.addon.lavamobs.block;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.StepSound;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdatebyte2;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.addon.tco.tiny.Tiny;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLavaTotem extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon icon;

    public BlockLavaTotem(int par1)
    {
        super(par1, Material.rock);
        this.setResistance(42.0F);
        this.setStepSound(new StepSound("stone", 1.0F, 1.0F));
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("LavaTotemBlock");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.blockIcon = par1IconRegister.registerIcon("anvil_base");
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

    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.15F, 0.0F, 0.15F, 0.85F, 4.0F, 0.85F);
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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float a, float b, float c)
    {
        if (world.isRemote)
        {
            return true;
        }
        else if (!entityplayer.isSneaking())
        {
            TileBlockLavaTotem var10 = (TileBlockLavaTotem)world.getBlockTileEntity(x, y, z);

            if (var10 != null)
            {
                ExtendedPlayer Epl = ExtendedPlayer.get(entityplayer);
                PacketDispatcher.sendPacketToPlayer((new PacketMAUpdatebyte2(Epl.idevent, Epl.idmob, Epl.mobcount)).makePacket(), (Player)entityplayer);

                if (Epl.idevent == -1)
                {
                    entityplayer.openGui(Tiny.instance, 523, world, x, y, z);
                }
                else
                {
                    entityplayer.openGui(Tiny.instance, 524, world, x, y, z);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new TileBlockLavaTotem();
    }
}
