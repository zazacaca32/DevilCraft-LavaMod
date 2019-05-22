package net.minecraft.client.addon.tchestplate.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.addon.lavamobs.LavaModMobs;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemScroll extends Item
{
    private int id;

    public ItemScroll(int par1, int scrollid)
    {
        super(par1);
        this.setUnlocalizedName("item_scroll_" + scrollid);
        this.setCreativeTab(LavaChestPlate.tabItemss);
        this.setMaxStackSize(1);
        this.id = scrollid;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
        super.itemIcon = par1IconRegister.registerIcon("provider:eventscroll_" + this.id);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack i, EntityPlayer e, List l, boolean b)
    {
        switch (this.id)
        {
            case 0:
                l.add("§8Распечатывается на ПКМ");
                l.add("§8Выпадает от 1 до 10 тотемов");
                break;

            case 1:
                l.add("§8Распечатывается на ПКМ");
                l.add("§8Выпадает от 1 до 10 старый койнов");
                break;

            case 2:
                l.add("§8Распечатывается на ПКМ");
                l.add("§8Выпадает 1 из свитков");
                break;

            case 3:
                l.add("§850% шанс снятия итема");
                break;

            case 4:
                l.add("§8100% шанс снятия итема");
        }
    }

    public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p)
    {
        if (!w.isRemote)
        {
            if (!p.capabilities.isCreativeMode)
            {
                --is.stackSize;
            }

            int r;

            if (this.id == 0)
            {
                r = w.rand.nextInt(10);
                p.dropPlayerItem(new ItemStack(LavaModMobs.TotemItem.itemID, r + 1, 3));
            }
            else if (this.id == 1)
            {
                r = w.rand.nextInt(10);
                p.dropPlayerItem(new ItemStack(LavaModMobs.TotemItem.itemID, r + 1, 4));
            }
            else if (this.id == 2)
            {
                r = w.rand.nextInt(5);

                if (r == 2)
                {
                    r = w.rand.nextInt(5);
                }
                else
                {
                    p.dropPlayerItem(new ItemStack(LavaChestPlate.scroll[r], 1, 0));
                }
            }

            return is;
        }
        else
        {
            return is;
        }
    }
}
