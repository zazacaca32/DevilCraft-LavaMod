package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

public class PacketMASendItemStackInSlotDonate extends PacketMA
{
    private byte Slot;
    private Object[] data;
    private Short dataCount;

    public PacketMASendItemStackInSlotDonate(byte Slot, Object... data)
    {
        this.Slot = Slot;
        this.data = data;
    }

    public PacketMASendItemStackInSlotDonate()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
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
        if (side.isServer())
        {
            try
            {
                if (!MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(player.getEntityName()))
                {
                    return;
                }

                DonateManagerInv var41 = LavaChestPlate.instanceDonate;

                if (this.Slot >= 0 && this.Slot < var41.GetStackSize())
                {
                    if (this.data != null && this.data.length > 0 && this.data[0] instanceof ItemStack)
                    {
                        var41.setInventorySlotContents(this.Slot, (ItemStack)this.data[0]);
                        var41.onInventoryChanged();
                    }
                    else
                    {
                        var41.setInventorySlotContents(this.Slot, (ItemStack)null);
                        var41.onInventoryChanged();
                    }
                }
            }
            catch (Exception var4)
            {
                System.out.println("PacketMASendItemStackInSlotDonate->" + var4.getMessage());
            }
        }
    }
}
