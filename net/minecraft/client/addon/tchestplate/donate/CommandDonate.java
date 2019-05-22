package net.minecraft.client.addon.tchestplate.donate;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.addon.tchestplate.LavaChestPlate;
import net.minecraft.client.addon.tchestplate.Utils;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandDonate implements ICommand
{
    private List aliases = new ArrayList();

    public String getCommandName()
    {
        return "donate";
    }

    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "donate";
    }

    public List getCommandAliases()
    {
        return this.aliases;
    }

    public void processCommand(ICommandSender icommandsender, String[] astring)
    {
        if (!Utils.isClient())
        {
            EntityPlayer p = (EntityPlayer)icommandsender;

            if (astring.length == 0)
            {
                LavaChestPlate.proxy.openExtGui(3003, 0, p);
            }
            else
            {
                if ("add".equals(astring[0]))
                {
                    LavaChestPlate.proxy.openExtGui(3004, 0, p);
                }

                if ("adm".equals(astring[0]) && MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(p.getEntityName()))
                {
                    LavaChestPlate.proxy.openExtGui(3001, 0, p);
                }
            }
        }
    }

    public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    {
        return true;
    }

    public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring)
    {
        return null;
    }

    public boolean isUsernameIndex(String[] astring, int i)
    {
        return false;
    }

    public int compareTo(Object o)
    {
        return 0;
    }
}
