package net.minecraft.client.addon.lavablock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemNewLavaCoin extends MultiItem
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    int count = 0;
    public static final String[] Coin = new String[] {"", "gold"};

    public ItemNewLavaCoin(int par1, int count)
    {
        super(par1, count);
        this.count = count;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[this.count];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("lavablock:" + this.getUnlocalizedName().substring(5) + Coin[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return par1 > this.icons.length ? this.icons[0] : this.icons[par1];
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        if (par1ItemStack.hasTagCompound())
        {
            if (par1ItemStack.getItemDamage() == 0)
            {
                par3List.add("§6 ЛаваКойн 20" + ModBlocks.getOrCreateNbtData(par1ItemStack).getByte("nb"));
            }

            if (par1ItemStack.getItemDamage() == 1)
            {
                par3List.add("§6 ЗолотойЛаваКойн 20" + ModBlocks.getOrCreateNbtData(par1ItemStack).getByte("nb"));
            }
        }
    }
}
