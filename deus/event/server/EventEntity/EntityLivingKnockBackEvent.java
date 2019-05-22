package deus.event.server.EventEntity;

import deus.event.Event;
import deus.event.HandlerList;

public class EntityLivingKnockBackEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();
    public Object owner;
    public int par2;
    public double par3;
    public double par5;
    public boolean stopY;

    public EntityLivingKnockBackEvent(Object owner, int par2, double par3, double par5, boolean stopY)
    {
        this.owner = owner;
        this.par2 = par2;
        this.par3 = par3;
        this.par5 = par5;
        this.stopY = stopY;
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
