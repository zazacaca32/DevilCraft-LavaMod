package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import java.io.IOException;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.donate.DonateManagerInv;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PacketMASendItemStacksDonateBuy extends PacketMA
{
    private byte Slot;
    private Object[] data;
    private Short dataCount;

    public PacketMASendItemStacksDonateBuy(byte Slot, Object... data)
    {
        this.Slot = Slot;
        this.data = data;
    }

    public PacketMASendItemStacksDonateBuy()
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
                DonateManagerInv var61 = LavaChestPlate.instanceDonate;

                if (this.Slot == 1)
                {
                    ArmorExtInventory armor = ExtendedPlayer.get(player).inventoryExt;
                    armor.setStatusDonate(1);

                    for (int i = 0; i < this.data.length; ++i)
                    {
                        if (this.data[i] != null && this.data[i] instanceof ItemStack && ((ItemStack)this.data[i]).stackSize > 0)
                        {
                            armor.invOfferts[i] = (ItemStack)this.data[i];
                        }
                        else
                        {
                            armor.invOfferts[i] = null;
                        }
                    }

                    var61.StartOffers(armor, player);
                }
            }
            catch (Exception var6)
            {
                System.out.println("PacketMASendItemStacksDonateBuy->" + var6.getMessage());
            }
        }
    }
}
