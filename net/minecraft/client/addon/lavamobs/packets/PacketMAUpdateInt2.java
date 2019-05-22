package net.minecraft.client.addon.lavamobs.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.entity.EntityGuardRaid;
import net.minecraft.client.addon.lavamobs.entity.EntityRaidBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAUpdateInt2 extends PacketMA
{
    private byte ID;
    private int idInt;
    private int idInt2;

    public PacketMAUpdateInt2(byte ID, int idInt, int idInt2)
    {
        this.ID = ID;
        this.idInt = idInt;
        this.idInt2 = idInt2;
    }

    public PacketMAUpdateInt2()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeByte(this.ID);
        out.writeInt(this.idInt);
        out.writeInt(this.idInt2);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.ID = in.readByte();
        this.idInt = in.readInt();
        this.idInt2 = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (!side.isServer())
        {
            try
            {
                if (this.ID == 1)
                {
                    Entity mob = Minecraft.getMinecraft().theWorld.getEntityByID(this.idInt);
                    Entity mobBoss = Minecraft.getMinecraft().theWorld.getEntityByID(this.idInt2);

                    if (mob instanceof EntityGuardRaid && mobBoss instanceof EntityRaidBoss)
                    {
                        ((EntityGuardRaid)mob).setraidboss((EntityRaidBoss)mobBoss);
                        ((EntityGuardRaid)mob).effectheal = 100;
                    }
                }
            }
            catch (Exception var5)
            {
                ;
            }
        }
    }
}
