package net.minecraft.client.addon.lavamobs.container;

import net.minecraft.client.addon.lavamobs.tile.TileBlockLavaTotem;
import net.minecraft.client.addon.tco.tiny.blocks.slots.SlotDisable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerLavaTotem extends Container
{
    public TileBlockLavaTotem tileEntity;
    private EntityPlayer player;

    public ContainerLavaTotem(TileBlockLavaTotem tileEntity, EntityPlayer player)
    {
        this.tileEntity = tileEntity;
        this.player = player;

        for (int i = 0; i < 5; ++i)
        {
            this.addSlotToContainer(new SlotDisable(tileEntity, i, 70, 40 + 25 * i));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntity.isUseableByPlayer(entityplayer);
    }
}
