package deus.event.client.EventEntity;

import deus.event.Event;
import deus.event.HandlerList;
import net.minecraft.client.Minecraft;

public class LavaClientChatEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    private final Minecraft mc;
    private final String message;

    public LavaClientChatEvent(Minecraft mc, String message)
    {
        this.mc = mc;
        this.message = message;
    }

    public Minecraft getMinecraft()
    {
        return this.mc;
    }

    public String getMessage()
    {
        return this.message;
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
