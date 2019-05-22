package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockQuartz extends Block
{
    public static final String[] quartzBlockBlackTypes = new String[] {"default", "chiseled", "lines"};
    private static final String[] quartzBlockBlackTextureTypes = new String[] {"quartzblock_black_side", "quartzblock_black_chiseled", "quartzblock_black_lines", null, null};
    @SideOnly(Side.CLIENT)
    private Icon[] quartzblockblackIcons;
    @SideOnly(Side.CLIENT)
    private Icon quartzblock_black_chiseled_top;
    @SideOnly(Side.CLIENT)
    private Icon quartzblock_black_lines_top;
    @SideOnly(Side.CLIENT)
    private Icon quartzblock_black_top;
    @SideOnly(Side.CLIENT)
    private Icon quartzblock_black_bottom;

    public BlockQuartz(int id)
    {
        super(id, Material.rock);
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        if (par2 != 2 && par2 != 3 && par2 != 4)
        {
            if (par1 != 1 && (par1 != 0 || par2 != 1))
            {
                if (par1 == 0)
                {
                    return this.quartzblock_black_top;
                }
                else
                {
                    if (par2 < 0 || par2 >= this.quartzblockblackIcons.length)
                    {
                        par2 = 0;
                    }

                    return this.quartzblockblackIcons[par2];
                }
            }
            else
            {
                return par2 == 1 ? this.quartzblock_black_chiseled_top : this.quartzblock_black_top;
            }
        }
        else
        {
            return par2 != 2 || par1 != 1 && par1 != 0 ? (par2 == 3 && (par1 == 5 || par1 == 4) ? this.quartzblock_black_lines_top : (par2 != 4 || par1 != 2 && par1 != 3 ? this.quartzblockblackIcons[par2] : this.quartzblock_black_lines_top)) : this.quartzblock_black_lines_top;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.quartzblockblackIcons = new Icon[quartzBlockBlackTextureTypes.length];

        for (int i = 0; i < this.quartzblockblackIcons.length; ++i)
        {
            if (quartzBlockBlackTextureTypes[i] == null)
            {
                this.quartzblockblackIcons[i] = this.quartzblockblackIcons[i - 1];
            }
            else
            {
                this.quartzblockblackIcons[i] = par1IconRegister.registerIcon("lavablock:" + quartzBlockBlackTextureTypes[i]);
            }
        }

        this.quartzblock_black_top = par1IconRegister.registerIcon("lavablock:quartzblock_black_top");
        this.quartzblock_black_chiseled_top = par1IconRegister.registerIcon("lavablock:quartzblock_black_chiseled_top");
        this.quartzblock_black_lines_top = par1IconRegister.registerIcon("lavablock:quartzblock_black_lines_top");
        this.quartzblock_black_bottom = par1IconRegister.registerIcon("lavablock:quartzblock_black_bottom");
    }

    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        if (par9 == 2)
        {
            switch (par5)
            {
                case 0:
                case 1:
                    par9 = 2;
                    break;

                case 2:
                case 3:
                    par9 = 4;
                    break;

                case 4:
                case 5:
                    par9 = 3;
            }
        }

        return par9;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int damageDropped(int par1)
    {
        return par1 != 3 && par1 != 4 ? par1 : 2;
    }
}
