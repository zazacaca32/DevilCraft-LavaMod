package net.minecraft.client.addon.lavamobs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class ItemInfo extends MultiItem
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    private int count = 0;
    public static final String[] InfoTextureTypes = new String[] {"0", "1", "2"};

    public ItemInfo(int par1, int count)
    {
        super(par1, count);
        this.count = count;
        this.setUnlocalizedName("Info");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[this.count];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("lavamobs:" + this.getUnlocalizedName().substring(5) + InfoTextureTypes[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return par1 > this.icons.length ? this.icons[0] : this.icons[par1];
    }
}
