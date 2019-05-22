package net.minecraft.client.addon.tchestplate.donate.containers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.slots.ISlotPlayerSide;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotCoupon;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonate;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateInv;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateInvHotBar;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateOffert;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DonateContainer extends Container
{
    public InventoryPlayer invPlayer;
    public ArmorExtInventory armor;
    DonateManagerInv inv;
    protected int dualstatusDonateOffert = -1;

    public DonateContainer()
    {
    }

    public DonateContainer(InventoryPlayer var1, ArmorExtInventory var2)
    {
        this.invPlayer = var1;
        this.armor = var2;
        this.inv = LavaChestPlate.instanceDonate;
        this.inv.setContainer(this);
        int var3;
        int var4;

        for (var3 = 0; var3 < 6; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new SlotDonate(var2, this.inv, var4 + var3 * 9, 8 + var4 * 18, 9 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 4; ++var3)
        {
            this.addSlotToContainer(new SlotDonateOffert(var2, this.inv, var3, 8 + var3 * 18, 121));
        }

        this.addSlotToContainer(new SlotCoupon(var2, 0, 8, 143));

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

            if (var5 instanceof SlotDonate)
            {
                if (Utils.isClient())
                {
                    SlotDonate var6 = (SlotDonate)var5;
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

    public List getISlotPlayerSide()
    {
        ArrayList var1 = new ArrayList();

        for (int var2 = 0; var2 < super.inventorySlots.size(); ++var2)
        {
            Slot var3 = (Slot)super.inventorySlots.get(var2);

            if (var3 instanceof ISlotPlayerSide)
            {
                var1.add(var3);
            }
        }

        return var1;
    }

    public int findindexOfferSlot(ItemStack var1)
    {
        for (int var2 = 0; var2 < this.armor.invOfferts.length; ++var2)
        {
            if (this.armor.invOfferts[var2] == null)
            {
                return var2;
            }

            if (this.armor.invOfferts[var2].isItemEqual(var1) && this.armor.invOfferts[var2].getMaxStackSize() >= this.armor.invOfferts[var2].stackSize + 1)
            {
                return var2;
            }
        }

        return -1;
    }

    public int findSizeStackInSlotDonateInv(ItemStack var1)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            int var2 = 0;

            for (int var3 = 0; var3 < this.armor.invOfferts.length; ++var3)
            {
                if (this.armor.invOfferts[var3] != null && this.armor.invOfferts[var3].isItemEqual(var1))
                {
                    var2 += this.armor.invOfferts[var3].stackSize;
                }
            }

            return var2;
        }
    }

    public int findSizeStackORSlotDonateInv(ItemStack var1)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            int var2 = 0;

            for (int var3 = 0; var3 < this.armor.invOfferts.length; ++var3)
            {
                if (this.armor.invOfferts[var3] != null && !this.armor.invOfferts[var3].isItemEqual(var1))
                {
                    ++var2;
                }
            }

            return var2;
        }
    }

    public int findSizeStackInSlotInvFree(ItemStack var1)
    {
        if (var1 == null)
        {
            return 0;
        }
        else
        {
            int var2 = 0;
            Iterator var3 = this.getISlotPlayerSide().iterator();

            while (var3.hasNext())
            {
                Slot var4 = (Slot)var3.next();

                if (var4 != null)
                {
                    ItemStack var5 = var4.getStack();

                    if (var5 != null)
                    {
                        if (var5.isItemEqual(var1))
                        {
                            var2 += var1.getMaxStackSize() - var5.stackSize;
                        }
                    }
                    else
                    {
                        var2 += var1.getMaxStackSize();
                    }
                }
            }

            return var2;
        }
    }

    public void addtoDonateOffert(ItemStack var1, int var2)
    {
        if (var1 != null)
        {
            int var3 = this.findindexOfferSlot(var1);

            if (var3 != -1)
            {
                int var4 = this.findSizeStackInSlotDonateInv(var1);
                int var5 = this.findSizeStackInSlotInvFree(var1);
                int var6 = this.findSizeStackORSlotDonateInv(var1);
                var5 -= var6 * var1.getMaxStackSize();

                if (var5 > var1.getMaxStackSize() + var4)
                {
                    var5 = var1.getMaxStackSize();
                }
                else
                {
                    var5 -= var4;
                }

                if (var5 > 0 && var4 < var5 + var1.getMaxStackSize() * 4)
                {
                    if (var5 > var2)
                    {
                        var5 = var2;
                    }

                    if (this.armor.invOfferts[var3] == null)
                    {
                        this.armor.invOfferts[var3] = var1.copy();

                        if (var5 > var2)
                        {
                            this.armor.invOfferts[var3].stackSize = var2;
                        }
                        else
                        {
                            this.armor.invOfferts[var3].stackSize = var5;
                        }
                    }
                    else if (this.armor.invOfferts[var3].getMaxStackSize() >= this.armor.invOfferts[var3].stackSize + var2)
                    {
                        if (this.armor.invOfferts[var3].stackSize + var2 > var5)
                        {
                            this.armor.invOfferts[var3].stackSize += var5;
                        }
                        else
                        {
                            this.armor.invOfferts[var3].stackSize += var2;
                        }
                    }
                    else
                    {
                        int var7 = this.armor.invOfferts[var3].getMaxStackSize() - this.armor.invOfferts[var3].stackSize;

                        if (var7 > var5)
                        {
                            this.armor.invOfferts[var3].stackSize += var5;
                        }
                        else
                        {
                            this.armor.invOfferts[var3].stackSize = this.armor.invOfferts[var3].getMaxStackSize();
                        }
                    }
                }
            }
        }
    }

    public void addCraftingToCrafters(ICrafting var1)
    {
        super.addCraftingToCrafters(var1);
        var1.sendProgressBarUpdate(this, 0, this.armor.statusDonateOffert);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < super.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)super.crafters.get(var1);

            if (this.dualstatusDonateOffert != this.armor.statusDonateOffert)
            {
                var2.sendProgressBarUpdate(this, 0, this.armor.statusDonateOffert);
            }
        }

        this.dualstatusDonateOffert = this.armor.statusDonateOffert;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var1, int var2)
    {
        if (var1 == 0)
        {
            this.armor.statusDonateOffert = var2;
        }
    }
}
