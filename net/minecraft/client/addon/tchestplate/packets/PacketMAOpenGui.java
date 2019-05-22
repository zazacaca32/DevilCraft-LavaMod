package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.player.EntityPlayer;

public class PacketMAOpenGui extends PacketMA
{
    private int iDGui;
    private int iDparam;

    public PacketMAOpenGui(int iDGui)
    {
        this.iDGui = iDGui;
        this.iDparam = 0;
    }

    public PacketMAOpenGui(int iDGui, int iDparam)
    {
        this.iDGui = iDGui;
        this.iDparam = iDparam;
    }

    public PacketMAOpenGui()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeInt(this.iDGui);
        out.writeInt(this.iDparam);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.iDGui = in.readInt();
        this.iDparam = in.readInt();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        LavaChestPlate.proxy.openExtGui(this.iDGui, this.iDparam, player);
    }
}
