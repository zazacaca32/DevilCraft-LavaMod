package net.minecraft.client.addon.lavamobs;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.addon.lavamobs.packets.PacketMAUpdateInt;
import net.minecraft.client.addon.lavamobs.tile.TileBlockRaid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public class ContainerBlockRaid extends Container
{
    TileBlockRaid tileEntity;
    private int custom_time;

    public ContainerBlockRaid(TileBlockRaid tileEntity)
    {
        this.tileEntity = tileEntity;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return this.tileEntity.isUseableByPlayer(entityplayer);
    }

    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)1, this.tileEntity.custom_time)).makePacket(), (Player)par1ICrafting);
    }

    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < super.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)super.crafters.get(i);

            if (this.custom_time != this.tileEntity.custom_time)
            {
                PacketDispatcher.sendPacketToPlayer((new PacketMAUpdateInt((byte)1, this.tileEntity.custom_time)).makePacket(), (Player)icrafting);
            }
        }

        this.custom_time = this.tileEntity.custom_time;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
    }

    public void updatetime(int idInt)
    {
        this.tileEntity.custom_time = idInt;
    }

    public void updatetimeserver(int idInt)
    {
        this.tileEntity.UpdateTimeServer(idInt);
    }
}
