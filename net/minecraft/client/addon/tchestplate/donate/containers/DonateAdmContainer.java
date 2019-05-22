package net.minecraft.client.addon.tchestplate.donate.containers;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdm;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DonateAdmContainer extends Container
{
    public InventoryPlayer invPlayer;
    DonateManagerInv inv;

    public DonateAdmContainer(InventoryPlayer var1, ArmorExtInventory var2)
    {
        this.invPlayer = var1;
        this.inv = LavaChestPlate.instanceDonate;
        this.inv.setContainer(this);
        int var3;
        int var4;

        for (var3 = 0; var3 < 6; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new SlotDonateAdm(var2, this.inv, var4 + var3 * 9, 8 + var4 * 18, 7 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 120 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 178));
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
}
