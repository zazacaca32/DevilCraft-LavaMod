package net.minecraft.client.addon.tchestplate.Bukkit;

import java.lang.reflect.InvocationTargetException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public final class CallEventBukkit
{
    public static final void CallDonatePrivelegeskEvent(String player, int id, int damage)
    {
        DonatePrivelegesEvent event = new DonatePrivelegesEvent(player, id, damage);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }

    public static final boolean CallBlockBreakEvent(EntityPlayer player, World world, int x, int y, int z)
    {
        Player bukkitPlayer;
        org.bukkit.World bukkitWorld;

        try
        {
            bukkitWorld = (org.bukkit.World)world.getClass().getMethod("getWorld", new Class[0]).invoke(world, new Object[0]);
            bukkitPlayer = (Player)player.getClass().getMethod("getBukkitEntity", new Class[0]).invoke(player, new Object[0]);
        }
        catch (NoSuchMethodException var8)
        {
            System.out.println("[Bukkit Utils]: Could not invoke Bukkit methods! Are you running a vanilla Minecraft server?");
            var8.printStackTrace();
            return false;
        }
        catch (InvocationTargetException var9)
        {
            System.out.println("[Bukkit Utils]: Something went wrong inside Bukkit!");
            var9.printStackTrace();
            return false;
        }
        catch (Exception var10)
        {
            System.out.println("[Bukkit Utils]: Unknown exception!");
            var10.printStackTrace();
            return false;
        }

        BlockBreakEvent event = new BlockBreakEvent(bukkitWorld.getBlockAt(x, y, z), bukkitPlayer);
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event.isCancelled();
    }

    public static final boolean CallBlockCanAttackEvent(EntityPlayer player, EntityPlayer target)
    {
        Player bukkitPlayer;
        Player bukkitPlayerTarget;

        try
        {
            bukkitPlayer = (Player)player.getClass().getMethod("getBukkitEntity", new Class[0]).invoke(player, new Object[0]);
            bukkitPlayerTarget = (Player)player.getClass().getMethod("getBukkitEntity", new Class[0]).invoke(target, new Object[0]);
        }
        catch (NoSuchMethodException var5)
        {
            System.out.println("[Bukkit Utils]: Could not invoke Bukkit methods! Are you running a vanilla Minecraft server?");
            var5.printStackTrace();
            return false;
        }
        catch (InvocationTargetException var6)
        {
            System.out.println("[Bukkit Utils]: Something went wrong inside Bukkit!");
            var6.printStackTrace();
            return false;
        }
        catch (Exception var7)
        {
            System.out.println("[Bukkit Utils]: Unknown exception!");
            var7.printStackTrace();
            return false;
        }

        EntityDamageByEntityEvent event = new EntityDamageByEntityEvent(bukkitPlayer, bukkitPlayerTarget, DamageCause.ENTITY_ATTACK, 1);
        Bukkit.getServer().getPluginManager().callEvent(event);
        return event.isCancelled();
    }
}
