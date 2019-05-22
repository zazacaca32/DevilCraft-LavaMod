package net.minecraft.client.addon.tchestplate.weapon.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class BaseEnergyOilItem extends Item
{
    protected Integer maxCharge = Integer.valueOf(0);
    HashMap map = new HashMap();
    protected long clearCache = 0L;
    public Long tick = Long.valueOf(0L);

    public BaseEnergyOilItem(int par1, int max, int cooldown, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1);
        this.maxCharge = Integer.valueOf(cooldown);
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (!par2World.isRemote && par4 < 36)
        {
            long timen = System.currentTimeMillis();

            if (this.clearCache < timen)
            {
                this.map.clear();
                this.map = new HashMap(this.map);
                this.clearCache = timen + 10800000L;
            }

            ArrayList l;

            if (this.map.containsKey(Integer.valueOf(par3Entity.entityId)))
            {
                l = (ArrayList)this.map.get(Integer.valueOf(par3Entity.entityId));

                if (((Long)l.get(par4)).longValue() < timen)
                {
                    l.set(par4, Long.valueOf(timen + (par5 ? 1000L : 1000L)));
                    this.map.put(Integer.valueOf(par3Entity.entityId), l);

                    if (this.getCharge(par1ItemStack).intValue() >= this.maxCharge.intValue())
                    {
                        return;
                    }

                    this.charge(par1ItemStack, 1);
                }
            }
            else
            {
                l = new ArrayList(Collections.nCopies(36, Long.valueOf(0L)));
                l.add(par4, Long.valueOf(timen));
                this.map.put(Integer.valueOf(par3Entity.entityId), l);
            }
        }
    }

    abstract ItemStack CreateItem(ItemStack var1);

    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        ItemStack j = new ItemStack(par1, 1, 0);
        this.charge(j, this.maxCharge.intValue());
        j = this.CreateItem(j);
        par3List.add(j);
    }

    public Integer charge(ItemStack stack, int amount)
    {
        BaseEnergyOilItem item = (BaseEnergyOilItem)stack.getItem();

        if (amount >= 0 && stack.stackSize <= 1)
        {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(stack);
            int charge = nbtData.getInteger("chargeoil");

            if (amount > item.getMaxCharge().intValue() - charge)
            {
                amount = item.getMaxCharge().intValue() - charge;
            }

            charge += amount;
            nbtData.setInteger("chargeoil", charge);
            return Integer.valueOf(charge);
        }
        else
        {
            return Integer.valueOf(0);
        }
    }

    public Integer getMaxCharge()
    {
        return this.maxCharge;
    }

    public Integer getCharge(ItemStack stack)
    {
        BaseEnergyOilItem item = (BaseEnergyOilItem)stack.getItem();

        if (stack.stackSize > 1)
        {
            return Integer.valueOf(0);
        }
        else
        {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(stack);
            int charge = nbtData.getInteger("chargeoil");

            if (charge > item.getMaxCharge().intValue())
            {
                charge = item.getMaxCharge().intValue();
            }

            return Integer.valueOf(charge);
        }
    }

    public String getOwner(ItemStack stack, String defaultOwner)
    {
        BaseEnergyOilItem item = (BaseEnergyOilItem)stack.getItem();

        if (stack.stackSize > 1)
        {
            return null;
        }
        else
        {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(stack);
            String owner = nbtData.getString("lStaffFriz");

            if (owner.isEmpty())
            {
                nbtData.setString("lStaffFriz", defaultOwner);
                return defaultOwner;
            }
            else
            {
                return owner;
            }
        }
    }

    public Integer discharge(ItemStack stack, int value)
    {
        BaseEnergyOilItem item = (BaseEnergyOilItem)stack.getItem();

        if (stack.stackSize > 1)
        {
            return Integer.valueOf(0);
        }
        else
        {
            NBTTagCompound nbtData = Utils.getOrCreateNbtData(stack);
            int charge = nbtData.getInteger("chargeoil");

            if (charge <= 0)
            {
                return Integer.valueOf(0);
            }
            else
            {
                if (charge > item.getMaxCharge().intValue())
                {
                    charge = item.getMaxCharge().intValue();
                }

                if (charge - value >= 0)
                {
                    charge -= value;
                }
                else
                {
                    charge = 0;
                }

                nbtData.setInteger("chargeoil", charge);
                return Integer.valueOf(charge);
            }
        }
    }
}
