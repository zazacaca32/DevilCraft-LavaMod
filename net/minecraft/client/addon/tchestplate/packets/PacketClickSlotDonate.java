package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.donate.containers.DonateAdmContainerScroll;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public class PacketClickSlotDonate extends PacketMA
{
    int mouseClick;
    int shift;
    int slotNumber;

    public PacketClickSlotDonate()
    {
    }

    public PacketClickSlotDonate(int var1, int var2, int var3)
    {
        this.mouseClick = var1;
        this.shift = var2;
        this.slotNumber = var3;
    }

    protected void write(ByteArrayDataOutput var1)
    {
        var1.writeInt(this.mouseClick);
        var1.writeInt(this.shift);
        var1.writeInt(this.slotNumber);
    }

    protected void read(ByteArrayDataInput var1) throws PacketMA.ProtocolException
    {
        this.mouseClick = var1.readInt();
        this.shift = var1.readInt();
        this.slotNumber = var1.readInt();
    }

    private void executeServer(EntityPlayer var1, Side var2)
    {
        EntityPlayerMP var3 = (EntityPlayerMP)var1;
        Container var4 = var3.openContainer;

        if (var4 != null && var4 instanceof DonateAdmContainerScroll)
        {
            ((DonateAdmContainerScroll)var4).handleSlotDonate(this.mouseClick, this.shift, this.slotNumber, var3);
        }
    }

    protected void execute(EntityPlayer var1, Side var2) throws PacketMA.ProtocolException
    {
        if (var2.isServer())
        {
            this.executeServer(var1, var2);
        }
    }
}
