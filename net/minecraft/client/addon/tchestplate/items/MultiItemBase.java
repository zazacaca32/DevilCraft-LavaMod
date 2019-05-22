package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.tchestplate.IUpdateItemExtPlayer;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MultiItemBase extends Item
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    int count = 0;

    public MultiItemBase(int par1, int count)
    {
        super(par1);
        this.count = count;
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(64);
        this.setCreativeTab(LavaChestPlate.tabItemss);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[this.count];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("LavaChestPlate:" + this.getUnlocalizedName().substring(5) + i);
        }
    }

    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        return par1 > this.icons.length ? this.icons[0] : this.icons[par1];
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int x = 0; x < this.count; ++x)
        {
            par3List.add(new ItemStack(this, 1, x));
        }
    }

    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + ".Mthestplate" + i;
    }

    public void onUpdateServer(ItemStack paramItemStack1, World paramWorld, ExtendedPlayer paramExtendedPlayer, int paramInt, boolean paramBoolean, long paramLong, IUpdateItemExtPlayer.SideExt paramSideExt)
    {
    }
}
