package net.minecraft.client.addon.tchestplate;

import net.minecraft.client.addon.lavamobs.gyroscooter.EntityGyroscooter;
import net.minecraft.entity.Entity;

public class LavamobsProxy implements ILavamobsProxy
{
    private final boolean isGiroClass;

    public LavamobsProxy() throws ProxyException
    {
        if (!Utils.isModLoaded("LavaMob"))
        {
            throw new ProxyException("no LavaMob");
        }
        else
        {
            this.isGiroClass = this.isLavaMobsGiroScuterClass();
        }
    }

    private final boolean isLavaMobsGiroScuterClass()
    {
        try
        {
            boolean var21 = Class.forName("net.minecraft.client.addon.lavamobs.gyroscooter.EntityGyroscooter") != null;
            return var21;
        }
        catch (ClassNotFoundException var2)
        {
            return false;
        }
    }

    public boolean isGiroScuterClient(Entity player)
    {
        return this.isGiroClass && player.ridingEntity instanceof EntityGyroscooter;
    }
}
