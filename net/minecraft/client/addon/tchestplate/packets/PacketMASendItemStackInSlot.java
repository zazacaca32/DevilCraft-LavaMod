package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketMASendItemStackInSlot extends PacketMA
{
    private byte idInventory;
    private byte Slot;
    private Object[] data;
    private int EntityID;
    private Short dataCount;

    public PacketMASendItemStackInSlot(int EntityID, byte idInventory, byte Slot, Object... data)
    {
        this.EntityID = EntityID;
        this.idInventory = idInventory;
        this.Slot = Slot;
        this.data = data;
    }

    public PacketMASendItemStackInSlot()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.EntityID);
        out.writeByte(this.idInventory);
        out.writeByte(this.Slot);
        out.writeShort(this.data.length);
        Object[] arr$ = this.data;
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            Object o = arr$[i$];

            try
            {
                DataEncoder.encode(out, o);
            }
            catch (IOException var7)
            {
                ;
            }
        }
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.EntityID = in.readInt();
        this.idInventory = in.readByte();
        this.Slot = in.readByte();
        this.dataCount = Short.valueOf(in.readShort());
        this.data = new Object[this.dataCount.shortValue()];

        for (int i = 0; i < this.dataCount.shortValue(); ++i)
        {
            try
            {
                this.data[i] = DataEncoder.decode(in);
            }
            catch (IOException var4)
            {
                ;
            }
        }
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isClient())
        {
            try
            {
                Entity var51 = player.worldObj.getEntityByID(this.EntityID);

                if (var51 != null && var51 instanceof EntityPlayer)
                {
                    ArmorExtInventory inv = ExtendedPlayer.get((EntityPlayer)var51).inventoryExt;

                    if (this.Slot >= 0 && this.Slot < inv.getSizeInventory())
                    {
                        if (this.data != null && this.data.length > 0 && this.data[0] instanceof ItemStack)
                        {
                            System.out.println("" + this.Slot + " " + ((ItemStack)this.data[0]).itemID);
                            inv.inventory[this.Slot] = (ItemStack)this.data[0];
                        }
                        else
                        {
                            System.out.println("" + this.Slot + " " + null);
                            inv.inventory[this.Slot] = null;
                        }
                    }
                }
            }
            catch (Exception var5)
            {
                System.out.println("PacketMASendItemStackInSlot->" + var5.getMessage());
            }
        }
    }
}
