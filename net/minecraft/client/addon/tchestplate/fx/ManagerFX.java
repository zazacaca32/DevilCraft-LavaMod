package net.minecraft.client.addon.tchestplate.fx;

import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.fx.manager.DeusCloak;
import net.minecraft.client.addon.tchestplate.fx.manager.DeusK;
import net.minecraft.client.addon.tchestplate.fx.manager.FX_base;
import net.minecraft.client.addon.tchestplate.fx.manager.Shock;
import net.minecraft.client.addon.tchestplate.fx.manager.Slowe;
import net.minecraft.client.addon.tchestplate.packets.PacketMAUpdateFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IntHashMap;

public class ManagerFX
{
    private static final IntHashMap fxID = new IntHashMap();
    private static float t;

    public static final void showLvLweapon(boolean firstperson, byte lvl, float x, float y, float z, float x1, float y1, float z1)
    {
        LavaChestPlate.proxy.showLvLweapon(firstperson, lvl, x, y, z, x1, y1, z1);
    }

    public static final FX_base getFXfromID(int ID_FX) throws Exception
    {
        Class clazz = (Class)fxID.lookup(ID_FX);

        if (clazz == null)
        {
            throw new Exception("Unknown Effect Id ManagerFX!");
        }
        else
        {
            FX_base f = (FX_base)clazz.newInstance();
            f.setCheckFlag((byte)0);
            return f;
        }
    }

    public static final void sendEffectToClientsAllPlayers(EntityPlayerMP owner, FX_base fx)
    {
        owner.getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(owner, (new PacketMAUpdateFX(fx.getIDeffect(), owner.entityId, fx.getMilliseconds(), fx.duration)).makePacket());
    }

    public static final void setEffectShock(Entity pointedEntity, int mills, byte duration)
    {
        if (pointedEntity != null && pointedEntity instanceof EntityPlayerMP)
        {
            setEffectShock(pointedEntity, mills, duration);
        }
    }

    public static final void setEffectSlowe(Entity pointedEntity, int mills, byte duration)
    {
        if (pointedEntity != null && pointedEntity instanceof EntityPlayerMP)
        {
            setEffectSlowe(pointedEntity, mills, duration);
        }
    }

    static
    {
        fxID.addKey(4, Shock.class);
        fxID.addKey(5, Slowe.class);
        fxID.addKey(6, DeusCloak.class);
        fxID.addKey(7, DeusK.class);
    }
}
