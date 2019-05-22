package deus.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;

public class HandlerList
{
    private static ArrayList allLists = new ArrayList(30);
    private final EnumMap handlerslots = new EnumMap(EventPriority.class);
    private volatile RegisteredListener[] handlers = null;

    public HandlerList()
    {
        EventPriority[] var4;
        int var3 = (var4 = EventPriority.values()).length;

        for (int var81 = 0; var81 < var3; ++var81)
        {
            EventPriority var9 = var4[var81];
            this.handlerslots.put(var9, new ArrayList());
        }

        ArrayList var91 = allLists;
        ArrayList var10 = allLists;
        ArrayList var5 = allLists;
        ArrayList var6 = allLists;

        synchronized (allLists)
        {
            allLists.add(this);
        }
    }

    public synchronized void register(RegisteredListener listener)
    {
        if (((ArrayList)this.handlerslots.get(listener.getPriority())).contains(listener))
        {
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString());
        }
        else
        {
            this.handlers = null;
            ((ArrayList)this.handlerslots.get(listener.getPriority())).add(listener);
        }
    }

    public synchronized void bake()
    {
        if (this.handlers == null)
        {
            ArrayList entries = new ArrayList();
            Iterator var3 = this.handlerslots.entrySet().iterator();

            while (var3.hasNext())
            {
                Entry entry = (Entry)var3.next();
                entries.addAll((Collection)entry.getValue());
            }

            this.handlers = (RegisteredListener[])((RegisteredListener[])((RegisteredListener[])((RegisteredListener[])entries.toArray(new RegisteredListener[entries.size()]))));
        }
    }

    public synchronized void unregister(IListener listener)
    {
        boolean changed = false;
        Iterator var5 = this.handlerslots.values().iterator();

        while (var5.hasNext())
        {
            List list = (List)var5.next();
            ListIterator i = list.listIterator();

            while (i.hasNext())
            {
                if (((RegisteredListener)i.next()).getListener().equals(listener))
                {
                    i.remove();
                    changed = true;
                }
            }
        }

        if (changed)
        {
            this.handlers = null;
        }
    }

    public static void bakeAll()
    {
        ArrayList var0 = allLists;
        ArrayList var1 = allLists;
        ArrayList var2 = allLists;
        ArrayList var3 = allLists;

        synchronized (allLists)
        {
            Iterator var21 = allLists.iterator();

            while (var21.hasNext())
            {
                HandlerList h = (HandlerList)var21.next();
                h.bake();
            }
        }
    }

    public RegisteredListener[] getRegisteredListeners()
    {
        while (true)
        {
            RegisteredListener[] handlers = this.handlers;

            if (this.handlers != null)
            {
                return handlers;
            }

            this.bake();
        }
    }

    public void registerAll(Collection listeners)
    {
        Iterator var3 = listeners.iterator();

        while (var3.hasNext())
        {
            RegisteredListener listener = (RegisteredListener)var3.next();
            this.register(listener);
        }
    }

    public static ArrayList getHandlerLists()
    {
        ArrayList var1 = allLists;
        ArrayList var11 = allLists;
        ArrayList var2 = allLists;
        ArrayList var3 = allLists;

        synchronized (allLists)
        {
            ArrayList k = (ArrayList)allLists.clone();
            return k;
        }
    }
}
