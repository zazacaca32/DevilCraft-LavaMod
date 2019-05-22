package deus.event;

public abstract class Event
{
    private boolean cancel = false;

    public abstract HandlerList getHandlers();

    public final boolean isCanceled()
    {
        return this.cancel;
    }

    public final void setCanceled(boolean canceled)
    {
        this.cancel = canceled;
    }

    public static enum Result
    {
        DENY("DENY", 0, "DENY", 0),
        DEFAULT("DEFAULT", 1, "DEFAULT", 1),
        ALLOW("ALLOW", 2, "ALLOW", 2);
        private static final Event.Result[] ENUM$VALUES = new Event.Result[]{DENY, DEFAULT, ALLOW};
        // $FF: synthetic field
        private static final Event.Result[] $VALUES = new Event.Result[]{DENY, DEFAULT, ALLOW};

        private Result(String var1, int var2, String var11, int var21) {}
    }
}
