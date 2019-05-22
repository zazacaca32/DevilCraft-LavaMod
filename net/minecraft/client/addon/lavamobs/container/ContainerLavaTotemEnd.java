package net.minecraft.client.addon.lavamobs.container;

import net.minecraft.client.addon.lavamobs.EventMobCount;
import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerLavaTotemEnd extends Container
{
    public TileBlockLavaTotem tileEntity;
    private EntityPlayer player;
    public boolean endQ = false;
    public byte idevent = -1;
    public byte idmob = 0;
    public short mobcount = 0;

    public ContainerLavaTotemEnd(TileBlockLavaTotem tileEntity, EntityPlayer player)
    {
        this.tileEntity = tileEntity;
        this.player = player;
        this.bindPlayerInventory(player.inventory);
        ExtendedPlayer Epl = ExtendedPlayer.get(player);

        if (Epl.idevent >= 0)
        {
            this.endQ = EventMobCount.SetContPlayer(Epl, Epl.idmob, false);
            this.idevent = Epl.idevent;
            this.idmob = Epl.idmob;
            this.mobcount = Epl.mobcount;
        }

        if (this.endQ)
        {
            this.addSlotToContainer(new Slot(tileEntity, 5, 80, 108));
        }
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
    {
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 185));
        }
    }

    public final ItemStack transferStackInSlot(EntityPlayer p, int idx)
    {
        return null;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntity.isUseableByPlayer(entityplayer);
    }
}
