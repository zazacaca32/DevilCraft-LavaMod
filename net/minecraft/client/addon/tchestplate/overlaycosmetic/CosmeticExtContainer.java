package net.minecraft.client.addon.tchestplate.overlaycosmetic;

import net.minecraft.client.addon.tchestplate.slots.BaseSlotInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CosmeticExtContainer extends Container
{
    public InventoryPlayer invPlayer;

    public CosmeticExtContainer(InventoryPlayer var1, CosmeticExtInventory var2)
    {
        this.invPlayer = var1;
        this.addSlotToContainer(new SlotInvCosmeticExt(BaseSlotInv.Access.IO, var2, 0, 74, 30));
        this.addSlotToContainer(new SlotInvCosmeticExt(BaseSlotInv.Access.IO, var2, 1, 106, 9));
        this.addSlotToContainer(new SlotInvCosmeticExt(BaseSlotInv.Access.IO, var2, 2, 106, 29));
        this.addSlotToContainer(new SlotInvCosmeticExt(BaseSlotInv.Access.IO, var2, 3, 74, 9));
        this.addSlotToContainer(new SlotInvCosmeticExt(BaseSlotInv.Access.IO, var2, 4, 106, 48));
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
