package net.minecraft.client.addon.tchestplate.overlaygui;

import net.minecraft.client.addon.tchestplate.slots.BaseSlotInv;
import net.minecraft.client.addon.tchestplate.slots.SlotInvArmorExt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ArmorExtContainer extends Container
{
    public InventoryPlayer invPlayer;

    public ArmorExtContainer(InventoryPlayer var1, ArmorExtInventory var2)
    {
        this.invPlayer = var1;
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO, var2, 0, 84, 7));
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO, var2, 1, 108, 9));
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO, var2, 2, 132, 7));
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO, var2, 3, 108, 27));
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO, var2, 4, 84, 54));
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO, var2, 5, 132, 54));
        this.addSlotToContainer(new SlotInvArmorExt(BaseSlotInv.Access.IO,  var2, 6, 141, 30));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer var1)
    {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer var1, int var2)
    {
        Object var3 = null;
        return (ItemStack)var3;
    }
}
