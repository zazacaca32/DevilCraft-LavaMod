package net.minecraft.client.addon.tchestplate.util;

import cpw.mods.fml.common.FMLLog;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LavaLog
{
    private static final Logger log;

    public static void log(Level level, String message)
    {
        log.log(level, message);
    }

    public static void log(Exception ex)
    {
        log.log(Level.WARNING, (String)null, ex);
    }

    public static void info(String message)
    {
        log.info(message);
    }

    public static void warning(String message)
    {
        log.warning(message);
    }

    public static void severe(String message)
    {
        log.severe(message);
    }

    static
    {
        (log = Logger.getLogger("LavaChestPlate")).setParent(FMLLog.getLogger());
    }
}
