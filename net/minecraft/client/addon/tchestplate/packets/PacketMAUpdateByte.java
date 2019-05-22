package net.minecraft.client.addon.tchestplate.packets;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.addon.tchestplate.IFastRightClickItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

public class PacketMAUpdateByte extends PacketMA
{
    private byte ID;

    public PacketMAUpdateByte(byte ID)
    {
        this.ID = ID;
    }

    public PacketMAUpdateByte()
    {
    }

    protected void write(ByteArrayDataOutput out)
    {
        out.writeByte(this.ID);
    }

    protected void read(ByteArrayDataInput in) throws PacketMA.ProtocolException
    {
        this.ID = in.readByte();
    }

    protected void execute(EntityPlayer player, Side side) throws PacketMA.ProtocolException
    {
        if (side.isServer())
        {
            try
            {
                if (this.ID < 9 && this.ID >= 0)
                {
                    ItemStack it = player.inventory.mainInventory[this.ID];

                    if (it != null)
                    {
                        if (it.getItem() instanceof IFastRightClickItem)
                        {
                            ((IFastRightClickItem)it.getItem()).FastRightClick(player, it);
                        }

                        if (it.stackSize <= 0)
                        {
                            player.inventory.mainInventory[this.ID] = null;
                            MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, it));
                        }
                    }
                }
            }
            catch (Exception var4)
            {
                ;
            }
        }
    }
}
