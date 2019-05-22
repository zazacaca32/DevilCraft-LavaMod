package net.minecraft.client.addon.tchestplate;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class LaikEvent extends Event implements Cancellable
{
    private static final HandlerList handlers = new HandlerList();
    public String Entity;
    public String targetEntity;
    public int count;
    private boolean cancel;

    public LaikEvent(String Entity, String targetEntity)
    {
        this.Entity = Entity;
        this.targetEntity = targetEntity;
    }

    public HandlerList getHandlers()
    {
        return handlers;
    }

    public static HandlerList getHandlerList()
    {
        return handlers;
    }

    public boolean isCancelled()
    {
        return this.cancel;
    }

    public void setCancelled(boolean cancel)
    {
        this.cancel = cancel;
    }
}
