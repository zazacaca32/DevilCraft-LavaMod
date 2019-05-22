package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMASwingUseItem extends PacketMA
{
    private byte flag;
    private int EntityID;
    private short count;

    public PacketMASwingUseItem(byte var1, int var2, short var3)
    {
        this.EntityID = var2;
        this.count = var3;
        this.flag = var1;
    }

    public PacketMASwingUseItem()
    {
    }

    protected void write(ByteArrayDataOutput var1)
    {
        var1.writeByte(this.flag);
        var1.writeInt(this.EntityID);
        var1.writeShort(this.count);
    }

    protected void read(ByteArrayDataInput var1) throws PacketMA.ProtocolException
    {
        this.flag = var1.readByte();
        this.EntityID = var1.readInt();
        this.count = var1.readShort();
    }

    private void case1(EntityPlayer var1, Side var2)
    {
        if (var2.isClient())
        {
            try
            {
                Entity var3 = var1.worldObj.getEntityByID(this.EntityID);

                if (var3 != null && var3 instanceof EntityPlayer)
                {
                    ExtendedPlayer var4 = ExtendedPlayer.get((EntityPlayer)var3);
                    var4.setSwing(this.count);
                }
            }
            catch (Exception var5)
            {
                ;
            }
        }
    }

    private void case2(EntityPlayer var1, Side var2)
    {
        Entity var3;

        if (var2.isClient())
        {
            try
            {
                var3 = var1.worldObj.getEntityByID(this.EntityID);

                if (var3 != null && var3 instanceof EntityPlayer)
                {
                    ;
                }
            }
            catch (Exception var6)
            {
                ;
            }
        }
        else
        {
            try
            {
                var3 = var1.worldObj.getEntityByID(this.EntityID);

                if (var3 != null && var3 instanceof EntityPlayer)
                {
                    ;
                }
            }
            catch (Exception var5)
            {
                var5.printStackTrace();
            }
        }
    }

    protected void execute(EntityPlayer var1, Side var2) throws PacketMA.ProtocolException
    {
        switch (this.flag)
        {
            case 1:
                this.case1(var1, var2);
                break;

            case 2:
                this.case2(var1, var2);
        }
    }
}
