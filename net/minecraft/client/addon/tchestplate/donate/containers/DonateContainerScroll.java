package net.minecraft.client.addon.tchestplate.donate.containers;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotCoupon;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateInv;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateInvHotBar;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateOffert;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateScroll;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DonateContainerScroll extends DonateContainer
{
    private List invItemStacks = new ArrayList();

    public DonateContainerScroll(InventoryPlayer var1, ArmorExtInventory var2)
    {
        super.invPlayer = var1;
        super.armor = var2;
        super.inv = LavaChestPlate.instanceDonate;
        super.inv.setContainer(this);
        int var3;

        for (var3 = 0; var3 < super.inv.inv.length; ++var3)
        {
            this.invItemStacks.add((ItemStack)null);
        }

        int var4;

        for (var3 = 0; var3 < 6; ++var3)
        {
            for (var4 = 0; var4 < 8; ++var4)
            {
                this.addSlotToContainer(new SlotDonateScroll(var2, super.inv, var4 + var3 * 8, 8 + var4 * 18, 9 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 4; ++var3)
        {
            this.addSlotToContainer(new SlotDonateOffert(var2, super.inv, var3, 8 + var3 * 18, 121));
        }

        this.addSlotToContainer(new SlotCoupon(new IInventory()
        {
            public int getSizeInventory()
            {
                return 1;
            }
            public ItemStack getStackInSlot(int var1)
            {
                return DonateContainerScroll.access$001(DonateContainerScroll.this).Coupon;
            }
            public ItemStack decrStackSize(int var1, int var2)
            {
                if (DonateContainerScroll.access$101(DonateContainerScroll.this).Coupon != null)
                {
                    ItemStack var3;

                    if (DonateContainerScroll.access$201(DonateContainerScroll.this).Coupon.stackSize <= var2)
                    {
                        var3 = DonateContainerScroll.access$301(DonateContainerScroll.this).Coupon;
                        DonateContainerScroll.access$401(DonateContainerScroll.this).Coupon = null;
                        return var3;
                    }
                    else
                    {
                        var3 = DonateContainerScroll.access$501(DonateContainerScroll.this).Coupon.splitStack(var2);

                        if (DonateContainerScroll.access$601(DonateContainerScroll.this).Coupon.stackSize == 0)
                        {
                            DonateContainerScroll.access$701(DonateContainerScroll.this).Coupon = null;
                        }

                        return var3;
                    }
                }
                else
                {
                    return null;
                }
            }
            public ItemStack getStackInSlotOnClosing(int var1)
            {
                return DonateContainerScroll.access$801(DonateContainerScroll.this).Coupon;
            }
            public void setInventorySlotContents(int var1, ItemStack var2)
            {
                DonateContainerScroll.access$901(DonateContainerScroll.this).Coupon = var2;
            }
            public String getInvName()
            {
                return "inv.Coupon";
            }
            public boolean isInvNameLocalized()
            {
                return false;
            }
            public int getInventoryStackLimit()
            {
                return 64;
            }
            public void onInventoryChanged()
            {
            }
            public boolean isUseableByPlayer(EntityPlayer var1)
            {
                return true;
            }
            public void openChest()
            {
            }
            public void closeChest()
            {
            }
            public boolean isStackValidForSlot(int var1, ItemStack var2)
            {
                return false;
            }
        }, 0, 8, 143));

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new SlotDonateInv(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 165 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new SlotDonateInvHotBar(var1, var3, 8 + var3 * 18, 223));
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        return null;
    }

    public ItemStack slotClick(int var1, int var2, int var3, EntityPlayer var4)
    {
        if (var1 >= 0 && var1 < super.inventorySlots.size())
        {
            Slot var5 = (Slot)super.inventorySlots.get(var1);

            if (var5 instanceof SlotDonateOffert)
            {
                if (Utils.isClient())
                {
                    SlotDonateOffert var61 = (SlotDonateOffert)var5;
                    var61.putStack((ItemStack)null);
                }

                return null;
            }

            if (var5 instanceof SlotDonateScroll)
            {
                if (Utils.isClient())
                {
                    SlotDonateScroll var6 = (SlotDonateScroll)var5;
                    ItemStack var7 = var4.inventory.getItemStack();

                    if (var7 != null)
                    {
                        return null;
                    }

                    if (var2 > 0)
                    {
                        this.addtoDonateOffert(var6.getStack(), 16);
                    }
                    else
                    {
                        this.addtoDonateOffert(var6.getStack(), 1);
                    }
                }

                return null;
            }
        }

        return super.slotClick(var1, var2, var3, var4);
    }

    static ArmorExtInventory access$001(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$101(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$201(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$301(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$401(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$501(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$601(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$701(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$801(DonateContainerScroll x0)
    {
        return x0.armor;
    }

    static ArmorExtInventory access$901(DonateContainerScroll x0)
    {
        return x0.armor;
    }
}
