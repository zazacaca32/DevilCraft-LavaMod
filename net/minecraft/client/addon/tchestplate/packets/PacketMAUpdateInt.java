package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.tchestplate.ClientProxy;
import net.minecraft.client.addon.tchestplate.overlaygui.ArmorExtInventory;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAUpdateInt extends PacketMA
{
    private byte ID;
    private int idInt;

    public PacketMAUpdateInt(byte ID, int idInt)
    {
        this.ID = ID;
        this.idInt = idInt;
    }

    public PacketMAUpdateInt()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeByte(this.ID);
        out.writeInt(this.idInt);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.ID = in.readByte();
        this.idInt = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (!side.isServer())
        {
            try
            {
                if (this.ID == 1)
                {
                    ExtendedPlayer.get(player).inventoryExt.coin = this.idInt;
                }

                ArmorExtInventory e2;

                if (this.ID == 2)
                {
                    e2 = ExtendedPlayer.get(player).inventoryExt;
                    e2.coin = this.idInt;
                    e2.clearinvOfferts();
                }

                if (this.ID == 3)
                {
                    e2 = ExtendedPlayer.get(player).inventoryExt;
                    e2.statusDonateOffert = this.idInt;
                }

                if (this.ID == 4)
                {
                    e2 = ExtendedPlayer.get(player).inventoryExt;
                    e2.coin = this.idInt;
                }

                if (this.ID == 5)
                {
                    e2 = ExtendedPlayer.get(player).inventoryExt;
                    e2.statusDonateOffert = this.idInt;
                }

                if (this.ID == 6)
                {
                    Entity e21 = player.worldObj.getEntityByID(this.idInt);

                    if (e21 != null && e21 instanceof EntityPlayer)
                    {
                        float var10002 = (float)e21.posX + 0.5F;
                        float var10003 = (float)e21.posY + 0.5F;
                        Minecraft.getMinecraft().sndManager.playSound("tchestplate.shoot", var10002, var10003, (float)e21.posZ + 0.5F, 0.1F, 0.2F + e21.worldObj.rand.nextFloat() * 0.2F);
                        ClientProxy.blockSparkle1(e21.worldObj, e21.posX - 0.5D, e21.posY - 0.0D, e21.posZ - 0.5D, 1, 4);
                    }
                }
            }
            catch (Exception var7)
            {
                ;
            }
        }
    }
}
