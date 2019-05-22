package net.minecraft.client.addon.tchestplate.fx.manager;

import deus.event.EventHandler;
import deus.event.EventManager;
import deus.event.EventPriority;
import deus.event.IListener;
import deus.event.server.EventEntity.EntityLivingKnockBackEvent;
import deus.event.server.EventEntity.EntityTrackerEntryEvent;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.addon.tchestplate.packets.PacketMARemoveFX;
import net.minecraft.client.addon.tchestplate.packets.PacketMASendItemStackInSlot;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateFX;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ListenerServerHack implements IListener
{
    @EventHandler(
        priority = EventPriority.HIGHEST
    )
    public void KnockBackEvent(EntityLivingKnockBackEvent var1)
    {
        if (var1.owner instanceof EntityPlayerMP)
        {
            ArrayList var2 = ExtendedPlayer.get((EntityPlayer)var1.owner).activepotions;

            if (var2 == null)
            {
                return;
            }

            if (var2.isEmpty())
            {
                return;
            }

            Iterator var3 = var2.iterator();

            while (var3.hasNext())
            {
                if (((FX_base)var3.next()).getIDeffect() == 1)
                {
                    var1.setCanceled(true);
                    break;
                }
            }
        }
    }

    @EventHandler(
        priority = EventPriority.HIGHEST
    )
    public void EntityTrackerEntry(EntityTrackerEntryEvent var1)
    {
        if (var1.owner instanceof EntityPlayerMP && var1.target instanceof EntityPlayerMP)
        {
            EntityPlayerMP var3 = (EntityPlayerMP)var1.owner;
            EntityPlayerMP var4 = (EntityPlayerMP)var1.target;
            ExtendedPlayer var5 = ExtendedPlayer.get(var3);

            if (var5.inventoryExt != null)
            {
                for (int var9 = 0; var9 < var5.inventoryExt.getSizeInventory(); ++var9)
                {
                    ItemStack var101 = var5.inventoryExt.inventory[var9];

                    if (var101 != null)
                    {
                        var4.playerNetServerHandler.sendPacketToPlayer((new PacketMASendItemStackInSlot(var3.entityId, (byte)1, (byte)var9, new Object[] {var101})).makePacket());
                    }
                }
            }

            ArrayList var91 = var5.activepotions;

            if (var5.activepotions != null && !var91.isEmpty())
            {
                Iterator var101 = var91.iterator();

                while (var101.hasNext())
                {
                    FX_base var10 = (FX_base)var101.next();

                    if (!var10.UpdateServer(var5, var3))
                    {
                        var101.remove();
                        var4.playerNetServerHandler.sendPacketToPlayer((new PacketMARemoveFX(var10.getIDeffect(), var3.entityId)).makePacket());
                    }
                    else
                    {
                        int var8 = var10.getMilliseconds();

                        if (var8 > 0)
                        {
                            var4.playerNetServerHandler.sendPacketToPlayer((new PacketMAUpdateFX(var10.getIDeffect(), var3.entityId, var8, (byte)0)).makePacket());
                        }
                        else
                        {
                            var4.playerNetServerHandler.sendPacketToPlayer((new PacketMARemoveFX(var10.getIDeffect(), var3.entityId)).makePacket());
                        }
                    }
                }
            }
        }
    }

    public static void Start()
    {
        EventManager.registerEvents(new ListenerServerHack());
    }
}
