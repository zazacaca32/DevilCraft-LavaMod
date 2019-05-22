package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class PacketInvisible extends PacketMA
{
    private boolean invis;

    public PacketInvisible(boolean invis)
    {
        this.invis = invis;
    }

    public PacketInvisible()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeBoolean(this.invis);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.invis = in.readBoolean();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isServer())
        {
            if (player.isPotionActive(Potion.invisibility.id))
            {
                player.removePotionEffect(Potion.invisibility.id);
            }

            LavaChestPlate.proxy.setInvisible(player, this.invis);
        }

        if (side.isClient())
        {
            LavaChestPlate.proxy.setInvisible(player, this.invis);
        }
    }
}
