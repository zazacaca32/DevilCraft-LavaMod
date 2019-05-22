package net.minecraft.client.addon.lavamobs.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.addon.lavamobs.ContainerBlockRaid;
import net.minecraft.client.addon.lavamobs.entity.EntityRaidBoss;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

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
        if (side.isServer())
        {
            try
            {
                MinecraftServer mob11 = MinecraftServer.getServer();

                if (this.ID == 1 && player.openContainer instanceof ContainerBlockRaid)
                {
                    ((ContainerBlockRaid)player.openContainer).updatetimeserver(this.idInt);
                }
            }
            catch (Exception var5)
            {
                ;
            }
        }
        else
        {
            try
            {
                if (this.ID == 1 && player.openContainer instanceof ContainerBlockRaid)
                {
                    ((ContainerBlockRaid)player.openContainer).updatetime(this.idInt);
                }

                Entity mob111;

                if (this.ID == 2 && (mob111 = Minecraft.getMinecraft().theWorld.getEntityByID(this.idInt)) instanceof EntityRaidBoss)
                {
                    ((EntityRaidBoss)mob111).SpawnPar(400);
                }
            }
            catch (Exception var4)
            {
                ;
            }
        }
    }
}
