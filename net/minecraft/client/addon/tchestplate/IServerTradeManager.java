package net.minecraft.client.addon.tchestplate;

import net.minecraft.client.addon.tchestplate.util.Coords;
import net.minecraft.entity.player.EntityPlayer;

public interface IServerTradeManager
{
    void tickServer();

    void addTicket(TicketTrade var1);

    Coords getSpawnCoords();

    boolean isSpawnRadius(EntityPlayer var1, int var2);
}
