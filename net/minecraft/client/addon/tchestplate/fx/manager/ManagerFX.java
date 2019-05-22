package net.minecraft.client.addon.tchestplate.fx.manager;

import net.minecraft.client.addon.tchestplate.items.ItemAmulets;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateFX;
import net.minecraft.client.addon.tchestplate.player.ExtendedPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IntHashMap;

public class ManagerFX
{
    public static final IntHashMap fxID = new IntHashMap();

    public static final FX_base getFXfromID(int var0) throws Exception
    {
        Class var1 = (Class)fxID.lookup(var0);

        if (var1 == null)
        {
            throw new Exception("Unknown Effect Id ManagerFX!");
        }
        else
        {
            FX_base var2 = (FX_base)var1.newInstance();
            var2.setCheckFlag((byte)0);
            return var2;
        }
    }

    public static final void sendEffectToClientsAllPlayers(EntityPlayerMP var0, FX_base var1)
    {
        var0.getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(var0, (new PacketMAUpdateFX(var1.getIDeffect(), var0.entityId, var1.getMilliseconds(), var1.duration)).makePacket());
    }

    public static final void setEffectShock(Entity var0, int var1, byte var2)
    {
        if (var0 != null && var0 instanceof EntityPlayerMP)
        {
            ExtendedPlayer var3 = ExtendedPlayer.get((EntityPlayer)var0);
            ItemStack var4 = var3.inventoryExt.inventory[3];

            if (var4 != null && var4.getItem() instanceof ItemAmulets && ((ItemAmulets)var4.getItem()).isCanceledShok(var4))
            {
                return;
            }

            Shock var5 = new Shock(var1, var2);
            var3.addEffect(var5);
            sendEffectToClientsAllPlayers((EntityPlayerMP)var0, var5);
            var0.worldObj.playSoundEffect(var0.posX + 0.5D, var0.posY + 0.5D, var0.posZ + 0.5D, "tchestplate.shock2", 3.0F, 1.5F);
        }
    }

    public static final void setEffectSlowe(Entity var0, int var1, byte var2)
    {
        if (var0 != null && var0 instanceof EntityPlayerMP)
        {
            Slowe var3 = new Slowe(var1, var2);
            ExtendedPlayer.get((EntityPlayer)var0).addEffect(var3);
            sendEffectToClientsAllPlayers((EntityPlayerMP)var0, var3);
            var0.worldObj.playSoundEffect(var0.posX + 0.5D, var0.posY + 0.5D, var0.posZ + 0.5D, "tchestplate.friz", 3.0F, 1.0F);
        }
    }

    public static void showLvLweapon(boolean b, byte toch1, float f, float g, float h, float i, float j, float k)
    {
    }

    static
    {
        fxID.addKey(1, Shock.class);
        fxID.addKey(2, Slowe.class);
        fxID.addKey(3, DeusCloak.class);
    }
}
