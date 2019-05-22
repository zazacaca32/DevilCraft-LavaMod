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

public class BlockForged extends Block
{
    public static final String[] ForgedTextureTypes = new String[] {"white", "blue", "black"};
    @SideOnly(Side.CLIENT)
    private Icon[] forgedblock;

    public BlockForged(int id)
    {
        super(id, Material.iron);
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= this.forgedblock.length)
        {
            par2 = 0;
        }

        return this.forgedblock[par2];
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        this.forgedblock = new Icon[ForgedTextureTypes.length];

        for (int i = 0; i < this.forgedblock.length; ++i)
        {
            this.forgedblock[i] = par1IconRegister.registerIcon("lavablock:" + this.getUnlocalizedName().substring(5) + "_" + ForgedTextureTypes[i]);
        }
    }

    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
}
