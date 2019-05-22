package net.minecraft.client.addon.tchestplate;

import org.bukkit.Bukkit;

public class CallLaikEvent
{
    public static final boolean[] callLaikEvent(String Entity, String targetEntity, int flag)
    {
        boolean[] res = new boolean[2];
        LaikEvent event = new LaikEvent(Entity, targetEntity);
        event.count = flag;
        Bukkit.getServer().getPluginManager().callEvent(event);

        if (event.count == -1)
        {
            res[1] = true;
        }
        else
        {
            res[1] = false;
        }

        res[0] = event.isCancelled();
        return res;
    }

    public static final int callLaikEvent(String Entity)
    {
        if ("Deus".equalsIgnoreCase(Entity))
        {
            return ClientProxy.shortArrayToInt(new short[] {(short)999, (short)0});
        }
        else
        {
            LaikEvent event = new LaikEvent(Entity, "&");
            Bukkit.getServer().getPluginManager().callEvent(event);
            return event.count;
        }
    }
}
