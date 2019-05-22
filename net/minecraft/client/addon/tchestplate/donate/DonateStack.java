package net.minecraft.client.addon.tchestplate.donate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class DonateStack
{
    public ItemStack source;
    public ItemStack fixed;
    static int i = 0;

    public DonateStack(ItemStack var1)
    {
        this.source = var1;
        this.fixed = var1.copy();
    }

    public DonateStack(ItemStack var1, ItemStack var2)
    {
        this.source = var1;
        this.fixed = var2;
    }

    public void writeToNBT(NBTTagCompound var1)
    {
        NBTTagCompound var2 = new NBTTagCompound();

        if (this.source != null)
        {
            this.source.writeToNBT(var2);
        }

        var1.setCompoundTag("*1", var2);
        var2 = new NBTTagCompound();

        if (this.fixed != null)
        {
            this.fixed.writeToNBT(var2);
        }

        var1.setCompoundTag("*2", var2);
    }

    public static DonateStack loadItemStackFromNBT(NBTTagCompound var0)
    {
        NBTTagCompound var1 = var0.getCompoundTag("*1");
        ItemStack var2 = null;

        if (var1 != null)
        {
            var2 = ItemStack.loadItemStackFromNBT(var1);
            System.out.println(++i + ")" + var2);
        }

        ItemStack var3 = null;
        var1 = var0.getCompoundTag("*2");

        if (var1 != null)
        {
            var3 = ItemStack.loadItemStackFromNBT(var1);
        }

        return var2 != null && var3 != null ? new DonateStack(var2, var3) : null;
    }

    public void setStackSize(int var1)
    {
        this.source.stackSize = var1;
    }

    public int getPrice()
    {
        if (this.source == null)
        {
            return 0;
        }
        else
        {
            NBTTagCompound var1 = Utils.getOrCreateNbtData(this.source);
            NBTTagCompound var2 = var1.getCompoundTag("display");
            return var2.getInteger("price");
        }
    }

    public int getDiscount()
    {
        if (this.source == null)
        {
            return 0;
        }
        else
        {
            NBTTagCompound var1 = Utils.getOrCreateNbtData(this.source);
            NBTTagCompound var2 = var1.getCompoundTag("display");
            return var2.getInteger("discount");
        }
    }

    public List getDescriptions()
    {
        ArrayList var1 = new ArrayList();

        if (this.source == null)
        {
            return var1;
        }
        else
        {
            NBTTagCompound var2 = Utils.getOrCreateNbtData(this.source);
            NBTTagCompound var3 = var2.getCompoundTag("display");
            NBTBase var4 = var3.getTag("Lore");

            if (var4 instanceof NBTTagList)
            {
                for (int var5 = 0; var5 < ((NBTTagList)var4).tagCount(); ++var5)
                {
                    NBTTagString var6 = (NBTTagString)((NBTTagList)var4).tagAt(var5);

                    if (var6.data != null && !var6.data.startsWith("Цена:") && !var6.data.startsWith("Скидка:"))
                    {
                        var1.add(var6.data);
                    }
                }

                return var1;
            }
            else
            {
                return var1;
            }
        }
    }

    public ItemStack addLoretoItemStack(List var1)
    {
        NBTTagCompound var2 = this.source.stackTagCompound;

        if (var2 == null)
        {
            var2 = new NBTTagCompound();
            var2.setCompoundTag("display", new NBTTagCompound());
            this.source.stackTagCompound = var2;
        }

        var2 = this.source.stackTagCompound.getCompoundTag("display");
        NBTTagList var3 = new NBTTagList();
        String var4 = (String)var1.remove(0);
        String var5 = (String)var1.remove(0);
        Iterator var6 = var1.iterator();

        while (var6.hasNext())
        {
            String var7 = (String)var6.next();

            if (!var7.isEmpty())
            {
                var3.appendTag(new NBTTagString("", var7));
            }
        }

        if (!var4.isEmpty())
        {
            var3.appendTag(new NBTTagString("", "Цена: " + var4));
            var2.setInteger("price", Integer.valueOf(var4).intValue());
        }

        if (!var5.isEmpty())
        {
            var3.appendTag(new NBTTagString("", "Скидка: " + var5 + "%"));
            var2.setInteger("discount", Integer.valueOf(var5).intValue());
        }

        var2.setTag("Lore", var3);
        this.source.stackTagCompound.setCompoundTag("display", var2);
        return this.source;
    }

    public int getStackSize()
    {
        return this.source.stackSize;
    }

    public void setStack(ItemStack var1)
    {
        this.source = var1;
    }
}
