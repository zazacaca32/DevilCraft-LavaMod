package deus.event.server.EventEntity;

import deus.event.Event;
import deus.event.HandlerList;

public class EntityTrackerEntryEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    public Object owner;
    public Object target;

    public EntityTrackerEntryEvent(Object owner, Object target)
    {
        this.owner = owner;
        this.target = target;
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
