package net.minecraft.client.addon.lavablock.Block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class BlockColorGlassPane extends BlockColorPane
{
    @SideOnly(Side.CLIENT)
    private static Icon[] icon_a;
    @SideOnly(Side.CLIENT)
    public static Icon[] icon_b;
    private static final String[] testicon = new String[] {"white", "black", "blue", "brown", "cyan", "gray", "green", "blue", "lime", "magenta", "orange", "pink", "purple", "red", "silver", "yellow"};

    public BlockColorGlassPane(int par1)
    {
        super(par1, "glass_pane_top_black", "glass_black");
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        super.icon = icon_a[par2];
        return icon_b[par2];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        icon_a = new Icon[16];
        icon_b = new Icon[16];

        for (int var2 = 0; var2 < icon_a.length; ++var2)
        {
            icon_a[var2] = par1IconRegister.registerIcon("lavablock:glass_pane_top_" + testicon[var2]);
            icon_b[var2] = par1IconRegister.registerIcon("lavablock:glass_" + testicon[var2]);
        }
    }

    public int damageDropped(int par1)
    {
        return par1;
    }

    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
        par3List.add(new ItemStack(par1, 1, 10));
        par3List.add(new ItemStack(par1, 1, 11));
        par3List.add(new ItemStack(par1, 1, 12));
        par3List.add(new ItemStack(par1, 1, 13));
        par3List.add(new ItemStack(par1, 1, 14));
        par3List.add(new ItemStack(par1, 1, 15));
    }
}
