package net.minecraft.client.addon.lavablock.Container;

import net.minecraft.client.addon.lavablock.Slot.MySlotArmor;
import net.minecraft.client.addon.lavablock.Slot.SlotArmorStand;
import net.minecraft.client.addon.lavablock.Tile.ArmorStandTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerArmorStand extends Container
{
    public ArmorStandTile tileEntity;

    public ContainerArmorStand(ArmorStandTile tileEntity, EntityPlayer player)
    {
        this.tileEntity = tileEntity;
        this.bindPlayerInventory(player.inventory);

        for (int i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new SlotArmorStand(tileEntity, 3 - i, 80, 8 + i * 18, i));
        }

        this.addSlotToContainer(new Slot(tileEntity, 4, 8, 26));
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntity.isUseableByPlayer(entityplayer);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }

        for (i = 0; i < 4; ++i)
        {
            this.addSlotToContainer(new MySlotArmor(inventoryPlayer.player, inventoryPlayer, 39 - i, 98, 8 + i * 18, i));
        }
    }

    public final ItemStack transferStackInSlot(EntityPlayer p, int idx)
    {
        return null;
    }
}
