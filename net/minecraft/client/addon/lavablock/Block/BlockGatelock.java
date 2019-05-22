package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.addon.lavablock.Tile.GateTileBlock;
import net.minecraft.client.addon.lavablock.Tile.GatelockTileBlock;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGatelock extends BlockContainer
{
    static final Random random = new Random();
    @SideOnly(Side.CLIENT)
    private Icon icon;
    String[] isImmuneToFire = new String[] {"field_71429_W", "leftClickCounter"};
    public long time;

    public BlockGatelock(int id)
    {
        super(id, Material.iron);
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
        super.blockIcon = Block.blockIron.getIcon(1, 0);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = par1World.getBlockMetadata(par2, par3, par4);

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | i1, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | i1, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | i1, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | i1, 2);
        }
    }

    public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
        if (par1World.isRemote)
        {
            int var111;

            if (par1World.getBlockMetadata(par2, par3, par4) < 4)
            {
                for (var111 = 0; var111 < 3; ++var111)
                {
                    par1World.spawnParticle("smoke", (double)((float)par2 + random.nextFloat()), (double)((float)par3 + 0.5F + random.nextFloat()), (double)((float)par4 + random.nextFloat()), 0.0D, 0.0D, 0.0D);
                }

                return;
            }

            if (par1World.getBlockMetadata(par2, par3, par4) < 8)
            {
                for (var111 = 0; var111 < 3; ++var111)
                {
                    par1World.spawnParticle("reddust", (double)((float)par2 + random.nextFloat()), (double)((float)par3 + 0.5F + random.nextFloat()), (double)((float)par4 + random.nextFloat()), 0.0D, 0.0D, 0.0D);
                }

                return;
            }

            if (par1World.getBlockMetadata(par2, par3, par4) > 7)
            {
                for (var111 = 0; var111 < 3; ++var111)
                {
                    par1World.spawnParticle("flame", (double)((float)par2 + random.nextFloat()), (double)((float)par3 + random.nextFloat()), (double)((float)par4 + random.nextFloat()), 0.0D, 0.0D, 0.0D);
                    par1World.spawnParticle("smoke", (double)((float)par2 + random.nextFloat()), (double)((float)par3 + 0.5F + random.nextFloat()), (double)((float)par4 + random.nextFloat()), 0.0D, 0.0D, 0.0D);
                }
            }
        }

        if (!par1World.isRemote)
        {
            GatelockTileBlock var11 = (GatelockTileBlock)par1World.getBlockTileEntity(par2, par3, par4);

            if (var11 != null)
            {
                long t = System.currentTimeMillis();

                if (t > this.time)
                {
                    this.time = t + 200L;
                    par1World.playSoundEffect((double)par2, (double)par3, (double)par4, "lavablock.sound1", 2.0F, 1.0F);
                }

                var11.currdamage -= this.onDamageBlock(par5EntityPlayer);
                int proc = var11.currdamage / (var11.maxdamage / 100);
                int i1;

                if (var11.flag && proc < 70 && proc > 30)
                {
                    var11.flag = false;
                    i1 = par1World.getBlockMetadata(par2, par3, par4);
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 4, 2);
                }

                if (!var11.flag && proc < 30)
                {
                    var11.flag = true;
                    i1 = par1World.getBlockMetadata(par2, par3, par4);
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, i1 + 4, 2);
                }

                if (proc <= 0)
                {
                    i1 = par1World.getBlockMetadata(par2, par3, par4) & 3;
                    helperGateBlock(par1World, par2, par3, par4, i1, true);
                    explodedGateBlock(par1World, par2, par3, par4);
                }
            }
        }
    }

    public static void helperGateBlock(World par1World, int par2, int par3, int par4, int i1, boolean flag)
    {
        --par3;
        int n;
        int p;

        switch (i1)
        {
            case 0:
                ++par4;
                par2 -= 3;

                for (n = 0; n < 8; ++n)
                {
                    for (p = 0; p < 7; ++p)
                    {
                        if (flag)
                        {
                            if (par1World.getBlockId(par2 + p, par3 + n, par4) == 2552)
                            {
                                ((GateTileBlock)par1World.getBlockTileEntity(par2 + p, par3 + n, par4)).isExpl = true;
                            }
                        }
                        else if (par1World.getBlockId(par2 + p, par3 + n, par4) == 0)
                        {
                            par1World.setBlock(par2 + p, par3 + n, par4, 2552, i1, 3);
                        }
                    }
                }

                return;

            case 1:
                --par2;
                par4 -= 3;

                for (n = 0; n < 8; ++n)
                {
                    for (p = 0; p < 7; ++p)
                    {
                        if (flag)
                        {
                            if (par1World.getBlockId(par2, par3 + n, par4 + p) == 2552)
                            {
                                ((GateTileBlock)par1World.getBlockTileEntity(par2, par3 + n, par4 + p)).isExpl = true;
                            }
                        }
                        else if (par1World.getBlockId(par2, par3 + n, par4 + p) == 0)
                        {
                            par1World.setBlock(par2, par3 + n, par4 + p, 2552, i1, 3);
                        }
                    }
                }

                return;

            case 2:
                --par4;
                par2 -= 3;

                for (n = 0; n < 8; ++n)
                {
                    for (p = 0; p < 7; ++p)
                    {
                        if (flag)
                        {
                            if (par1World.getBlockId(par2 + p, par3 + n, par4) == 2552)
                            {
                                ((GateTileBlock)par1World.getBlockTileEntity(par2 + p, par3 + n, par4)).isExpl = true;
                            }
                        }
                        else if (par1World.getBlockId(par2 + p, par3 + n, par4) == 0)
                        {
                            par1World.setBlock(par2 + p, par3 + n, par4, 2552, i1, 3);
                        }
                    }
                }

                return;

            case 3:
                ++par2;
                par4 -= 3;

                for (n = 0; n < 8; ++n)
                {
                    for (p = 0; p < 7; ++p)
                    {
                        if (flag)
                        {
                            if (par1World.getBlockId(par2, par3 + n, par4 + p) == 2552)
                            {
                                ((GateTileBlock)par1World.getBlockTileEntity(par2, par3 + n, par4 + p)).isExpl = true;
                            }
                        }
                        else if (par1World.getBlockId(par2, par3 + n, par4 + p) == 0)
                        {
                            par1World.setBlock(par2, par3 + n, par4 + p, 2552, i1, 3);
                        }
                    }
                }

            default:
        }
    }

    public static void explodedGateBlock(World par1World, int par2, int par3, int par4)
    {
        for (int n = 0; n < 3; ++n)
        {
            par1World.createExplosion((Entity)null, (double)((float)par2 + random.nextFloat()), (double)((float)par3 + random.nextFloat()), (double)((float)par4 + random.nextFloat()), 0.0F, true);
        }

        par1World.setBlock(par2, par3, par4, 0);
        par1World.removeBlockTileEntity(par2, par3, par4);
    }

    public int onDamageBlock(EntityPlayer player)
    {
        int id = 0;

        if (player.getCurrentEquippedItem() != null)
        {
            id = player.getCurrentEquippedItem().itemID;
        }

        boolean damage = false;
        byte damage1;

        switch (id)
        {
            case 267:
                damage1 = 4;
                break;

            case 268:
                damage1 = 2;
                break;

            case 272:
                damage1 = 3;
                break;

            case 276:
                damage1 = 6;
                break;

            case 283:
                damage1 = 5;
                break;

            case 2476:
                damage1 = 12;
                break;

            case 2477:
                damage1 = 17;
                break;

            case 2478:
                damage1 = 23;
                break;

            case 2855:
                damage1 = 21;
                break;

            case 20968:
                damage1 = 10;
                break;

            case 20969:
                damage1 = 15;
                break;

            case 20970:
                damage1 = 20;
                break;

            case 30149:
                damage1 = 7;
                break;

            case 30477:
                damage1 = 8;
                break;

            default:
                damage1 = 1;
        }

        return damage1;
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new GatelockTileBlock();
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        helperGateBlock(par1World, par2, par3, par4, i1, false);
    }
}
