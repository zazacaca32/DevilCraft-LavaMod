package deus.event;

public interface IEventExecutor
{
    void execute(IListener var1, Event var2) throws Exception;
}
