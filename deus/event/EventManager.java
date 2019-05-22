package deus.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class EventManager
{
    public static Map createRegisteredListeners(IListener listener)
    {
        HashMap ret = new HashMap();
        HashSet methods = null;

        try
        {
            Method[] var11 = listener.getClass().getMethods();
            methods = new HashSet(var11.length, Float.MAX_VALUE);
            Method[] var12 = var11;
            int var13 = var11.length;
            Method var14;
            int var15;

            for (var15 = 0; var15 < var13; ++var15)
            {
                var14 = var12[var15];
                methods.add(var14);
            }

            var13 = (var12 = listener.getClass().getDeclaredMethods()).length;

            for (var15 = 0; var15 < var13; ++var15)
            {
                var14 = var12[var15];
                methods.add(var14);
            }
        }
        catch (NoClassDefFoundError var10)
        {
            ;
        }

        Iterator var111 = methods.iterator();

        while (var111.hasNext())
        {
            final Method var121 = (Method)var111.next();
            EventHandler var131 = (EventHandler)var121.getAnnotation(EventHandler.class);

            if (var131 != null)
            {
                Class var141 = var121.getParameterTypes()[0];

                if (Event.class.isAssignableFrom(var141) && var121.getParameterTypes().length == 1)
                {
                    final Class var151 = var141.asSubclass(Event.class);
                    var121.setAccessible(true);
                    Object eventSet = (Set)ret.get(var151);

                    if (eventSet == null)
                    {
                        eventSet = new HashSet();
                        ret.put(var151, eventSet);
                    }

                    IEventExecutor executor = new IEventExecutor()
                    {
                        public void execute(IListener listener, Event event) throws Exception
                        {
                            try
                            {
                                if (var151.isAssignableFrom(event.getClass()))
                                {
                                    var121.invoke(listener, new Object[] {event});
                                }
                            }
                            catch (InvocationTargetException var4)
                            {
                                throw new Exception(var4.getCause());
                            }
                            catch (Throwable var5)
                            {
                                throw new Exception(var5);
                            }
                        }
                    };
                    ((Set)eventSet).add(new RegisteredListener(listener, executor, var131.ignoreCancelled(), var131.priority()));
                }
            }
        }

        return ret;
    }

    public static void registerEvents(IListener listener)
    {
        Iterator var2 = createRegisteredListeners(listener).entrySet().iterator();

        while (var2.hasNext())
        {
            Entry entry = (Entry)var2.next();
            getEventListeners(getRegistrationClass((Class)entry.getKey())).registerAll((Collection)entry.getValue());
        }
    }

    private static HandlerList getEventListeners(Class type)
    {
        try
        {
            Method var2 = getRegistrationClass(type).getDeclaredMethod("getHandlerList", new Class[0]);
            var2.setAccessible(true);
            return (HandlerList)var2.invoke((Object)null, new Object[0]);
        }
        catch (Exception var21)
        {
            return null;
        }
    }

    private static Class getRegistrationClass(Class clazz)
    {
        try
        {
            clazz.getDeclaredMethod("getHandlerList", new Class[0]);
            return clazz;
        }
        catch (NoSuchMethodException var2)
        {
            return clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Event.class) && Event.class.isAssignableFrom(clazz.getSuperclass()) ? getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class)) : null;
        }
    }

    public static void CallEvent(Event event)
    {
        HandlerList h = event.getHandlers();
        RegisteredListener[] var5;
        int var4 = (var5 = h.getRegisteredListeners()).length;

        for (int var3 = 0; var3 < var4; ++var3)
        {
            RegisteredListener listener = var5[var3];

            try
            {
                listener.callEvent(event);
            }
            catch (Exception var7)
            {
                var7.printStackTrace();
            }
        }
    }
}
