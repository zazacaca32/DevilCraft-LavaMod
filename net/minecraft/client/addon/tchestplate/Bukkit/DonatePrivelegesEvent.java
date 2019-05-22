package net.minecraft.client.addon.tchestplate.Bukkit;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class DonatePrivelegesEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private int id;
    private int damage;
    private String PlayerName;

    public int getId()
    {
        return this.id;
    }

    public int getDamage()
    {
        return this.damage;
    }

    public DonatePrivelegesEvent(String PlayerName, int id, int damage)
    {
        this.PlayerName = PlayerName;
        this.id = id;
        this.damage = damage;
    }

    public String getPlayerName()
    {
        return this.PlayerName;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }
}
