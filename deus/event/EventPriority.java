package deus.event;

public enum EventPriority
{
    LOWEST("LOWEST", 0, "LOWEST", 0, 0),
    LOW("LOW", 1, "LOW", 1, 1),
    NORMAL("NORMAL", 2, "NORMAL", 2, 2),
    HIGH("HIGH", 3, "HIGH", 3, 3),
    HIGHEST("HIGHEST", 4, "HIGHEST", 4, 4),
    MONITOR("MONITOR", 5, "MONITOR", 5, 5);
    private final int slot;
    private static final EventPriority[] ENUM$VALUES = new EventPriority[]{LOWEST, LOW, NORMAL, HIGH, HIGHEST, MONITOR};
    // $FF: synthetic field
    private static final EventPriority[] $VALUES = new EventPriority[]{LOWEST, LOW, NORMAL, HIGH, HIGHEST, MONITOR};

    private EventPriority(String var1, int var2, String var11, int var21, int slot)
    {
        this.slot = slot;
    }

    public int getSlot()
    {
        return this.slot;
    }
}
