package deus.event;

public class RegisteredListener
{
    private final IListener listener;
    private final IEventExecutor executor;
    private final EventPriority priority;

    public RegisteredListener(IListener listener, IEventExecutor executor, boolean listenCancelled, EventPriority priority)
    {
        this.listener = listener;
        this.executor = executor;
        this.priority = priority;
    }

    public IListener getListener()
    {
        return this.listener;
    }

    public EventPriority getPriority()
    {
        return this.priority;
    }

    public void callEvent(Event event) throws Exception
    {
        if (!event.isCanceled())
        {
            this.executor.execute(this.listener, event);
        }
    }
}
