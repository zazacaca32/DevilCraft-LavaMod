package net.minecraft.client.addon.tchestplate.donate.containers;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.donate.slots.SlotDonateAdmConfig;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class DonateAdmConfigContainer extends Container
{
    public InventoryPlayer invPlayer;
    DonateManagerInv inv;
    public int indexItem;

    public DonateAdmConfigContainer(InventoryPlayer inventoryplayer, ArmorExtInventory armor, int index)
    {
        this.invPlayer = inventoryplayer;
        (this.inv = LavaChestPlate.instanceDonate).setContainer(this);
        this.indexItem = index;
        this.addSlotToContainer(new SlotDonateAdmConfig(index, this.inv, 0, 77, 18));
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
    {
        return null;
    }
}
